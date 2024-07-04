package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import implementation.MyStack;
import utilities.StackADT;
import java.util.NoSuchElementException;

class MyStackTests {

    private StackADT<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new MyStack<>();
    }

    @Test
    void testPush() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.size());
        assertEquals(2, stack.peek());
    }

    @Test
    void testPop() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertThrows(NoSuchElementException.class, () -> stack.pop());
    }

    @Test
    void testPeek() {
        stack.push(1);
        assertEquals(1, stack.peek());
        stack.push(2);
        assertEquals(2, stack.peek());
        stack.pop();
        assertEquals(1, stack.peek());
        stack.pop();
        assertThrows(NoSuchElementException.class, () -> stack.peek());
    }

    @Test
    void testClear() {
        stack.push(1);
        stack.push(2);
        stack.clear();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testIsEmpty() {
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testToArray() {
        stack.push(1);
        stack.push(2);
        Object[] array = stack.toArray();
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
    }

    @Test
    void testToArrayWithGenericArray() {
        stack.push(1);
        stack.push(2);
        Integer[] array = new Integer[2];
        array = stack.toArray(array);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
    }

    @Test
    void testContains() {
        stack.push(1);
        stack.push(2);
        assertTrue(stack.contains(1));
        assertFalse(stack.contains(3));
    }

    @Test
    void testSearch() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(1, stack.search(3));
        assertEquals(2, stack.search(2));
        assertEquals(3, stack.search(1));
        assertEquals(-1, stack.search(4));
    }

    @Test
    void testEquals() {
        StackADT<Integer> anotherStack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        anotherStack.push(1);
        anotherStack.push(2);
        assertTrue(stack.equals(anotherStack));
        anotherStack.pop();
        assertFalse(stack.equals(anotherStack));
    }

    @Test
    void testSize() {
        assertEquals(0, stack.size());
        stack.push(1);
        assertEquals(1, stack.size());
        stack.push(2);
        assertEquals(2, stack.size());
        stack.pop();
        assertEquals(1, stack.size());
    }
}
