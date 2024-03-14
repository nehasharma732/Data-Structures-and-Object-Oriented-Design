public class MyDeque<E> implements DequeInterface<E> {

    // Constants
    private static final String ILLEGAL_ARG = "Invalid argument given.";
    private static final String NULL_POINTER = "Null argument not allowed.";
    private static final int ONE = 1;

    // Member variables
    Object[] data;
    int size;
    int rear;
    int front;
    // Capacity of MyDeque will be equal to theinitialcapacity
    // Size is equal to num of elements in data

    public MyDeque(int initialCapacity) {

        if (initialCapacity < 0) {
            throw new IllegalArgumentException(ILLEGAL_ARG);
        }

        data = new Object[initialCapacity];
        size = 0;
        rear = 0;
        front = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void expandCapacity() {

        int currentCapacity = this.data.length;
        int newCapacity;

        if (currentCapacity > 0) {

            newCapacity = currentCapacity * 2;

        } else {

            newCapacity = 10;
        }

        Object[] newCapArr = new Object[newCapacity];

        // Copy over all elements from the old array to the new array
        for (int i = 0; i < this.size; i++) {

            int circularIndex = (this.front + i) % this.data.length;
            newCapArr[i] = this.data[circularIndex];
        }

        // update instance variable to be new array
        this.data = newCapArr;

        if (this.size == 0) {
            this.rear = 0;
        } else {
            this.rear = this.size - 1;
        }
        front = 0;
    }

    @Override
    public void addFirst(E element) {

        if (element == null) {
            throw new NullPointerException(NULL_POINTER);
        }
        if (this.size + ONE > data.length) {
            expandCapacity();
        }

        if (this.size == 0) {

            // inserting the element in the this.front/this.rear index
            this.data[this.front] = element;

            // front/rear doesn't change

        } else if (this.front == 0) {

            // insert element to the last index,

            this.data[this.data.length - 1] = element;

            // front points to the index of new element
            this.front = this.data.length - 1;

        } else {

            // insert to the index (this.front - 1) than
            this.data[this.front - 1] = element;
            this.front = this.front - 1;
        }
        this.size++;

    }

    @Override
    public void addLast(E element) {

        if (element == null) {
            throw new NullPointerException(NULL_POINTER);
        }
        if (this.size + ONE > data.length) {
            expandCapacity();
        }

        if (this.size == 0) {

            this.data[this.rear] = element;
            // rear doesn't change

        } else if (this.rear == this.data.length - 1) { // if rear is the last index

            this.data[0] = element; // wrap around
            this.rear = 0;

        } else {

            this.data[this.rear + 1] = element;
            this.rear = this.rear + 1;

        }
        this.size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E removeFirst() {

        if (this.size == 0) {
            return null;

        } else {

            E front_element = (E) this.data[this.front]; // CORRECT??

            // remove
            this.data[this.front] = null;
            this.size--;

            if (this.size != 0) {

                if (this.front == this.data.length - 1) {

                    this.front = 0;

                } else {

                    this.front = this.front + 1;
                }
            }
            return front_element;
        }
    }

    @Override
    public E removeLast() {

        if (this.size == 0) {
            return null;

        } else {

            E rear_element = (E) this.data[this.rear]; // CORRECT??

            this.data[this.rear] = null;
            size--;

            if (this.size != 0) {

                if (this.rear == 0) {

                    this.rear = this.data.length - 1;

                } else {

                    this.rear = this.rear - 1;
                }
            }
            return rear_element;
        }
    }

    @Override
    public E peekFirst() {

        if (this.size == 0) {
            return null;
        }

        return (E) this.data[this.front];
    }

    @Override
    public E peekLast() {

        if (this.size == 0) {
            return null;
        }
        return (E) this.data[this.rear];
    }

}
