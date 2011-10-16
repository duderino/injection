package org.duderino.invasion.example.flavor1;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.nio.reactor.IOReactorException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Helper {

    private AsyncParallelHttpClient client = [
            default: new AsyncParallelHttpClient(),
            response1: new AsyncParallelHttpClient() {
                @Override
                public void fetch(final String[] urls, final Callback callback) throws IOReactorException {
                    Map<String, Object> map = new HashMap<String, Object>();

                    for (String url : urls) {
                        if ("http://foo.com".equals(url)) {
                            map.put(url, new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK"));
                        } else if ("http://bar.com".equals(url)) {
                            map.put(url, new BasicHttpResponse(HttpVersion.HTTP_1_1, 404, "Not Found"));
                        } else {
                            map.put(url, new Exception("Request failed"));
                        }
                    }

                    callback.completed(map);
                }
            },
            response2: new AsyncParallelHttpClient() {
                @Override
                public void fetch(final String[] urls, final Callback callback) throws IOReactorException {
                    Map<String, Object> map = new HashMap<String, Object>();

                    for (String url : urls) {
                        if ("http://foo.com".equals(url)) {
                            map.put(url, new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK"));
                        } else if ("http://bar.com".equals(url)) {
                            map.put(url, new BasicHttpResponse(HttpVersion.HTTP_1_1, 404, "Not Found"));
                        } else {
                            map.put(url, new Exception("Request failed"));
                        }
                    }

                    callback.completed(map);
                }
            }
    ];

    private static final String[] URLS = {"http://foo.com", "http://bar.com", "http://baz.com"};

    public Helper() {
    }

    private static class Callback implements AsyncParallelHttpClient.Callback {
        private int successes = 0;
        private final CountDownLatch latch = new CountDownLatch(1);

        public void completed(Map<String, Object> results) {
            for (Map.Entry<String, Object> entry : results.entrySet()) {
                if (entry.getValue() instanceof HttpResponse) {
                    HttpResponse response = (HttpResponse) entry.getValue();

                    if (200 <= response.getStatusLine().getStatusCode() && 300 > response.getStatusLine().getStatusCode()) {
                        ++successes;
                    }
                }
            }

            latch.countDown();
        }

        public int getSuccesses() {
            return successes;
        }

        public void join() throws InterruptedException {
            latch.wait();
        }
    }

    /**
     * Do it.
     *
     * @return number of successful replies
     */
    public int doIt() {
        Callback callback = new Callback();

        try {
            client.fetch(URLS, callback);
            callback.join();
        } catch (Exception ex) {
            return 0;
        }

        return callback.getSuccesses();
    }

    test doIt {
        Helper helper = new(response1) Helper();

        assert helper.doIt() == 1;
    }

    test doIt {
        Helper helper = new(response2) Helper();

        assert helper.doIt() == 1;
    }

}
