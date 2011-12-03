package org.duderino.injection.jmockit._8;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * Demonstrate injecting a mock that decorates/delegates to the
 * underlying real dependency.
 */
public class ClassTest {
    private static class Count {
        private int value = 0;

        public void increment() {
            ++value;
        }

        public int total() {
            return value;
        }
    }

    @Test
    public void testIt() {
        final Count count = new Count();

        Mockit.setUpMock(Dependency.class, new Dependency() {
            public Dependency it;

            @Mock(reentrant = true)
            @Override
            public int generate() {
                count.increment();

                return it.generate();
            }
        });

        Class clazz = new Class();

        for (int i = 0; i < 10; ++i) {
            assert 999 * 2 == clazz.generate();
        }

        assert count.total() == 10;
    }
}
