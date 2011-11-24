package org.duderino.injection.jmockit._2;

import mockit.Mock;
import mockit.MockUp;
import org.testng.annotations.Test;

/**
 * Demonstrate mocking out a dependency with final/non-virtual methods.
 */
public class ClassTestAlternative {
    @Test
    public void testIt() {
        new MockUp<Dependency>() {
            @Mock
            public int generate() {
                return 123;
            }
        };


        Class clazz = new Class();

        assert 123 * 2 == clazz.generate();
    }
}
