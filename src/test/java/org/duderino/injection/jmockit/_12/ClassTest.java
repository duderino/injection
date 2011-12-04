package org.duderino.injection.jmockit._12;

import mockit.Mock;
import mockit.MockClass;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * FAILS!  Demonstrate mocking out any implementation of an interface.
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
