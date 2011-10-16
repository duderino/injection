package org.duderino.invasion;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.TokenRewriteStream;

import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;

public abstract class TestBase {

    protected void parse(String fileName) throws Exception {
        Reader reader = new FileReader(fileName);

        InvasionLexer lexer = new InvasionLexer(new ANTLRReaderStream(reader));

        InvasionParser parser = new InvasionParser(new TokenRewriteStream(lexer));

        parser.compilationUnit();

        if (parser.isOK()) {
            return;
        }

        StringWriter summary = new StringWriter();

        parser.printSummary(summary, true);

        throw new Exception(summary.toString());
    }
}
