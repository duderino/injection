package org.duderino.sugar;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;

import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.duderino.sugar.JavaLexer;
import org.duderino.sugar.JavaParser;

public abstract class TestBase {
	
	protected void parse(String fileName) throws Exception {
		Reader reader = new FileReader(fileName);
		
		JavaLexer lexer = new JavaLexer(new ANTLRReaderStream(reader));
		
		JavaParser parser = new JavaParser(new CommonTokenStream(lexer));
		
		parser.compilationUnit();
		
		if (parser.isOK()) {
			return;
		}
		
		StringWriter summary = new StringWriter();
		
		parser.printSummary(summary, true);
		
		throw new Exception(summary.toString());
	}
}
