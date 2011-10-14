package org.duderino.inversion.example;

import org.apache.http.nio.reactor.IOReactorException;
import org.w3c.dom.Document;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class MusicianInfo {
    private AsyncParallelHttpClient client;

    public MusicianInfo(AsyncParallelHttpClient client) {
        this.client = client;
    }

    public MusicianInfo() {
        client = new AsyncParallelHttpClient();
    }

    public static class Info {
        private String dateOfBirth;
        private String bookTitle;
        private String newsHeadline;

        public Info() {
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public String getBookTitle() {
            return bookTitle;
        }

        public String getNewsHeadline() {
            return newsHeadline;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public void setBookTitle(String bookTitle) {
            this.bookTitle = bookTitle;
        }

        public void setNewsHeadline(String newsHeadline) {
            this.newsHeadline = newsHeadline;
        }
    }

    public Info fetchNews(String name) throws IOReactorException {
        final String[] urls = {
                "http://musicbrainz.org/ws/1/artist?limit=1&query=" + name,
                "http://api.usatoday.com/open/articles?count=1&api_key=ptm7j7nr5qqbry5uze5vgnvn&tag=" + name,
                "http://z3950.loc.gov:7090/voyager?version=1.1&operation=searchRetrieve&maximumRecords=1&recordSchema=dc&query=" + name
        };

        final CountDownLatch latch = new CountDownLatch(1);
        final Info info = new Info();

        client.fetch(urls, new AsyncParallelHttpClient.Callback() {
            public void completed(Map<String, Object> results) {
                for (Map.Entry<String, Object> entry : results.entrySet()) {
                    assert null != entry.getValue();

                    if (entry.getValue() instanceof Throwable) {
                        Throwable t = (Throwable) entry.getValue();

                        if (urls[0].equals(entry.getKey())) {
                            info.setDateOfBirth(t.getMessage());
                        } else if (urls[1].equals(entry.getKey())) {
                            info.setBookTitle(t.getMessage());
                        } else if (urls[2].equals(entry.getKey())) {
                            info.setNewsHeadline(t.getMessage());
                        }

                        continue;
                    }

                    assert entry.getValue() instanceof Document;

                    Document document = (Document) entry.getValue();

                    XPathFactory factory = XPathFactory.newInstance();
                    XPath xPath = factory.newXPath();

                    if (urls[0].equals(entry.getKey())) {
                        try {
                            info.setDateOfBirth((String) xPath.evaluate("//metadata/artist-list/artist/life-span/@begin", document, XPathConstants.STRING));
                        } catch (Exception ex) {
                            info.setDateOfBirth(ex.getMessage());
                        }
                    } else if (urls[1].equals(entry.getKey())) {
                        try {
                            info.setNewsHeadline((String) xPath.evaluate("//rss/channel/item/title", document, XPathConstants.STRING));
                        } catch (Exception ex) {
                            info.setNewsHeadline((String) ex.getMessage());
                        }
                    } else if (urls[2].equals(entry.getKey())) {
                        try {
                            info.setBookTitle((String) xPath.evaluate("//searchRetrieveResponse/records/record/recordData/dc/title", document, XPathConstants.STRING));
                        } catch (Exception ex) {
                            info.setBookTitle((String) ex.getMessage());
                        }
                    }
                }

                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException ex) {
            if (null == info.getBookTitle()) {
                info.setBookTitle(ex.getMessage());
            }

            if (null == info.getDateOfBirth()) {
                info.setDateOfBirth(ex.getMessage());
            }

            if (null == info.getNewsHeadline()) {
                info.setNewsHeadline(ex.getMessage());
            }
        }

        return info;
    }
}
