package org.duderino.injection.jmockit;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

@Test
public class MusicianDatabaseTest {

    public void functionalTest() throws Exception {
        MusicianDatabase musicianDatabase = new MusicianDatabase();

        MusicianDatabase.Info info = musicianDatabase.fetch("madonna");

        assert "1958-08-16".equals(info.getDateOfBirth());
        assert null != info.getNewsHeadline() && 0 < info.getNewsHeadline().length();
        assert null != info.getBookTitle() && 0 < info.getBookTitle().length();
    }

    public void unitTest() throws Exception {
        Mockit.setUpMock(XmlFetcher.class, new XmlFetcher() {
            @Mock
            public void fetch(final String[] urls, final Callback callback) {
                Map<String, String> results = new HashMap<String, String>();

                results.put("http://musicbrainz.org/ws/1/artist?limit=1&query=madonna", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><metadata xmlns=\"http://musicbrainz.org/ns/mmd-1.0#\" xmlns:ext=\"http://musicbrainz.org/ns/ext-1.0#\"><artist-list offset=\"0\" count=\"13\"><artist type=\"Person\" id=\"79239441-bfd5-4981-a70c-55c3f15c1287\" ext:score=\"100\"><name>Madonna</name><sort-name>Madonna</sort-name><life-span begin=\"1958-08-16\"/></artist></artist-list></metadata>");
                results.put("http://api.usatoday.com/open/articles?count=1&api_key=ptm7j7nr5qqbry5uze5vgnvn&tag=madonna", "<?xml version=\"1.0\" encoding=\"utf-8\"?> <!--BUILD-DATE 10/12/2011 7:13 PM EDT--><rss version=\"2.0\" xmlns:feedburner=\"http://rssnamespace.org/feedburner/ext/1.0\" xmlns:cf=\"http://www.microsoft.com/schemas/rss/core/2005\">  <channel>    <cf:treatAs>list</cf:treatAs>    <title>Tagged </title>    <link>http://apidata.usatoday.com?kjnd=IQjxkWOoDv5N0ClRonidL%2BUBhwm9de05TlznPpXDSJVIet6LgNRH1Joe%2B9Xrgrs5-98c91ab3-c0d0-485f-af46-dc784a660014_Qw1mE0qYNdqK2rhwx4uZFmf1wtmHM8mufNrUrKc8EWjOCndv4TpXfbLlOASn0aof</link>    <description>http://apidata.usatoday.com?kjnd=VnkcP1gTTcavo7evVuWGBXwzwjLTpZSbAdc45lgWirwft891uP8F0W4d8dMbcLp2-98c91ab3-c0d0-485f-af46-dc784a660014_tobxsELjSi6Zsfi%2BJh4Ftd8fFvx5mMtU98isXHtEIoa95vM14TL4CLctWK3fy96Z</description>    <language>en-us</language>    <copyright>Copyright 2011, USATODAY.com, USA TODAY</copyright>    <lastBuildDate>Wed, 12 Oct 2011 23:13:34 GMT</lastBuildDate>    <image>      <title>USATODAY.com</title>      <width>135</width>      <height>20</height>      <link>http://apidata.usatoday.com?kjnd=wJW%2BCj9F7f7r2bElKg1seaKHZ32bsD%2BYQO%2FxXe4MRVgLdqYxlT5RjUz%2B4A75WOu3-98c91ab3-c0d0-485f-af46-dc784a660014_b02m9JYgA%2BTcHpmthOezxO9GaJJrvHLgqT4SHpbR6%2B4OQBEK%2B7ZFxxfEhUk9B3tT</link>      <url>http://i.usatoday.net/_common/_images/usatodaycom_135x20.gif</url>    </image>    <cf:listinfo>      <cf:sort element=\"pubDate\" data-type=\"date\" label=\"Date/Time Order\" default=\"false\" />      <cf:sort element=\"title\" data-type=\"text\" label=\"Title Order\" default=\"false\" />    </cf:listinfo>    <item>      <title>Madonna to perform at Super Bowl halftime show?</title>      <link>http://apidata.usatoday.com/communities/entertainment/post/2011/10/madonna-to-perform-at-super-bowl-halftime-show/1?kjnd=8C3iK2xNa2ENJnMNOZlbFMrQNnRyBRse1wNJLGuxl3Hr1HJYrElcEtj42euVYrB9-98c91ab3-c0d0-485f-af46-dc784a660014_%2Fvx56o0sQ5eVI5OvRnUWl4vHox%2Fj0Lz9o7iZKpCjhDT%2FnMOsLvGLtP%2BsWAEuFRpI</link>      <pubDate>Mon, 03 Oct 2011 15:33:00 GMT</pubDate>      <description>That's the buzz o' the day.</description>      <guid isPermalink=\"true\">http://apidata.usatoday.com/communities/entertainment/post/2011/10/madonna-to-perform-at-super-bowl-halftime-show/1?kjnd=uecHIfbZPcts718K4jmW1NkFMqVXQUEJpQA7wsoSVmsys3zrU0u8f%2Bpxoe9LV537-98c91ab3-c0d0-485f-af46-dc784a660014_31IUC1MXkGeJYyTxzQBdHh7HJ9qeom1qr%2BljDGRqNxFfQbtu6IJdQfoMLhao6sPc</guid>    </item>  </channel></rss>");
                results.put("http://z3950.loc.gov:7090/voyager?version=1.1&operation=searchRetrieve&maximumRecords=1&recordSchema=dc&query=madonna", "<?xml version=\"1.0\" encoding=\"utf-8\"?> <zs:searchRetrieveResponse xmlns:zs=\"http://www.loc.gov/zing/srw/\"><zs:version>1.1</zs:version><zs:numberOfRecords>2197</zs:numberOfRecords><zs:records><zs:record><zs:recordSchema>info:srw/schema/1/dc-v1.1</zs:recordSchema><zs:recordPacking>xml</zs:recordPacking><zs:recordData><srw_dc:dc xmlns:srw_dc=\"info:srw/schema/1/dc-schema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://purl.org/dc/elements/1.1/\" xsi:schemaLocation=\"info:srw/schema/1/dc-schema http://www.loc.gov/standards/sru/resources/dc-schema.xsd\">  <title>The 100 most influential musicians of all time /</title>  <creator>Gorlinski, Gini. edt</creator>  <type>text</type>  <publisher>New York : Britannica Educational Pub.,</publisher>  <date>2010.</date>  <language>eng</language>  <description>Published in association with Rosen Educational Services.</description>  <description>Includes bibliographical references (p. 349-350) and index.</description>  <description>Introduction -- Guido d'Arezzo -- Josquin des Prez -- Antonio Vivaldi -- George Frideric Handel -- Johann Sebastian Bach -- Joseph Haydn -- Wolfgang Amadeus Mozart -- Ludwig van Beethoven -- Franz Schubert -- Felix Medelssohn -- Fre&#x301;de&#x301;ric Chopin -- Franz Liszt -- Richard Wagner -- Giuseppe Verdi -- Johannes Brahms -- Sir W.S. Gilbert and Sir Arthur Sullivan --  Pyotr Ilyich Tchaikovsky -- Giacomo Puccini -- Gustav Mahler -- Claude Debussy -- Sergey Rachmaninoff -- W.C. Handy -- Arnold Schoenberg -- Charles Ives -- Be&#x301;la Barto&#x301;k -- Igor Stravinsky -- Leadbelly -- Carter Family -- Sergey Prokofiev -- Cole Porter -- Jimmie Rodgers -- Fletcher Henderson -- Bessie Smith -- George Gershwin -- Duke Ellington -- Kurt Weill -- Aaron Copland -- Louis Armstrong -- Umm Kulthu&#x304;m -- Count Basie -- Dmitry Shostakovich -- Bill Monroe -- Mahalia Jackson -- Robert Johnson -- Woody Guthrie -- John Cage -- Muddy Waters -- Billie Holiday -- Frank Sinatra -- Edith Piaf -- Nat King Cole -- Ella Fitzgerald -- Leonard Bernstein -- Pete Seeger -- Ravi Shankar -- Charlie Parker -- Tito Puente -- Hank Williams -- Maria Callas -- Clifton Chenier -- B.B. King -- Miles Davis -- Chuck Berry -- Antonio Carlos Jobim -- Ray Charles -- Patsy Cline -- James Brown -- Elvis Presley -- Luciano Pavarotti -- Buddy Holly -- The Rolling Stones -- Philip Glass -- Smokey Robinson and the Miracles -- Parliament-Funkadelic -- The Beatles -- Joan Baez -- Pla&#x301;cido Domingo -- The Beach Boys -- Bob Dylan -- Aretha Franklin -- Jimi Hendrix -- Joni Mitchell -- Led Zeppelin -- The Who -- Bob Marley -- Eric Clapton -- King Sunny Ade -- David Bowie -- Nusrat Fateh Ali Khan -- Bruce Springsteen -- Stevie Wonder -- The Sex Pistols -- Prince -- Madonna -- Michael Jackson -- Public Enemy -- U2 -- Nirvana -- Radiohead -- Jay-Z.</description>  <subject>Musicians--Biography.</subject>  <subject>Musicians--Encyclopedias.</subject>  <identifier>URN:ISBN:9781615300068</identifier>  <identifier>URN:ISBN:1615300066</identifier></srw_dc:dc></zs:recordData><zs:recordPosition>1</zs:recordPosition></zs:record></zs:records></zs:searchRetrieveResponse>");

                Map<String, Object> map = new HashMap<String, Object>();

                for (String url : urls) {
                    try {
                        DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

                        Document document = docBuilder.parse(new ByteArrayInputStream(results.get(url).getBytes("UTF-8")));

                        map.put(url, document);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        assert false;
                    }
                }

                callback.completed(map);
            }
        });

        MusicianDatabase musicianDatabase = new MusicianDatabase();

        MusicianDatabase.Info info = musicianDatabase.fetch("madonna");

        assert "1958-08-16".equals(info.getDateOfBirth());
        assert "Madonna to perform at Super Bowl halftime show?".equals(info.getNewsHeadline());
        assert "The 100 most influential musicians of all time /".equals(info.getBookTitle());
    }
}
