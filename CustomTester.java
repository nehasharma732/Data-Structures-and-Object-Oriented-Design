import static org.junit.Assert.*;
import org.junit.*;

public class CustomTester {

    static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
            int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }

    @Test
    public void testDequeConstructor() {

        // check if it throws exception
        assertThrows(IllegalArgumentException.class, () -> new MyDeque<>(-1));
    }

    @Test
    public void testDequeExpandCapacity() {

        MyDeque<Integer> deque = new MyDeque<>(5);

        Integer[] orig = { 3, 4, 5, 1, 2 };
        initDeque(deque, orig, 5, 3, 2);

        Integer[] expanded = { 1, 2, 3, 4, 5, null, null, null, null, null };

        deque.expandCapacity();

        for (int i = 0; i < 10; i++) {
            assertEquals("Deque structure should be maintained",
                    expanded[i], deque.data[i]);
        }
        assertSame(deque.size, 5);
        assertSame(deque.front, 0);
        assertSame(deque.rear, 4);

    }

    @Test
    public void testDequeAddFirst() {

        // Case 1
        MyDeque<Integer> deque = new MyDeque<>(5);

        Integer[] orig = { 1, 2, 3, 4, 5 };
        initDeque(deque, orig, 5, 0, 4);

        Integer[] finalOrdering = { 1, 2, 3, 4, 5, null, null, null, null, 0 };

        deque.addFirst(0);

        for (int i = 0; i < 10; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrdering[i], deque.data[i]);
        }
        assertSame(deque.size, 6);
        assertSame(deque.front, 9);
        assertSame(deque.rear, 4);

        // Case 2
        MyDeque<Integer> deque2 = new MyDeque<>(5);

        Integer[] orig2 = { 0, null, null, null, null };
        initDeque(deque2, orig2, 1, 0, 0);

        Integer[] finalOrdering2 = { 0, null, null, null, 10 };

        deque2.addFirst(10);

        for (int i = 0; i < 5; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrdering2[i], deque2.data[i]);
        }
        assertSame(deque2.size, 2);
        assertSame(deque2.front, 4);
        assertSame(deque2.rear, 0);

        // Case 3
        MyDeque<Integer> deque3 = new MyDeque<>(5);

        Integer[] orig3 = { null, null, null, null, null };
        initDeque(deque3, orig3, 0, 4, 4);

        deque3.addFirst(5);

        Integer[] finalOrdering3 = { null, null, null, null, 5 };

        for (int i = 0; i < 5; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrdering3[i], deque3.data[i]);
        }
        assertSame(deque3.size, 1);
        assertSame(deque3.front, 4);
        assertSame(deque3.rear, 4);

    }

    @Test
    public void testDequeAddLast() {

        // Case 1
        MyDeque<Integer> deque = new MyDeque<>(10);

        Integer[] orig = { null, 2, 3, 4, 5, null, null, null, null, null };
        initDeque(deque, orig, 4, 1, 4);

        Integer[] finalOrdering = { null, 2, 3, 4, 5, 6, null, null, null, null };

        deque.addLast(6);

        for (int i = 0; i < 10; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrdering[i], deque.data[i]);
        }
        assertSame(deque.size, 5);
        assertSame(deque.front, 1);
        assertSame(deque.rear, 5);

        // Case 2
        MyDeque<Integer> deque2 = new MyDeque<>(5);

        Integer[] orig2 = { null, null, null, null, null };
        initDeque(deque2, orig2, 0, 0, 0);

        Integer[] finalOrdering2 = { 0, null, null, null, null };

        deque2.addLast(0);

        for (int i = 0; i < 5; i++) {
            assertEquals(finalOrdering2[i], deque2.data[i]);
        }
        assertSame(deque2.size, 1);
        assertSame(deque2.front, 0);
        assertSame(deque2.rear, 0);

    }

    @Test
    public void testDequeRemoveFirst() {

        // Case 1
        MyDeque<Integer> deque = new MyDeque<>(10);

        Integer[] orig = { 1, 2, 3, 4, 5, null, null, null, null, 0 };
        initDeque(deque, orig, 6, 9, 4);

        Integer[] finalOrdering = { 1, 2, 3, 4, 5, null, null, null, null, null };

        deque.removeFirst();

        for (int i = 0; i < 10; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrdering[i], deque.data[i]);
        }
        assertSame(deque.size, 5);
        assertSame(deque.front, 0);
        assertSame(deque.rear, 4);

        deque.removeFirst(); // call again

        Integer[] finalOrdering2 = { null, 2, 3, 4, 5, null, null, null, null, null };

        for (int i = 0; i < 10; i++) {
            assertEquals(finalOrdering2[i], deque.data[i]);
        }
        assertSame(deque.size, 4);
        assertSame(deque.front, 1);
        assertSame(deque.rear, 4);
    }

    @Test
    public void testDequeRemoveLast() {

        // Case 1
        MyDeque<Integer> deque = new MyDeque<>(5);

        Integer[] orig = { 0, null, null, null, 10 };
        initDeque(deque, orig, 2, 4, 0);

        deque.removeLast();

        Integer[] finalOrdering = { null, null, null, null, 10 };

        for (int i = 0; i < 5; i++) {
            assertEquals("Deque structure should be maintained",
                    finalOrdering[i], deque.data[i]);
        }
        assertSame(deque.size, 1);
        assertSame(deque.front, 4);
        assertSame(deque.rear, 4);

        deque.removeLast();

        Integer[] finalOrdering2 = { null, null, null, null, null };

        for (int i = 0; i < 5; i++) {
            assertEquals(finalOrdering2[i], deque.data[i]);
        }
        assertSame(deque.size, 0);
        assertSame(deque.front, 4);
        assertSame(deque.rear, 4);
    }

    @Test
    public void testStack() {

        MyStack<String> stack = new MyStack<>(15);

        stack.push("First");
        stack.push("Second");
        stack.push("Third");

        assertSame("Third", stack.pop());
        assertSame("Second", stack.pop());
        assertSame("First", stack.pop());

        assertTrue(stack.empty());

    }

    @Test
    public void testQueue() {

        MyQueue<Integer> queue = new MyQueue<>(10);

        queue.enqueue(5);
        queue.enqueue(7);
        queue.enqueue(4);

        assertSame(5, queue.dequeue());
        assertSame(7, queue.dequeue());
        assertSame(4, queue.dequeue());
    }

    @Test
    public void testavgDiffMonotonicIncreasing() {

        MyAlgorithm algorithmInstance = new MyAlgorithm();

        int[] intArrShort = { 4, 1, 3, 5, 7 };
        int[] intArrLarge = { 1, 2, 3, 3, 4, 5, 4, 5 };
        int[] intArrLarge2 = { 4, 3, 2, 1, 4, 3, 3, 3 };
        int[] intArr1Arg = { 1 };
        int[] intArrNoArg = {};

        assertEquals(1.5, algorithmInstance.avgDiffMonotonicIncreasing(intArrShort), 0.0001);
        assertEquals(1.0, algorithmInstance.avgDiffMonotonicIncreasing(intArrLarge), 0.0001);
        assertEquals(0.0, algorithmInstance.avgDiffMonotonicIncreasing(intArrLarge2), 0.0001);
        assertEquals(0.0, algorithmInstance.avgDiffMonotonicIncreasing(intArr1Arg), 0.0001);
        assertEquals(0.0, algorithmInstance.avgDiffMonotonicIncreasing(intArrNoArg), 0.0001);
    }

}
