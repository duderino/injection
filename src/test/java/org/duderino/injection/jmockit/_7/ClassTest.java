package org.duderino.injection.jmockit._7;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrate injecting a mock that decorates/delegates to the
 * underlying real dependency.
 */
public class ClassTest {
    @Test
    public void testIt() {
        final List<Integer> calls = new ArrayList<Integer>();

        Mockit.setUpMock(Dependency.class, new Dependency() {
            //private int numCalls = 0;
            public Dependency it;

            @Mock(reentrant = true)
            @Override
            public int generate() {
                int result = it.generate();

                calls.add(new Integer(result));

                return result;
            }
        });

        Class clazz = new Class();

        for (int i = 0; i < 10; ++i) {
            assert 123 * 2 == clazz.generate();
        }

        assert calls.size() == 10;
    }
}
