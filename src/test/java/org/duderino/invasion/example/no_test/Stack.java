package org.duderino.invasion.example.no_test;

public class Stack<E> {
    private Object[] array;
    private int top = 0;

    public Stack(int size) {
        array = new Object[size];
    }

    public int size() {
        return top;
    }

    public void push(E element) {
        if (null == element) {
            return;
        }

        if (size() == array.length - 1) {
            throw new IndexOutOfBoundsException("I'm full, go away");
        }

        array[top++] = element;

        assert top < array.length;
        assert 0 <= top;
    }

    public E pop() {
        if (0 == size()) {
            return null;
        }

        E object = (E) array[top--];

        assert top < array.length;
        assert 0 <= top;

        return object;
    }

    public void clear() {
        top = 0;
    }
}
