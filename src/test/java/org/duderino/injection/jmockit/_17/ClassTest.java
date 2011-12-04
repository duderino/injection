package org.duderino.injection.jmockit._17;

import org.testng.annotations.Test;

/**
 * Demonstrate associating assertions in hand-coded mocks or stubs with test cases in different threads
 */
public class ClassTest implements Runnable {
    public void run() {
        assert 1 == 2;
    }

    @Test
    public void test() throws InterruptedException {
        Thread[] threads = new Thread[10];

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
