import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Name: Neha Sharma
 * Email: nsharma@ucsd.edu
 * PID: A16652611
 * Sources Used: JDK 17 Docs, chat gpt for help with identifying errors
 *
 * This file contains  Provides a generic doubly linked list 
 * implementation extending AbstractList<E>. 
 * Features include element addition, removal, and access by index. 
 * It includes an inner Node class for element encapsulation 
 * and a custom ListIterator for bidirectional traversal.
 * size - size of list
 * head - points to the head sentinal node
 * tail - points to the tail sentinal node
 */
public class MyLinkedList<E> extends AbstractList<E> {
    
    /** Constants (Magic Numbers) */
    // Labels for the printing of the numbers

    private static final String NO_NULLS_ALLOWED = "Cannot add null into list.";
    private static final String INDEX_OUT_OF_BOUNDS = "Index argument is out of bounds";

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     * data - data in node
     * next - next node from current node
     * prev - previous node from current not
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }
    /**
     * Constructs an empty MyLinkedList.
     * 
     * Initializes the list with sentinel head and tail nodes that are 
     * interconnected. Initiated with no elements. 
     * 
     * This constructor does not take any parameters.
     */
    public MyLinkedList() {

        this.head = new Node(null);
        this.tail = new Node(null);

        this.head.next = tail;
        this.tail.prev = head;

        size = 0;
    }
    /**
     * Returns the number of nodes stores in list.
     *
     * @return the number of elements in this list 
     * (excluding sentinel nodes)
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Gets data of type E within the node at position index.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range 
     */
    @Override
    public E get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS); 
        }
        return getNth(index).data;
    }
    /**
     * Method to add a node at a specific index.
     * 
     * @param index The index of the node to retrieve.
     * @param data element to be inserted
     * @throws NullPointerException if the specified data is null
     * @throws IndexOutOfBoundsException if the index is out of range
     * 
     */
    @Override
    public void add(int index, E data) {

        if (data == null) {
            throw new NullPointerException(NO_NULLS_ALLOWED);
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS);
        }
        Node newNode = new Node(data);
        if (index == 0) { // Insert at beginning
            newNode.next = head.next;
            head.next.prev = newNode;
            head.next = newNode;
            newNode.prev = head;
        } else if (index == size) { // Insert at end
            newNode.prev = tail.prev;
            tail.prev.next = newNode;
            tail.prev = newNode;
            newNode.next = tail;
        } else { // Insert in the middle
            Node current = getNth(index);
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }
    /**
     * This method adds a node at the end into this list
     *
     * @param data data to be added to list
     * @return presumably always returns true
     */ 
    @Override
    public boolean add(E data) {

        if (data == null) {
            throw new NullPointerException(NO_NULLS_ALLOWED);
        }

        Node newNode = new Node(data);
    
        newNode.prev = tail.prev; // This will be head when list is empty
        newNode.next = tail;

        tail.prev.next = newNode;

        tail.prev = newNode;

        size++;
        return true;
    }
    /**
     * Replaces the element at the specified position in this list with the specified element.
     * Returns the element previously at the specified position.
     *
     * @param index index of the element to replace
     * @param data element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws NullPointerException if the specified data is null
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public E set(int index, E data) {

        if (data == null) {
            throw new NullPointerException(NO_NULLS_ALLOWED);
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS);
        }
        
        Node nodeToSet = getNth(index);
        E oldData = nodeToSet.data;
        nodeToSet.data = data;
        return oldData;
    }
    /**
     * Removes the element at the specified position in this list.
     * 
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range 
     */
    @Override
    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS);
        }
    
        Node toRemove;
        if (index == 0) { // Remove from beginning
            toRemove = head.next;
            head.next = toRemove.next;
            toRemove.next.prev = head;
        } else {
            toRemove = getNth(index);
            toRemove.prev.next = toRemove.next;
            toRemove.next.prev = toRemove.prev;
        }
        size--;
        return toRemove.data;
    }
    /**
     * Removes all of the elements from this list (except for the sentinel nodes).
     */
    @Override
    public void clear() {
        // reset the links between the head and tail nodes
        head.next = tail;
        tail.prev = head;
        
        // reset the size
        size = 0;
    }
    /**
     * Determines if the list is empty.
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {

        return size == 0;
    }
    /**
     * Returns the node at the specified position in this list.
     *
     * @param index index of the node to return
     * @return the node at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    protected Node getNth(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS);
        }

        Node current;
        if (index < size / 2) { // Start from head if index is in the first half
            current = head.next;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else { // Start from tail if index is in the second half
            current = tail.prev;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }
    /**
     * Determines if this list contains the element elem between the specified indicies
     *
     * @param elem the element to search for in the list
     * @param start the starting index, inclusive, to begin the search from
     * @param end the ending index, exclusive, to end the search
     * @return true if this list contains the specified element within the specified range,
     *         false otherwise
     * @throws IndexOutOfBoundsException if either start or end are out of bounds
     *         (start < 0 or start >= size || end < 0 or end > size) or if end is <= to start
     */

    public boolean contains(E elem, int start, int end) {

        if (start < 0 || start >= size || end < 0 || end > size || end < start) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS);
        }
        
        Node current = head.next; // Starting from the node after the head sentinel
        for (int i = 0; i < end; i++) {
            if (i >= start && current.data.equals(elem)) {
                return true; // Found the element within the specified range
            }
            current = current.next;
        }
        // element is in the linked list but not within range 
        return false;
    }
}