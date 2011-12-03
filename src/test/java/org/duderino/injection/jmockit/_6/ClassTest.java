package org.duderino.injection.jmockit._6;

import mockit.Mock;
import org.testng.annotations.Test;

/**
 * Demonstrate mocking out a private static inner class dependency.
 */
public class ClassTest {
    // Does not compile:  @MockClass(realClass = Class.Dependency.class)
    public class MockDependency {
        @Mock
        public int generate() {
            return 123;
        }
    }

    @Test
    public void testIt() {
        // Does not compile:  Mockit.setUpMock(Class.Dependency.class, MockDependency.class);

        Class clazz = new Class();

        // Does not pass:  assert 123 * 2 == clazz.generate();
    }
}
