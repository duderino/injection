package org.duderino.sugar;

import java.io.IOException;

import org.duderino.sugar.TestBase;
import org.testng.annotations.Test;

@Test
public class HelloWorldTest extends TestBase {
 
  public void test() throws Exception { 	  
	  parse("src/test/resources/org/duderino/sugar/HelloWorld.java");
  }
  
  public void badHelloWorld() throws Exception {
	  try {
		  parse("src/test/resources/org/duderino/sugar/BadHelloWorld.java");
	  } catch (Exception expected) {
		  return;
	  }
	  
	  assert false;
  }
  
  public void missingHelloWorld() throws Exception {
	  try {
		  parse("src/test/resources/org/duderino/sugar/MissingHelloWorld.java");
	  } catch (IOException expected) {
		  return;
	  }
	  
	  assert false;
  }
  
}
