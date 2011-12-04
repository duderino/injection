package org.duderino.injection.jmockit._13;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigurationTest {
    @Test
    public void test() throws Exception {
        final String xml = "<settings><setting><key>foo</key><value>bar</value></setting></settings>";

        Mockit.setUpMock(FileInputStream.class, new InputStream() {
            private byte[] bytes = xml.getBytes();
            private int index = 0;

            @Mock
            void $init(String fileName) {
            }

            @Mock
            public int read() throws IOException {
                if (index >= bytes.length) {
                    return 0;
                }

                return bytes[index++];
            }

            @Mock
            public int read(byte[] out) throws IOException {
                for (int i = 0; i < out.length; ++i) {
                    int result = read();

                    if (0 == result) {
                        return i;
                    }

                    out[i] = bytes[i];
                }

                return out.length;
            }
        });

        Configuration configuration = new Configuration("README");

        assert "bar".equals(configuration.get("foo"));
    }
}
