package org.duderino.injection.jmockit;

public class ConsumerPool {
    private static final int THREADS = 3;
    private SharedQueue<Integer> queue = new SharedQueue<Integer>();
    private Thread[] threads;
    private volatile boolean stopSignalled = false;

    /*private -- private inner classes cannot be mocked out because the test cannot access the .class object */
    class Consumer extends Thread {
        @Override
        public void run() {
            try {
                while (stopSignalled == false) {
                    Integer integer = queue.take();
                }
            } catch (Throwable ex) {
                ex.printStackTrace();
                return;
            }
        }
    }

    public ConsumerPool() {
        threads = new Thread[THREADS];

        for (int i = 0; i < threads.length; ++i) {
            /* bad - anonymous inner classes cannot be mocked by jmockit, I think.
            threads[i] = new Thread() {
                @Override
                public void run() {
                    try {
                        while (stopSignalled == false) {
                            Integer integer = queue.take();
                        }
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
            }; */

            threads[i] = new Consumer();
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stop() throws InterruptedException {
        stopSignalled = true;

        for (Thread thread : threads) {
            queue.put(new Integer(999));
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    public void put(Integer integer) {
        queue.put(integer);
    }

}
