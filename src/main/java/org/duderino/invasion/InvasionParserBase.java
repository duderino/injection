package org.duderino.invasion;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.TokenStream;

public class InvasionParserBase extends Parser {
	private List<Error> errors = new ArrayList<Error>();
	
	public static class Error {
		private final String message;
		private final String stackTrace;
		
		private Error(String message, String stackTrace) {
			this.message = message;
			this.stackTrace = stackTrace;
		}
		
		public String getMessage() {
			return message;
		}

		public String getStackTrace() {
			return stackTrace;
		}
		
		public String toString() {
			return message;
		}
	}

	public InvasionParserBase(TokenStream input) {
		super(input);
		
		System.err.println("Hiho");
	}
	
	public InvasionParserBase(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}
	
	/** Report a recognition problem.
	 *
	 *  This method sets errorRecovery to indicate the parser is recovering
	 *  not parsing.  Once in recovery mode, no errors are generated.
	 *  To get out of recovery mode, the parser must successfully match
	 *  a token (after a resync).  So it will go:
	 *
	 * 		1. error occurs
	 * 		2. enter recovery mode, report error
	 * 		3. consume until token found in resynch set
	 * 		4. try to resume parsing
	 * 		5. next match() will reset errorRecovery mode
	 *
	 *  If you override, make sure to update syntaxErrors if you care about that.
	 */
	@Override
	public void reportError(RecognitionException ex) {
		// if we've already reported an error and have not matched a token
		// yet successfully, don't report any errors.
		if ( state.errorRecovery ) {
			return;
		}
		
		state.syntaxErrors++; // don't count spurious
		state.errorRecovery = true;
		
		String header = getErrorHeader(ex);
		String message = getErrorMessage(ex, this.getTokenNames());
		
		StringWriter stackTrace = new StringWriter();
		ex.printStackTrace(new PrintWriter(stackTrace));
		
		errors.add(new Error(header + " " + message, stackTrace.toString()));
	}
	
	public boolean isOK() {
		return errors.isEmpty();
	}

	public List<Error> getErrors() {
		return Collections.unmodifiableList(errors);
	}
	
	public void printSummary(Writer writer, boolean verbose) throws IOException {
		if (isOK()) {
			writer.write("OK\n");
			return;
		}
		
		writer.write(errors.size() + " Errors\n");
		
		for (Error error : errors) {
			writer.write(error.getMessage());
			writer.write('\n');

			if (verbose) {
				writer.write(error.getStackTrace());
			}
		}
	}
	
	public void printSummary(PrintStream stream, boolean verbose) {
		if (isOK()) {
			stream.println("OK");
			return;
		}
		
		stream.println(errors.size() + " Errors");
		
		for (Error error : errors) {
			stream.println(error.getMessage());

			if (verbose) {
				stream.print(error.getStackTrace());
			}
		}
	}
	
	public void printSummary(boolean verbose) {
		if (isOK()) {
			printSummary(System.out, verbose);
		} else {
			printSummary(System.err, verbose);
		}
	}
	
	public void printSummary() {
		printSummary(false);
	}
}
