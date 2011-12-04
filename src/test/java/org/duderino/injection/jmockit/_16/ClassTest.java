package org.duderino.injection.jmockit._16;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * Demonstrate associating assertions in hand-coded mocks or stubs with test cases in same thread
 */
public class ClassTest {
    @Test
    public void testIt() {
        Mockit.setUpMock(Dependency.class, new Dependency() {
            @Mock
            @Override
            public int generate() {
                // uncomment to intentionally break test: assert 1 == 2;

                return 123;
            }
        });

        Class clazz = new Class();

        assert 123 * 2 == clazz.generate();
    }
}
