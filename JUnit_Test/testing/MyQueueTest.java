package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import implementation.MyQueue;
import utilities.QueueADT;
import utilities.EmptyQueueException;
import utilities.Iterator;
import java.util.NoSuchElementException;

public class MyQueueTest {

    private QueueADT<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new MyQueue<>();
    }

    @Test
    public void testEnqueue() throws EmptyQueueException {
        queue.enqueue(1);
        assertEquals(1, queue.size(), "Queue size should be 1 after enqueue");
        assertEquals(1, queue.peek(), "Element at front should be 1");
    }

    @Test
    public void testDequeue() throws EmptyQueueException {
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue(), "Dequeued element should be 1");
        assertEquals(1, queue.size(), "Queue size should be 1 after dequeue");
        assertEquals(2, queue.peek(), "Element at front should be 2");

        queue.dequeue();
        assertThrows(EmptyQueueException.class, () -> queue.dequeue(), "Should throw EmptyQueueException if queue is empty");
    }

    @Test
    public void testPeek() throws EmptyQueueException {
        queue.enqueue(1);
        assertEquals(1, queue.peek(), "Element at front should be 1");

        queue.dequeue();
        assertThrows(EmptyQueueException.class, () -> queue.peek(), "Should throw EmptyQueueException if queue is empty");
    }

    @Test
    public void testDequeueAll() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.dequeueAll();
        assertTrue(queue.isEmpty(), "Queue should be empty after dequeueAll");
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty(), "Queue should be empty initially");
        queue.enqueue(1);
        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue");
    }

    @Test
    public void testIterator() {
        queue.enqueue(1);
        queue.enqueue(2);
        Iterator<Integer> iterator = queue.iterator();

        assertTrue(iterator.hasNext(), "Iterator should have next element");
        assertEquals(1, iterator.next(), "Next element should be 1");
        assertTrue(iterator.hasNext(), "Iterator should have next element");
        assertEquals(2, iterator.next(), "Next element should be 2");
        assertFalse(iterator.hasNext(), "Iterator should not have next element");

        assertThrows(NoSuchElementException.class, () -> iterator.next(), "Should throw NoSuchElementException if no more elements");
    }

    @Test
    public void testToArray() {
        queue.enqueue(1);
        queue.enqueue(2);
        Object[] result = queue.toArray();

        assertArrayEquals(new Object[]{1, 2}, result, "Array should contain [1, 2]");
    }

    @Test
    public void testToArrayWithParameter() {
        queue.enqueue(1);
        queue.enqueue(2);
        Integer[] array = new Integer[2];
        Integer[] result = queue.toArray(array);

        assertArrayEquals(new Integer[]{1, 2}, result, "Array should contain [1, 2]");

        assertThrows(NullPointerException.class, () -> queue.toArray((Integer[]) null), "Should throw NullPointerException for null array");
    }

    @Test
    public void testEquals() throws EmptyQueueException {
        QueueADT<Integer> anotherQueue = new MyQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        anotherQueue.enqueue(1);
        anotherQueue.enqueue(2);

        assertTrue(queue.equals(anotherQueue), "Queues should be equal");
        anotherQueue.dequeue();
        assertFalse(queue.equals(anotherQueue), "Queues should not be equal after modification");
    }

    @Test
    public void testIsFull() {
        assertFalse(queue.isFull(), "Queue should not be full (always false for linked list implementation)");
    }

    @Test
    public void testSize() {
        assertEquals(0, queue.size(), "Initial size should be 0");
        queue.enqueue(1);
        assertEquals(1, queue.size(), "Size should be 1 after enqueue");
    }
}
