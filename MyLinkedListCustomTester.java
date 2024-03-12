/**
 * Tester file for MyLinkedList class as part of PA3. 
 * Includes various test cases to validate the functionality of the MyLinkedList and its Node inner class.
 */

import static org.junit.Assert.*;

import javax.swing.text.html.parser.Element;

import org.junit.*;

public class MyLinkedListCustomTester {

	private MyLinkedList<Integer> list = new MyLinkedList<>();

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {

        // Populate  list with some initial data for certain tests
        for (int i = 1; i <= 5; i++) { // List will contain [1, 2, 3, 4, 5]
            this.list.add(i);
        }

		// My junit is acting weird so @Before initializations were not recognized in my methods
		// Hence I created a seperate method, createList()

	}

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {

		boolean ref = this.list.add(6); // Adding at the end
		assertSame(Integer.valueOf(6), list.get(list.size() - 1));
		assertSame(Integer.valueOf(6), list.size());
		assertSame(Integer.valueOf(1), list.head.next.getElement());
		assertSame(true, ref);

		this.list.add(7); // Adding at the end
		assertSame(Integer.valueOf(7), list.get(list.size() - 1));
		assertSame(Integer.valueOf(7), list.size());
		assertSame(Integer.valueOf(1), list.head.next.getElement());

		assertThrows(NullPointerException.class, () -> {
			list.add(null);
		});
	}
	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {

		this.list.add(0, 0);
		
		assertSame(Integer.valueOf(0), list.get(0));
		assertSame(6, list.size());
		assertSame(Integer.valueOf(0), list.head.next.getElement());
		assertSame(list.head.next, list.head.next.next.prev);
		assertNull(list.head.next.prev.getElement());
		assertNotNull(list.head.next.next);

		this.list.add(0, 8);
		
		assertSame(Integer.valueOf(8), list.get(0));
		assertSame(7, list.size());
		assertSame(Integer.valueOf(8), list.head.next.getElement());
		assertSame(list.head.next, list.head.next.next.prev);
		assertNull(list.head.next.prev.getElement());
		assertNotNull(list.head.next.next);

		assertThrows(NullPointerException.class, () -> {
            this.list.add(0, null);
        });
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(9, 7); // Assuming list size is 6 after first add
		});
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {

		this.list.add(1, 0); // Adding in the middle
        assertEquals(Integer.valueOf(0), list.get(1));
        assertEquals(6, list.size());
		assertSame(list.head.next.next.getElement(), 0);
		assertSame(list.head.next.next.prev, list.head.next);
		assertSame(list.head.next.next, list.getNth(1));
		assertSame(list.head.next.next.next.getElement(), 2);

		this.list.add(2, 7); // Adding in the middle
        assertEquals(Integer.valueOf(7), list.get(2));
        assertEquals(7, list.size());
		assertSame(list.head.next.next.next.getElement(), 7);
		assertSame(list.head.next.next.next.prev, list.head.next.next);
		assertSame(list.head.next.next.getElement(), 0);
		
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test
	public void testCustomRemoveFromEmpty() {

		MyLinkedList<Integer> emptyList = new MyLinkedList<>();

		assertThrows(IndexOutOfBoundsException.class, () -> {
            emptyList.remove(0);
        });
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {

		Integer removed = this.list.remove(2); // Removing from the middle
        assertEquals(Integer.valueOf(3), removed);
        assertFalse(list.contains(3));
        assertEquals(4, list.size());
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		assertSame(list.head.next.next.next, list.tail.prev.prev);
		assertSame(list.head.next.next, list.tail.prev.prev.prev);

// 

		Integer removed2 = this.list.remove(3); // Removing from the middle
        assertEquals(Integer.valueOf(5), removed2);
        assertFalse(list.contains(5));
        assertEquals(3, list.size());
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
		assertSame(list.head.next.next, list.tail.prev.prev);
		
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test
	public void testCustomSetIdxOutOfBounds() {

		assertThrows(IndexOutOfBoundsException.class, () -> {
            this.list.set(6, 7);
        });
	}

	/**
	 * Aims to test the contains(E data, int start, int end) method.
	 * Data argument exists in the list but outside the given range. 
	 */
	@Test
	public void testCustomContainsExistsOutOfRange() {

		assertFalse("Element 1 should not be found in the range 1-5.", this.list.contains(1, 1, 5));
        assertFalse("Element 3 should be found in the list but not in the range 3-5.", this.list.contains(3, 3, 5));
	}
}
