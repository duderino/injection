package org.duderino.injection.jmockit._6;

import mockit.Mock;
import mockit.MockClass;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * Demonstrate mocking out a private static inner class dependency.
 */
public class ClassTest {
    @MockClass(realClass = Class.Dependency.class)
    public class MockDependency {
        @Mock
        public int generate() {
            return 123;
        }
    }

    @Test
    public void testIt() {
        Mockit.setUpMock(Class.Dependency.class, MockDependency.class);

        Class clazz = new Class();

        assert 123 * 2 == clazz.generate();
    }
}
