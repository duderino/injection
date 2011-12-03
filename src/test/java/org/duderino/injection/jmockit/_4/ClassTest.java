package org.duderino.injection.jmockit._4;

import mockit.Mock;
import mockit.MockClass;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * Demonstrate mocking out a dependency with a constructor.
 */
public class ClassTest {
    @MockClass(realClass = Dependency.class)
    public static class MockDependency {
        @Mock
        void $init(int value) {
            assert 999 == value;
        }

        @Mock
        public static int generate() {
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

