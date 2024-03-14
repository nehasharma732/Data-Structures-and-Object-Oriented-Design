import java.util.Collection;

public class MyPriorityQueue<E extends Comparable<E>> {

    public static final String NULL_ARG = "Null arg.";

    protected MyMinHeap<E> heap;

    public MyPriorityQueue() {

        heap = new MyMinHeap<>();

    }

    public MyPriorityQueue(Collection<? extends E> collection) {

        if (collection == null || collection.contains(null)) {
            throw new NullPointerException(NULL_ARG);
        }

        heap = new MyMinHeap<>(collection);
    }

    public void push(E element) {

        if (element == null) {
            throw new NullPointerException(NULL_ARG);
        }

        heap.insert(element);
    }

    public E peek() {

        if (heap.size() == 0) {
            return null;
        }

        return heap.getMin();
    }

    public E pop() {

        if (heap.size() == 0) {
            return null;
        }

        E minElement = heap.getMin();

        heap.remove();

        return minElement;

    }

    public int getLength() {

        return heap.size();
    }

    public void clear() {

        heap.clear();
    }

}
