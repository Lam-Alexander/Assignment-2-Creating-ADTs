package implementation;

import java.io.Serializable;
import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.ListADT;

public class MyArrayList<E> implements ListADT<E>, Serializable {

    private static final long serialVersionUID = 1L;
    private static final int DEFAULT_CAPACITY = 10;
    private E[] elements;
    private int size;

    // Constructor to initialize the array with default capacity
    public MyArrayList() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // Returns the current number of elements in the list
    @Override
    public int size() {
        return this.size;
    }

    // Removes all elements from the list
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    // Inserts an element at the specified position
    @Override
    public boolean add(int index, E toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = toAdd;
        size++;
        return true;
    }

    // Appends an element to the end of the list
    @Override
    public boolean add(E toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }
        ensureCapacity(size + 1);
        elements[size++] = toAdd;
        return true;
    }

    // Adds all elements from another list to this list
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null collection");
        }
        ensureCapacity(size + toAdd.size());
        for (int i = 0; i < toAdd.size(); i++) {
            elements[size++] = toAdd.get(i);
        }
        return true;
    }

    // Returns the element at the specified position
    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return elements[index];
    }

    // Removes the element at the specified position
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        E removedElement = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null; // clear to let GC do its work
        return removedElement;
    }

    // Removes the first occurrence of the specified element
    @Override
    public E remove(E toRemove) {
        if (toRemove == null) {
            throw new NullPointerException("Cannot remove null element");
        }
        for (int i = 0; i < size; i++) {
            if (toRemove.equals(elements[i])) {
                return remove(i);
            }
        }
        return null;
    }

    // Replaces the element at the specified position
    @Override
    public E set(int index, E toChange) {
        if (toChange == null) {
            throw new NullPointerException("Cannot set null element");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        E oldElement = elements[index];
        elements[index] = toChange;
        return oldElement;
    }

    // Returns true if the list contains no elements
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns true if the list contains the specified element
    @Override
    public boolean contains(E toFind) {
        if (toFind == null) {
            throw new NullPointerException("Cannot search for null element");
        }
        for (int i = 0; i < size; i++) {
            if (toFind.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    // Returns an array containing all elements in this list
    @Override
    public E[] toArray(E[] toHold) {
        if (toHold == null) {
            throw new NullPointerException("Array cannot be null");
        }
        if (toHold.length < size) {
            E[] newArray = (E[]) new Object[size];
            for (int i = 0; i < size; i++) {
                newArray[i] = elements[i];
            }
            return newArray;
        }
        for (int i = 0; i < size; i++) {
            toHold[i] = elements[i];
        }
        if (toHold.length > size) {
            toHold[size] = null;
        }
        return toHold;
    }

    // Returns an array containing all elements in this list
    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }
        return newArray;
    }

    // Returns an iterator over the elements in this list
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    // Ensures the list has sufficient capacity to hold the specified number of elements
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            E[] newElements = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }

    // Internal iterator class for the MyArrayList
    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iteration");
            }
            return elements[currentIndex++];
        }
    }
}