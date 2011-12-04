package org.duderino.injection.jmockit._13;

import java.io.FileInputStream;

public class Class {
    public int generate() throws Exception {
        FileInputStream stream = new FileInputStream("foo");

        byte[] bytes = new byte[3];

        stream.read(bytes);

        String string = new String(bytes, "ASCII");

        return 2 * Integer.parseInt(string);
    }
}
