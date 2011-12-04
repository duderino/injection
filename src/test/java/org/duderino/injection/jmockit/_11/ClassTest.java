package org.duderino.injection.jmockit._11;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * Demonstrate mocking out a dependency with final/non-virtual methods.
 */
public class ClassTest {
    @Test
    public void testIt() {
        Mockit.setUpMock(Dependency.class, new Dependency() {
            public Dependency it;
            private int calls = 0;

            @Mock(reentrant = true)
            public int generate() {
                return ++calls == 2 ? 123 : it.generate();
            }
        });

        Class clazz = new Class();

        assert 999 + (2 * 123) == clazz.generate();
    }
}
