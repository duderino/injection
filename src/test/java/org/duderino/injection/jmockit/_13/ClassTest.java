package org.duderino.injection.jmockit._13;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassTest {
    @Test
    public void test() throws Exception {
        Mockit.setUpMock(FileInputStream.class, new InputStream() {
            private byte[] bytes = new byte[]{0x31, 0x32, 0x33};
            private int index = 0;

            @Mock
            void $init(String fileName) {
                assert "foo".equals(fileName);
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

        Class clazz = new Class();

        assert 2 * 123 == clazz.generate();
    }
}
