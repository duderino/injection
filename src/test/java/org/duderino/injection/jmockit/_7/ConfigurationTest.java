package org.duderino.injection.jmockit._7;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigurationTest {
    @Test
    public void testCheck() throws Exception {
        final String xml = "<settings><setting><key>foo</key><value>bar</value></setting></settings>";

        Mockit.setUpMock(FileInputStream.class, new InputStream() {
            private byte[] bytes = xml.getBytes("UTF-8");
            private int index = 0;

            @Mock
            public int read() throws IOException {
                if (index >= bytes.length) {
                    return 0;
                }

                return bytes[index++];
            }
        });

        Configuration configuration = new Configuration("README");

        assert "bar".equals(configuration.get("foo"));
    }
}
