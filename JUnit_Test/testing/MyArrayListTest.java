package testing;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import implementation.MyArrayList;
import utilities.ListADT;
import utilities.Iterator;

import java.util.NoSuchElementException;

public class MyArrayListTest {

    private ListADT<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new MyArrayList<>(); // Assuming MyArrayList is the class that implements ListADT
    }

    @Test
    public void testSize() {
        assertEquals(0, list.size(), "Size should be 0 for an empty list");
        list.add(1);
        assertEquals(1, list.size(), "Size should be 1 after adding one element");
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0, list.size(), "Size should be 0 after clear");
        assertTrue(list.isEmpty(), "List should be empty after clear");
    }

    @Test
    public void testAddAtIndex() {
        list.add(1); // [1]
        list.add(0, 0); // [0, 1]
        assertEquals(0, list.get(0), "Element at index 0 should be 0");
        assertEquals(1, list.get(1), "Element at index 1 should be 1");

        assertThrows(NullPointerException.class, () -> list.add(0, null), "Should throw NullPointerException for null element");
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 2), "Should throw IndexOutOfBoundsException for negative index");
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(3, 2), "Should throw IndexOutOfBoundsException for out-of-range index");
    }

    @Test
    public void testAddToEnd() {
        list.add(1);
        assertEquals(1, list.size(), "Size should be 1 after adding one element");
        assertEquals(1, list.get(0), "Element at index 0 should be 1");

        assertThrows(NullPointerException.class, () -> list.add(null), "Should throw NullPointerException for null element");
    }

    @Test
    public void testAddAll() {
        ListADT<Integer> anotherList = new MyArrayList<>();
        anotherList.add(2);
        anotherList.add(3);
        
        list.add(1);
        list.addAll(anotherList); // [1, 2, 3]

        assertEquals(3, list.size(), "Size should be 3 after adding all elements");
        assertEquals(1, list.get(0), "Element at index 0 should be 1");
        assertEquals(2, list.get(1), "Element at index 1 should be 2");
        assertEquals(3, list.get(2), "Element at index 2 should be 3");

        assertThrows(NullPointerException.class, () -> list.addAll(null), "Should throw NullPointerException for null collection");
    }

    @Test
    public void testGet() {
        list.add(1);
        assertEquals(1, list.get(0), "Element at index 0 should be 1");
        
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1), "Should throw IndexOutOfBoundsException for negative index");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1), "Should throw IndexOutOfBoundsException for out-of-range index");
    }

    @Test
    public void testRemoveAtIndex() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.remove(0), "Removed element should be 1");
        assertEquals(1, list.size(), "Size should be 1 after removal");
        assertEquals(2, list.get(0), "Element at index 0 should be 2");

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1), "Should throw IndexOutOfBoundsException for negative index");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1), "Should throw IndexOutOfBoundsException for out-of-range index");
    }

    @Test
    public void testRemoveElement() {
        list.add(1);
        list.add(2);
        assertEquals(Integer.valueOf(1), list.remove((Integer) 1), "Removed element should be 1");
        assertEquals(1, list.size(), "Size should be 1 after removal");
        assertEquals(2, list.get(0), "Element at index 0 should be 2");
        assertNull(list.remove((Integer) 3), "Should return null if the element is not found");

        assertThrows(NullPointerException.class, () -> list.remove(null), "Should throw NullPointerException for null element");
    }

    @Test
    public void testSet() {
        list.add(1);
        assertEquals(Integer.valueOf(1), list.set(0, 2), "Old element should be 1");
        assertEquals(2, list.get(0), "New element should be 2");
        
        assertThrows(NullPointerException.class, () -> list.set(0, null), "Should throw NullPointerException for null element");
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 2), "Should throw IndexOutOfBoundsException for negative index");
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, 2), "Should throw IndexOutOfBoundsException for out-of-range index");
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty(), "List should be empty initially");
        list.add(1);
        assertFalse(list.isEmpty(), "List should not be empty after adding an element");
    }

    @Test
    public void testContains() {
        list.add(1);
        assertTrue(list.contains(1), "List should contain the element 1");
        assertFalse(list.contains(2), "List should not contain the element 2");

        assertThrows(NullPointerException.class, () -> list.contains(null), "Should throw NullPointerException for null element");
    }

    @Test
    public void testToArrayWithParameter() {
        list.add(1);
        list.add(2);
        Integer[] array = new Integer[2];
        Integer[] result = list.toArray(array);
        
        assertArrayEquals(new Integer[]{1, 2}, result, "Array should contain [1, 2]");
        
        assertThrows(NullPointerException.class, () -> list.toArray((Integer[]) null), "Should throw NullPointerException for null array");
    }

    @Test
    public void testToArray() {
        list.add(1);
        list.add(2);
        Object[] result = list.toArray();
        
        assertArrayEquals(new Object[]{1, 2}, result, "Array should contain [1, 2]");
    }

    @Test
    public void testIterator() {
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        
        assertTrue(iterator.hasNext(), "Iterator should have next element");
        assertEquals(1, iterator.next(), "Next element should be 1");
        assertTrue(iterator.hasNext(), "Iterator should have next element");
        assertEquals(2, iterator.next(), "Next element should be 2");
        assertFalse(iterator.hasNext(), "Iterator should not have next element");
        
        assertThrows(NoSuchElementException.class, () -> iterator.next(), "Should throw NoSuchElementException if no more elements");
    }
}
