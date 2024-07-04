package implementation;

import java.util.NoSuchElementException;

import utilities.Iterator;
import utilities.StackADT;

public class MyStack<E> implements StackADT<E> {
    private MyArrayList<E> arrayList;

    public MyStack() {
        arrayList = new MyArrayList<>();
    }

    @Override
    public void push(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Cannot add null element to the stack");
        arrayList.add(toAdd);
    }

    @Override
    public E pop() throws NoSuchElementException {
        if (arrayList.isEmpty()) throw new NoSuchElementException("Stack is empty");
        return arrayList.remove(arrayList.size() - 1);
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (arrayList.isEmpty()) throw new NoSuchElementException("Stack is empty");
        return arrayList.get(arrayList.size() - 1);
    }

    @Override
    public void clear() {
        arrayList.clear();
    }

    @Override
    public boolean isEmpty() {
        return arrayList.isEmpty();
    }

    @Override
    public Object[] toArray() {
        return arrayList.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return arrayList.toArray(holder);
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        return arrayList.contains(toFind);
    }

    @Override
    public int search(E toFind) {
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            if (arrayList.get(i).equals(toFind)) {
                return arrayList.size() - i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return arrayList.iterator();
    }

    @Override
    public boolean equals(StackADT<E> that) {
        if (this == that) return true;
        if (that == null || this.size() != that.size()) return false;
        Iterator<E> thisIter = this.iterator();
        Iterator<E> thatIter = that.iterator();
        while (thisIter.hasNext() && thatIter.hasNext()) {
            if (!thisIter.next().equals(thatIter.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return arrayList.size();
    }
}
