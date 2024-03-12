public class MyArrayList<E> implements  MyList<E> {

    // Instance Variables
    Object[] values; 

    int length; // num of elements at a given time 

    

    // Constructors 
    public MyArrayList() {

        this.values = new Object[5]; // capacity of 5, length 0

        this.length = 0; //number of element of myarraylist

    }
    public MyArrayList(int initialCapacity) {

        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Invalid initial capacity.");
        }
        this.values = new Object[initialCapacity];
        // The capacity of the ArrayList is the length of the array

        this.length = 0;

    }
    public MyArrayList(E[] arr) {

        if (arr == null) {
            this.values = new Object[5]; // capacity of 5, length 0
            this.length = 0;
        }
        else {
            this.values = arr.clone();
            this.length = arr.length;
        }
    }




    // Methods 

    /**
     * Checks and updates capacity if need be
     */
    public void expandCapacity (int requiredCapacity) {

        int currentCapacity = this.values.length;

        if (requiredCapacity < currentCapacity) { 
            throw new IllegalArgumentException("Required capacity should be greater than initial capacity.");
        } 
        else { 

            int newCapacity; 

            if (currentCapacity != 0) { // If current capacity is non-zero, double current capacity.

                newCapacity = currentCapacity * 2; 

            } else { // If the current capacity is 0, reset the capacity to the default capacity of 5.

                newCapacity = 5; 
            } 

            if (newCapacity < requiredCapacity) {

                newCapacity = requiredCapacity;
            }

            // Use the new capacity to create a new array. 
            Object[] newCapArr = new Object[newCapacity]; 

            // Copy over all elements from the old array to the new array
            for (int i = 0; i < this.values.length; i++) {
                newCapArr[i] = this.values[i];
            }

            // Update values array to the new array, original this.length is preserved
            this.values = newCapArr;
        }

    }
    /**
     * Get the number of elements that the underlying array can possibly hold, 
     * i.e. the length of the underlying array.
     */
    public int getCapacity() { 

        return this.values.length; 
    }
    /**
     * Insert an element at the specified index. Utilizes expandCapacity.
     */
    public void insert(int index, E element) { 

       if (index < 0 || index > this.length) { 
            throw new IndexOutOfBoundsException("Index must be greater than 0 and less than length of the ArrayList.");
        } else {

            // Expand capacity if need be. method takes care of everything
            // place if statement 

            if (this.length + 1 > this.values.length ) {
                expandCapacity(this.length + 1);
            } 

            // shift all elements to the right of index to make space for new element
            for (int i = this.length - 1; i >= index ; i--) {
                this.values[i + 1] = this.values[i];
            }

            this.values[index] = element;
            this.length++;
        }
    }
    /**
     * Add an element at the end of the list. Utilizes expandCapacity.
     */
    public void append(E element) { 

        if (this.length + 1 > this.values.length ) {
            expandCapacity(this.length + 1);
        } 
        this.values[this.length] = element;
        this.length++;
    }
    /**
     * Add an element at the beginning of the list. Utilizes expandCapacity.
     */
    public void prepend(E element) {  

        if (this.length + 1 > this.values.length ) {
            expandCapacity(this.length + 1);
        } 
        // Shift all elements in the array to right by one
        for (int i = this.length - 1; i >= 0; i--) {
            this.values[i + 1] = this.values[i];
        }

        this.values[0] = element;
        this.length++;
    }
    /**
     * Get an element at the specified index.
     */@SuppressWarnings("unchecked")
    public E get(int index) { 

        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("Index must be greater than 0 and less than the length of Arraylist. ");
        } else { 
            return (E) this.values[index]; // IS THIS OK???
        }
    }
    /**
     * Set the given element at the specified index and return the overwritten element.
     */@SuppressWarnings("unchecked")
    public E set(int index, E element) {

        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("Index must be greater than 0 and less than the length of Arraylist. ");
        } else { 

            E overwritten = (E) this.values[index];  //IS THIS OK??
            
            this.values[index] = element; 

            return overwritten; 
        }

    }
    /**
     * Remove and return the element at the specified index.
     */@SuppressWarnings("unchecked")
    public E remove(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("Index must be greater than 0 and less than the length of Arraylist. ");
        } else {
            
            E removedElement = (E) this.values[index];  //HELPP 

            this.values[index] = null; 

            // move all elements to the right of index towards the left by 1
            for (int i = index; i < this.length - 1; i++) {
                this.values[i] = this.values[i + 1];
            }

            this.length --;
            return removedElement; 
        }
    }
    /**
     * Return the number of elements that exist in the ArrayList
     */
    public int size() { 

        return this.length; 

    }
    /**
     * Rotates each element in the ArrayList left by index amount. 
     */
    public void rotate(int index) { 
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("Index must be greater than 0 and less than the length of Arraylist. ");
        } else {
        
            // loop executes index times
            for (int i = 0; i < index; i++) {
                Object temp = this.values[0]; // Store the first element temporarily
    
                // Shift elements to the left
                for (int j = 0; j < this.length - 1; j++) {
                    this.values[j] = this.values[j + 1];
                }
    
                // Place stored element at the end
                this.values[this.length - 1] = temp;
            }
        }
    }
    /**
     * Find the given element in the ArrayList and return it's index.
     */
    public int find(E element) {

        /// .equals or ==? 

        for (int i = 0; i < this.length; i++) {
            if (this.values[i] == element) {
                return i; 
            }
        }
        return -1;
    }
}