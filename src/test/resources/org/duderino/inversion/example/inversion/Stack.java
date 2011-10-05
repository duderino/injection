package org.duderino.inversion.example.inversion;

/**
 * This example illustrates a possible built-in unit test functionality.
 * <p/>
 * Notice how assertions are used for both testing the class's internal state and external behavior.
 * <p/>
 * We would further bind unit tests to classes by breaking the build if any test fails.
 * <p/>
 * Test cases are interspersed with the methods they test.   Does this feel more natural than a
 * separate section?   Interspersed tests may aid developer understanding of the methods they test.
 * <p/>
 * Test cases could be incorporated into API documentation - if a test has the same
 * name as a method, it's source code could be included in the method's documentation.
 */
public class Stack<E> {
    private Object[] array;
    private int top = 0;

    beforeSuite {
        Stack<Integer> stack = new Stack<Integer>(3);
    }

    afterSuite {
    }

    beforeTest {
        stack.clear();
    }

    afterTest {
    }

    public Stack(int size) {
        array = new Object[size];
    }

    public int size() {
        return top;
    }

    test size {
        assert stack.size() == 0;

        stack.push(new Integer(1));

        assert stack.size() == 1;

        stack.push(new Integer(2));

        assert stack.size() == 2;

        stack.pop();

        assert stack.size() == 1;

        stack.pop();

        assert stack.size() == 0;
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

    test push expect IndexOutOfBoundsException
    {
        stack.push(new Integer(1));

        assert true;

        stack.push(new Integer(2));

        assert true;

        stack.push(new Integer(3));

        assert true;

        stack.push(new Integer(4));

        assert false;
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

    // this test method would be incorporated into the API documentation of both the push and the pop method

    test push pop {
        stack.push(new Integer(1));
        stack.push(new Integer(2));
        stack.push(new Integer(3));

        assert stack.pop().intValue() == 3;
        assert stack.pop().intValue() == 2;
        assert stack.pop().intValue() == 1;
    }

    public void clear() {
        top = 0;
    }

    test clear {
        stack.push(new Integer(1));

        stack.push(new Integer(2));

        assert stack.size() == 2;

        stack.clear();

        assert stack.size() == 0;

        assert stack.pop() == null;
    }
}
