package org.duderino.injection.jmockit;

import mockit.Mock;
import mockit.MockClass;
import mockit.Mockit;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.nio.client.AbstractHttpAsyncClient;
import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.nio.concurrent.FutureCallback;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.util.Map;
import java.util.concurrent.Future;

@Test
public class XmlFetcherTest {
    public void functionalTest() throws Exception {
        final String URL = "http://musicbrainz.org/ws/1/artist?limit=1&query=madonna";

        XmlFetcher client = new XmlFetcher();

        client.fetch(new String[]{URL}, new XmlFetcher.Callback() {
            @Override
            public void completed(Map<String, Object> results) {
                Object result = results.get(URL);

                assert result instanceof Document;

                Document document = (Document) result;

                NodeList nodeList = document.getElementsByTagName("name");

                assert null != nodeList;
                assert 1 == nodeList.getLength();

                Node node = nodeList.item(0);

                assert "Madonna".equals(node.getTextContent());
            }
        });
    }

    @MockClass(realClass = AbstractHttpAsyncClient.class)
    public static final class MockDefaultHttpAsyncClient {
        private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><metadata xmlns=\"http://musicbrainz.org/ns/mmd-1.0#\" xmlns:ext=\"http://musicbrainz.org/ns/ext-1.0#\"><artist-list offset=\"0\" count=\"13\"><artist type=\"Person\" id=\"79239441-bfd5-4981-a70c-55c3f15c1287\" ext:score=\"100\"><name>Madonna</name><sort-name>Madonna</sort-name><life-span begin=\"1958-08-16\"/></artist></artist-list></metadata>";

        @Mock
        public Future<HttpResponse> execute(HttpUriRequest request, FutureCallback<HttpResponse> callback) {
            try {
                BasicHttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK");

                BasicHttpEntity entity = new BasicHttpEntity();

                byte[] content = XML.getBytes("UTF-8");

                entity.setContentLength(content.length);
                entity.setContentType("application/xml;charset=\"utf8\"");
                entity.setContent(new ByteArrayInputStream(content));

                response.setEntity(entity);


                callback.completed(response);
            } catch (Exception ex) {
                callback.failed(ex);
            }

            return null;
        }
    }


    @Test
    public void testValidResponseSync() throws Exception {
        final String URL = "http://musicbrainz.org/ws/1/artist?limit=1&query=madonna";

        Mockit.setUpMock(AbstractHttpAsyncClient.class, MockDefaultHttpAsyncClient.class);

        XmlFetcher client = new XmlFetcher();

        Map<String, Object> results = client.fetch(new String[]{URL});

        Object result = results.get("http://musicbrainz.org/ws/1/artist?limit=1&query=madonna");

        assert result instanceof Document;

        Document document = (Document) result;

        NodeList nodeList = document.getElementsByTagName("name");

        assert null != nodeList;
        assert 1 == nodeList.getLength();

        Node node = nodeList.item(0);

        assert "Madonna".equals(node.getTextContent());
    }

    @Test
    public void testValidResponseAsync() throws Exception {
        final String URL = "http://musicbrainz.org/ws/1/artist?limit=1&query=madonna";

        // EVIL - Must setup the mock for AbstractHttpAsyncClient because that is where the execute method is
        // defined.   If you try to setup the mock against DefaultHttpAsyncClient (its subclass), you would see
        // an error message like this:
        // testValidResponseAsync(org.duderino.invasion.example.jmockit_test.XmlFetcherTest)  Time elapsed: 0.009 sec  <<< FAILURE!
        // java.lang.IllegalArgumentException: Matching real methods not found for the following mocks of org.duderino.invasion.example.jmockit_test.XmlFetcherTest$MockDefaultHttpAsyncClient:
        // java.util.concurrent.Future execute(org.apache.http.client.methods.HttpUriRequest, org.apache.http.nio.concurrent.FutureCallback)
        //     at org.duderino.invasion.example.jmockit_test.XmlFetcherTest.testValidResponseAsync(XmlFetcherTest.java:110)

        Mockit.setUpMock(AbstractHttpAsyncClient.class, new DefaultHttpAsyncClient() {
            private static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><metadata xmlns=\"http://musicbrainz.org/ns/mmd-1.0#\" xmlns:ext=\"http://musicbrainz.org/ns/ext-1.0#\"><artist-list offset=\"0\" count=\"13\"><artist type=\"Person\" id=\"79239441-bfd5-4981-a70c-55c3f15c1287\" ext:score=\"100\"><name>Madonna</name><sort-name>Madonna</sort-name><life-span begin=\"1958-08-16\"/></artist></artist-list></metadata>";

            @Mock
            public Future<HttpResponse> execute(HttpUriRequest request, FutureCallback<HttpResponse> callback) {
                try {
                    BasicHttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK");

                    BasicHttpEntity entity = new BasicHttpEntity();

                    byte[] content = XML.getBytes("UTF-8");

                    entity.setContentLength(content.length);
                    entity.setContentType("application/xml;charset=\"utf8\"");
                    entity.setContent(new ByteArrayInputStream(content));

                    response.setEntity(entity);


                    callback.completed(response);
                } catch (Exception ex) {
                    callback.failed(ex);
                }

                return null;
            }
        });

        XmlFetcher client = new XmlFetcher();

        client.fetch(new String[]{URL}, new XmlFetcher.Callback() {
            @Override
            public void completed(Map<String, Object> results) {
                Object result = results.get("http://musicbrainz.org/ws/1/artist?limit=1&query=madonna");

                assert result instanceof Document;

                Document document = (Document) result;

                NodeList nodeList = document.getElementsByTagName("name");

                assert null != nodeList;
                assert 1 == nodeList.getLength();

                Node node = nodeList.item(0);

                assert "Madonna".equals(node.getTextContent());
            }
        });
    }
}
