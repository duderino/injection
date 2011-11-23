package org.duderino.injection.jmockit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.nio.concurrent.FutureCallback;
import org.apache.http.nio.reactor.IOReactorException;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;

/**
 * A HTTP client that fetches and parses a XML response from n URLs concurrently and asynchronously.
 */
public class XmlFetcher {

    public XmlFetcher() {
    }

    public interface Callback {
        public void completed(Map<String, Object> results);
    }

    /**
     * GET and parse n XML responses from n URLs.
     *
     * @param URLs             The URLs to fetch
     * @param externalCallback A user-supplied callback that will be passed a map of URL to Object results.  Objects
     *                         can be downcast to either an org.w3c.dom.Document or a java.lang.Exception
     */
    public void fetch(final String[] URLs, final Callback externalCallback) {
        final Map<String, Object> results = new HashMap<String, Object>();
        final DefaultHttpAsyncClient client;

        try {
            client = new DefaultHttpAsyncClient();
        } catch (IOReactorException ex) {
            for (String url : URLs) {
                results.put(url, ex);
            }

            return;
        }

        client.start();

        for (final String URL : URLs) {
            client.execute(new HttpGet(URL), new FutureCallback<HttpResponse>() {
                private String url = URL;

                public void completed(HttpResponse response) {
                    if (200 > response.getStatusLine().getStatusCode() || 300 <= response.getStatusLine().getStatusCode()) {
                        handleEvent(new HttpException(response.getStatusLine().getReasonPhrase()));

                        return;
                    }

                    HttpEntity body = response.getEntity();

                    try {
                        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                        Document document = docBuilder.parse(body.getContent());

                        handleEvent(document);
                    } catch (Exception ex) {
                        handleEvent(ex);
                    }
                }

                public void failed(Exception ex) {
                    handleEvent(ex);
                }

                public void cancelled() {
                    handleEvent(new CancellationException());
                }

                private void handleEvent(Object object) {
                    boolean isComplete = false;

                    synchronized (results) {
                        results.put(url, object);

                        if (results.size() == URLs.length) {
                            isComplete = true;
                        }
                    }

                    if (isComplete) {
                        // TODO - do a client.shutdown() without a deadlock.
                        externalCallback.completed(results);
                    }
                }
            });
        }
    }

    /**
     * GET and parse n XML responses from n URLs
     *
     * @param URLs The URLs to fetch
     * @return A map of URL to Object results.  Objects can be downcast to either an org.w3c.dom.Document or a java.lang.Exception
     * @throws InterruptedException
     */
    public Map<String, Object> fetch(String[] URLs) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Map<String, Object>[] result = (Map<String, Object>[]) new Map[1];

        fetch(URLs, new Callback() {
            @Override
            public void completed(Map<String, Object> results) {
                result[0] = results;
                latch.countDown();
            }
        });

        latch.await();

        return result[0];
    }
}
