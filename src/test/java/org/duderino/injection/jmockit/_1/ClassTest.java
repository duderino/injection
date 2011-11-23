package org.duderino.injection.jmockit._1;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * Demonstrate injecting a dependency into a class without modifying its public API.
 */
public class ClassTest {
    @Test
    public void testIt() {
        Mockit.setUpMock(Dependency.class, new Dependency() {
            @Mock
            @Override
            public int generate() {
                return 123;
            }
        });

        Class clazz = new Class();

        assert 123 * 2 == clazz.generate();
    }
}
