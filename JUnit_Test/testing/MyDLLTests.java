package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import implementation.MyDLL;

class MyDLLTests {

    private MyDLL<Integer> list;

    @BeforeEach
    void setUp() {
        list = new MyDLL<>();
    }

    @Test
    void testAdd() {
        assertTrue(list.add(1));
        assertTrue(list.add(2));
        assertEquals(2, list.size());
    }

    @Test
    void testAddAtIndex() {
        list.add(0, 1);
        list.add(1, 2);
        list.add(1, 3); // List should be [1, 3, 2]
        assertEquals(3, list.size());
        assertEquals(3, list.get(1));
    }

    @Test
    void testGet() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.remove(1));
        assertEquals(1, list.size());
        assertEquals(1, list.get(0));
    }

    @Test
    void testRemoveByElement() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.remove((Integer) 2));
        assertEquals(1, list.size());
        assertNull(list.remove((Integer) 3)); // Element not in the list
    }

    @Test
    void testSet() {
        list.add(1);
        list.add(2);
        assertEquals(2, list.set(1, 3));
        assertEquals(3, list.get(1));
    }

    @Test
    void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    void testContains() {
        list.add(1);
        list.add(2);
        assertTrue(list.contains(1));
        assertFalse(list.contains(3));
    }

    @Test
    void testToArray() {
        list.add(1);
        list.add(2);
        Object[] array = list.toArray();
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
    }

    @Test
    void testToArrayWithGenericArray() {
        list.add(1);
        list.add(2);
        Integer[] array = new Integer[2];
        array = list.toArray(array);
        assertEquals(1, array[0]);
        assertEquals(2, array[1]);
    }

    @Test
    void testIterator() {
        list.add(1);
        list.add(2);
        utilities.Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
