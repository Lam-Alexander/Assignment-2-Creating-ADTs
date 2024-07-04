package implementation;

public class MyDLLNode<E> {
    E data;
    MyDLLNode<E> next, prev;

    MyDLLNode(E data) {
        this.data = data;
    }
}
