package implementation;

import utilities.Iterator;
import utilities.QueueADT;
import utilities.EmptyQueueException;
import implementation.MyDLL;

public class MyQueue<E> implements QueueADT<E> {
    private static final long serialVersionUID = 1L;
    private MyDLL<E> list;

    public MyQueue() {
        this.list = new MyDLL<>();
    }

    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Cannot add null element");
        }
        list.add(toAdd);
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (list.isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.remove(0);
    }

    @Override
    public E peek() throws EmptyQueueException {
        if (list.isEmpty()) {
            throw new EmptyQueueException("Queue is empty");
        }
        return list.get(0);
    }

    @Override
    public void dequeueAll() {
        list.clear();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public boolean equals(QueueADT<E> that) {
        if (that == null) {
            return false;
        }
        if (this == that) {
            return true;
        }
        if (this.size() != that.size()) {
            return false;
        }
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();
        while (thisIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return list.toArray(holder);
    }

    @Override
    public boolean isFull() {
        return false; // Linked list-based queue is never full
    }

    @Override
    public int size() {
        return list.size();
    }
}
