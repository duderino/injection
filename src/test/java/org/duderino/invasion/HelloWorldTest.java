package org.duderino.invasion;

import org.testng.annotations.Test;

import java.io.IOException;

@Test
public class HelloWorldTest extends TestBase {

    public void test() throws Exception {
        parse("src/test/resources/org/duderino/invasion/HelloWorld.inv");
    }

    public void badHelloWorld() throws Exception {
        try {
            parse("src/test/resources/org/duderino/invasion/BadHelloWorld.inv");
        } catch (Exception expected) {
            return;
        }

        assert false;
    }

    public void missingHelloWorld() throws Exception {
        try {
            parse("src/test/resources/org/duderino/invasion/MissingHelloWorld.inv");
        } catch (IOException expected) {
            return;
        }

        assert false;
    }

}
