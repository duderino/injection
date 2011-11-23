package org.duderino.injection.jmockit;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class SharedQueue<E> {
    private LinkedList<E> list = new LinkedList<E>();
    private Semaphore semaphore = new Semaphore(0);

    public SharedQueue() {
    }

    public void put(E element) {
        synchronized (list) {
            list.addLast(element);
        }

        semaphore.release();
    }

    public E take() throws InterruptedException {
        semaphore.acquire();

        synchronized (list) {
            return list.removeFirst();
        }
    }
}
