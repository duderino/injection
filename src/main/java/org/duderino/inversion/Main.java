package org.duderino.inversion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.TokenRewriteStream;

public class Main {
	private static final String EXTENSION = "inv";
	private static final boolean VERBOSE = false;
	private static final int OK = 0;
	private static final int BAD_INPUT = 1;
	private static final int INTERNAL_ERROR = 2;
	
	public static void main(String args[]) {
		try {		
			if (0 == args.length) {
				System.err.println("Reading from stdin, writing to stdout...");
				
				if (rewrite(System.in, System.out)) {
					System.exit(OK);
				}
				
				System.exit(BAD_INPUT);
			}
			
			for (String inputFileName : args) {
				File file = new File(inputFileName);
				
				InputStream inputStream = new FileInputStream(file);
				
				String path = file.getPath();
				
				int index = path.lastIndexOf('.');
				
				String baseFileName = path.substring(0, index);
				String extension = path.substring(index + 1);
				
				if (false == EXTENSION.equals(extension)) {
					System.err.println("Cannot handle " + inputFileName + ".  Only *." + EXTENSION + " files are supported");
					System.exit(BAD_INPUT);
				}
				
				String outputFileName = path.substring(0, index) + ".java";
				
				PrintStream outputStream = new PrintStream(new FileOutputStream(outputFileName));
				
				if (false == rewrite(inputStream, outputStream)) {
					System.exit(BAD_INPUT);
				}
				
				inputStream.close();
				outputStream.close();
			}
			
			System.exit(OK);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(INTERNAL_ERROR);
		}
	}
	
	private static boolean rewrite(InputStream inputStream, PrintStream outputStream) throws Exception {
		InversionLexer lexer = new InversionLexer(new ANTLRInputStream(inputStream));

		TokenRewriteStream tokens = new TokenRewriteStream(lexer);
		
		InversionParser parser = new InversionParser(tokens);

		parser.compilationUnit();

		if (parser.isOK()) {
			outputStream.print(tokens.toString());
			return true;
		}

		parser.printSummary(System.err, VERBOSE);

		return false;
	}
}
