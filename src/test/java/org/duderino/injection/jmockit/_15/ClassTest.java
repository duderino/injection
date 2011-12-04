package org.duderino.injection.jmockit._15;

import mockit.Mock;
import mockit.Mockit;
import org.testng.annotations.Test;

/**
 * Demonstrate thread-safety of mocks and injection mechanism.
 */
public class ClassTest implements Runnable {
    @Override
    public void run() {
        long magicNumber = Thread.currentThread().getId() + 123;

        Mockit.setUpMock(Dependency.class, new Dependency() {
            @Mock
            @Override
            public long generate() {
                return Thread.currentThread().getId() + 123;
            }
        });

        Class clazz = new Class();

        assert 2 * magicNumber == clazz.generate();
    }

    @Test
    public void testIt() throws InterruptedException {
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread(this);
        }

        for (int i = 0; i < threads.length; ++i) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; ++i) {
            threads[i].join();
        }
    }
}
