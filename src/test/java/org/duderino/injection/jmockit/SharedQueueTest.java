package org.duderino.injection.jmockit;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class SharedQueueTest {

    @Test
    public void test() throws InterruptedException {
        final SharedQueue<Integer> queue = new SharedQueue<Integer>();
        final Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        final int items = 30;
        final int numThreads = 3;

        for (int i = 0; i < items; ++i) {
            map.put(new Integer(i), Boolean.FALSE);
        }

        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < threads.length; ++i) {
            threads[i] = new Thread() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i < items / numThreads; ++i) {
                            Integer integer = queue.take();

                            synchronized (map) {
                                Assert.assertEquals(true, map.containsKey(integer));
                                Assert.assertEquals(Boolean.FALSE, map.get(integer));

                                map.put(integer, Boolean.TRUE);
                            }
                        }
                    } catch (Throwable ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
            };

            threads[i].start();
        }

        for (Integer integer : map.keySet()) {
            queue.put(integer);
        }

        for (int i = 0; i < threads.length; ++i) {
            threads[i].join(1000 * 10);
        }

        for (Map.Entry<Integer, Boolean> entry : map.entrySet()) {
            Assert.assertEquals(Boolean.TRUE, entry.getValue());
        }
    }
}
