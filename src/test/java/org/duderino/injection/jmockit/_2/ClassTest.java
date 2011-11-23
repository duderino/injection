package org.duderino.injection.jmockit._2;

import mockit.Mock;
import mockit.MockClass;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * Demonstrate mocking out a dependency with final/non-virtual methods.
 */
public class ClassTest {
    @MockClass(realClass = Dependency.class)
    public static class MockDependency {
        @Mock
        public int generate() {
            return 123;
        }
    }

    @Test
    public void testIt() {
        Mockit.setUpMock(Dependency.class, MockDependency.class);

        Class clazz = new Class();

        assert 123 * 2 == clazz.generate();
    }
}
