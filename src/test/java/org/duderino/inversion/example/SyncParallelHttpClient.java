package org.duderino.inversion.example;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.nio.reactor.IOReactorException;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SyncParallelHttpClient {
    private static Logger logger = Logger.getLogger(AsyncParallelHttpClient.class.getName());

    // Don't want to make these class variables because then they might be shared illegally between threads
    // private final DefaultHttpAsyncClient client = new DefaultHttpAsyncClient();
    // private final Map<HttpUriRequest, Object> results = new HashMap<HttpUriRequest, Object>();

    public SyncParallelHttpClient() {
    }

    public Map<String, Object> fetch(String[] URLs) throws IOReactorException {
        DefaultHttpAsyncClient client = new DefaultHttpAsyncClient();
        Map<String, Object> results = new HashMap<String, Object>();

        client.start();

        Future<HttpResponse>[] futures = (Future<HttpResponse>[]) Array.newInstance(Future.class, URLs.length);

        try {
            for (int i = 0; i < URLs.length; ++i) {
                futures[i] = client.execute(new HttpGet(URLs[i]), null);
            }

            for (int i = 0; i < URLs.length; ++i) {
                try {
                    HttpResponse response = futures[i].get();

                    results.put(URLs[i], response);
                } catch (ExecutionException ex) {
                    results.put(URLs[i], ex.getCause());
                } catch (Exception ex) {
                    results.put(URLs[i], ex);
                }
            }
        } finally {
            try {
                client.shutdown();
            } catch (InterruptedException ex) {
                if (logger.isLoggable(Level.WARNING)) {
                    logger.log(Level.WARNING, "Cannot shutdown http client", ex);
                }
            }
        }

        return results;
    }
}
