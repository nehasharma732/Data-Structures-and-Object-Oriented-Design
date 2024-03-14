import java.util.ArrayList;
import java.util.Collection;

public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {

    // Constants
    public static final int ONE = 1;
    public static final int NEG_ONE = -1;
    public static final int TWO = 2;
    public static final String NULL_ARG = "Null arg.";

    protected ArrayList<E> data;

    public MyMinHeap() {
        data = new ArrayList<>();

    }

    public MyMinHeap(Collection<? extends E> collection) {

        if (collection == null || collection.contains(null)) {
            throw new NullPointerException(NULL_ARG);
        }

        data = new ArrayList<>(collection);

        for (int i = data.size() - 1; i >= 0; i--) {

            percolateDown(i);

        }
    }

    protected void swap(int from, int to) {

        E from_data = data.get(from);
        E to_data = data.get(to);

        data.set(from, to_data);
        data.set(to, from_data);

    }

    protected static int getParentIdx(int index) {

        int parent = (index - ONE) / TWO;

        return parent;

    }

    protected static int getLeftChildIdx(int index) {

        int left = (TWO * index) + ONE;

        return left;

    }

    protected static int getRightChildIdx(int index) {

        int left = (TWO * index) + TWO;

        return left;

    }

    protected int getMinChildIdx(int index) {
        int leftIndex = getLeftChildIdx(index);
        int rightIndex = getRightChildIdx(index);
    
        if (leftIndex >= data.size()) { 
            return NEG_ONE;

        } else if (rightIndex >= data.size()) {
            return leftIndex;

        } else { 
            E leftData = data.get(leftIndex);
            E rightData = data.get(rightIndex);
    
            
            if (leftData.compareTo(rightData) <= 0) {
                return leftIndex;

            } else {
                return rightIndex; 
            }
        }
    }
    

    protected void percolateUp(int index) {

        while (index > 0) {  

            int parentIdx = getParentIdx(index);
            E currentIndexData = data.get(index);
            E parentData = data.get(parentIdx);

            if (currentIndexData.compareTo(parentData) < 0) {
                
                swap(index, parentIdx);
                index = parentIdx; 

            } else {
                break;
            }
        }

    }

    protected void percolateDown(int index) {
        while (index < data.size()) {

            int minChildIndex = getMinChildIdx(index);
            
            if (minChildIndex == -1) {
                break; // The node is  a leaf 
            }
            if (data.get(index).compareTo(data.get(minChildIndex)) > 0) {

                swap(index, minChildIndex);
                index = minChildIndex; 

            } else {
                break;
            }
        }
    }

    protected E deleteIndex(int index) {

        E removedElement = data.get(index);

        if (getMinChildIdx(index) == NEG_ONE) { //leaf 

            data.remove(index); 
            return removedElement;
            
        } else {

            int lastElementIdx = data.size() - 1;
            data.set(index, data.get(lastElementIdx));
            data.remove(lastElementIdx);

            if (index > 0 && data.get(index).compareTo(data.get(getParentIdx(index))) < 0) {

                percolateUp(index);

            } else {

                percolateDown(index);
            }

            return removedElement;
        }
    }
    
    @Override
    public void insert(E element) {

        if (element == null) {
            throw new NullPointerException(NULL_ARG);
        }

        data.add(element);
        percolateUp(data.lastIndexOf(element));

    }

    @Override
    public E getMin() {

        if (data.size() == 0) {

            return null;
        }

        return data.get(0);
    }

    @Override
    public E remove() {

        if (data.size() == 0) {

            return null;
        }

        E root = data.get(0);
        deleteIndex(0);

        return root;
    }
    @Override
    public int size() {

        return data.size();
    }

    @Override
    public void clear() {

        data.clear();
    }

}
