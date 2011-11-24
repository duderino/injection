package org.duderino.injection.jmockit._4;

import mockit.Mock;
import mockit.MockClass;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * Demonstrate mocking out a dependency with a constructor.
 */
public class ClassTest {
    private static final int MAX = 1000;

    @MockClass(realClass = Dependency.class)
    public static class MockDependency {
        @Mock
        void $init(int max) {
            assert MAX == max;
        }

        @Mock
        public static int generate() {
            return 123;
        }
    }

    @Test
    public void testIt() {
        Mockit.setUpMock(Dependency.class, MockDependency.class);

        Class clazz = new Class(MAX);

        assert 123 * 2 == clazz.generate();
    }
}
