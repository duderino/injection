package org.duderino.sugar;

import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;


import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.CommonTokenStream;

public abstract class TestBase {
	
	protected void parse(String fileName) throws Exception {
		Reader reader = new FileReader(fileName);
		
		SugarLexer lexer = new SugarLexer(new ANTLRReaderStream(reader));
		
		SugarParser parser = new SugarParser(new CommonTokenStream(lexer));
		
		parser.compilationUnit();
		
		if (parser.isOK()) {
			return;
		}
		
		StringWriter summary = new StringWriter();
		
		parser.printSummary(summary, true);
		
		throw new Exception(summary.toString());
	}
}
