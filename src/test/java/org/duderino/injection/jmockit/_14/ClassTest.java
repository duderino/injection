package org.duderino.injection.jmockit._14;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * Demonstrate injecting async-friendly mocks/stubs.
 */
public class ClassTest {
    @Test
    public void testIt() {
        Mockit.setUpMock(Dependency.class, new Dependency() {
            @Mock
            @Override
            public void generate(Class clazz) {
                clazz.callback(123);
            }
        });

        Class clazz = new Class();

        assert 123 * 2 == clazz.generate();
    }
}
