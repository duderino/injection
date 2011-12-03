package org.duderino.injection.jmockit._7;


import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

public class ClassTest {
    @Test
    public void testOverride() {
        Mockit.setUpMock(SubDependency.class, new SubDependency() {
            @Mock
            public int generate() {
                return 123;
            }
        });

        Class clazz = new Class();

        assert 2 * 123 == clazz.generate();
        assert 2 * 333 == clazz.subGenerate();
        assert 2 * 999 == clazz.superGenerate();
    }

    @Test
    public void testOvershadow() {
        Mockit.setUpMock(SuperDependency.class, new SuperDependency() {
            @Mock
            public int generate() {
                return 123;
            }
        });

        Class clazz = new Class();

        assert 2 * 333 == clazz.generate();
        assert 2 * 333 == clazz.subGenerate();
        assert 2 * 999 == clazz.superGenerate();
    }

    @Test
    public void testSuper() {
        /* Fails at runtime with a: "Matching real methods not found for the following mocks of org.duderino.injection.jmockit._7.ClassTest$3"

        Mockit.setUpMock(SubDependency.class, new SubDependency() {
            @Mock
            public int superGenerate() {
                return 123;
            }
        }); */

        Mockit.setUpMock(SuperDependency.class, new SuperDependency() {
            @Mock
            public int superGenerate() {
                return 123;
            }
        });

        Class clazz = new Class();

        assert 2 * 333 == clazz.generate();
        assert 2 * 333 == clazz.subGenerate();
        assert 2 * 123 == clazz.superGenerate();
    }

    @Test
    public void testSub() {
        Mockit.setUpMock(SubDependency.class, new SubDependency() {
            @Mock
            public int subGenerate() {
                return 123;
            }
        });

        Class clazz = new Class();

        assert 2 * 333 == clazz.generate();
        assert 2 * 123 == clazz.subGenerate();
        assert 2 * 999 == clazz.superGenerate();
    }
}
