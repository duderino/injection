package org.duderino.injection.jmockit;

import mockit.Mock;
import mockit.Mockit;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Test
public class ConsumerPoolTest {
    private static final int ITEMS = 30;
    private static final int THREADS = 3;


    public static class MockConsumer {
        public static Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();

        @Mock
        public void run() {
            System.err.println(Thread.currentThread().toString() + ": Hello world!");

            try {
                for (int i = 0; i < ITEMS / THREADS; ++i) {
                    // bad!  cannot access members of the enclosing ConsumerPool class
                    /*Integer integer = queue.take();

                    synchronized (map) {
                        Assert.assertEquals(true, map.containsKey(integer));
                        Assert.assertEquals(Boolean.FALSE, map.get(integer));

                        map.put(integer, Boolean.TRUE);
                    }   */
                }
            } catch (Throwable ex) {
                ex.printStackTrace();
                return;
            }
        }
    }

    @Test
    public void test() throws InterruptedException {
        Mockit.setUpMock(ConsumerPool.Consumer.class, MockConsumer.class);

        for (int i = 0; i < ITEMS; ++i) {
            MockConsumer.map.put(new Integer(i), Boolean.FALSE);
        }

        ConsumerPool pool = new ConsumerPool();

        pool.start();

        for (Integer integer : MockConsumer.map.keySet()) {
            pool.put(integer);
        }

        pool.stop();

        for (Map.Entry<Integer, Boolean> entry : MockConsumer.map.entrySet()) {
            Assert.assertEquals(Boolean.TRUE, entry.getValue());
        }
    }
}
