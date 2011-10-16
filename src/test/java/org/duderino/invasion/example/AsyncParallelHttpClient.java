package org.duderino.invasion.example;

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
import java.util.logging.Logger;

public class AsyncParallelHttpClient {
    private static Logger logger = Logger.getLogger(AsyncParallelHttpClient.class.getName());

    // Don't want to make these class variables because then they might be shared illegally between threads
    // private final DefaultHttpAsyncClient client = new DefaultHttpAsyncClient();
    // private final Map<HttpUriRequest, Object> results = new HashMap<HttpUriRequest, Object>();

    public AsyncParallelHttpClient() {
    }

    public interface Callback {
        public void completed(Map<String, Object> results);
    }

    public void fetch(final String[] URLs, final Callback externalCallback) throws IOReactorException {
        final DefaultHttpAsyncClient client = new DefaultHttpAsyncClient();
        final Map<String, Object> results = new HashMap<String, Object>();

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
                        /*try {   Deadlocks.   Not necessary to fix this to illustrate our point.
                            client.shutdown();
                        } catch (InterruptedException ex) {
                            if (logger.isLoggable(Level.WARNING)) {
                                logger.log(Level.WARNING, "Cannot shutdown http client", ex);
                            }
                        } */

                        externalCallback.completed(results);
                    }
                }
            });
        }
    }
}
