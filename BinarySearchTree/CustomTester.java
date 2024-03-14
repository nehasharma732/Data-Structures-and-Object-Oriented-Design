import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class CustomTester {

    MyBST<Integer, Integer> tree;
    MyBST<Integer, Integer> emptyTree;

    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root = new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two = new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six = new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one = new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three = new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five = new MyBST.MyBSTNode<>(5, 50, six);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        tree.size = 6;

        this.emptyTree = new MyBST<>();
        this.emptyTree.root = null;

    }

    @Test
    public void testSuccessor() {

        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;

        assertSame(treeRoot.getLeft().getLeft().successor(), treeRoot.getLeft());
        assertSame(treeRoot.successor(), treeRoot.getRight().getLeft());
        assertSame(treeRoot.getRight().successor(), null);
    }

    @Test
    public void testInsert() {

        assertThrows(NullPointerException.class, () -> tree.insert(null, 5));

        // inserting into empty tree
        emptyTree.insert(5, 2);

        assertSame(emptyTree.root.getKey(), 5);
        assertSame(emptyTree.root.getValue(), 2);
        assertSame(emptyTree.root.getParent(), null);

        // Replacement of an existing node's value
        tree.insert(4, 1);
        Integer replacedValue = tree.insert(4, 10);
        assertSame(1, replacedValue);
        assertSame(10, tree.root.getValue());

        // Inserting a new node as a left child
        tree.insert(2, 20);
        assertSame(20, tree.root.getLeft().getValue());

        // Inserting a new node as a right child
        tree.insert(6, 60);
        assertSame(60, tree.root.getRight().getValue());
    }

    @Test
    public void testSearch() {

        assertNull(tree.search(null));

        // Search in an empty tree
        assertNull(emptyTree.search(5));

        // Search for an existing key
        assertEquals(Integer.valueOf(50), tree.search(5));
        assertEquals(Integer.valueOf(30), tree.search(3));

        // Search for a non-existing key
        assertNull(tree.search(10));
    }

    @Test
    public void testRemove() {

        assertNull("Removing a non-existent key should return null", tree.remove(99));
        assertNull("Removing a null key should return null", tree.remove(null));

        assertSame(2, tree.remove(1));
        assertSame(5, tree.size);

        tree.remove(3); // First remove 3 to make 2 a node with only a left child
        assertSame(4, tree.size);

        assertSame("Expected value from removing node with only a left child", 1, tree.remove(2));
        assertSame(3, tree.size);

        assertSame("Expected value from removing node with two children", 1, tree.remove(4));
        assertSame(2, tree.size);
    }

    @Test
    public void testInorder() {

        // Test 1: Inorder traversal of an empty tree
        assertTrue("Inorder traversal of an empty tree should return an empty list", emptyTree.inorder().isEmpty());

        // Test 2: Inorder traversal of a tree with only a root node
        emptyTree.insert(10, 100);
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> singleNodeExpected = new ArrayList<>();
        singleNodeExpected.add(new MyBST.MyBSTNode<>(10, 100, null)); // Adjust based on your constructor
        assertEquals("Inorder traversal of a single node tree", singleNodeExpected, emptyTree.inorder());
        assertSame(1, singleNodeExpected.size());

        // Test case: Inorder traversal of a non-empty tree
        MyBST.MyBSTNode<Integer, Integer> root = tree.root;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> expectedRes = new ArrayList<>();
        expectedRes.add(root.getLeft().getLeft());
        expectedRes.add(root.getLeft());
        expectedRes.add(root.getLeft().getRight());
        expectedRes.add(root);
        expectedRes.add(root.getRight().getLeft());
        expectedRes.add(root.getRight());
        assertEquals(expectedRes, tree.inorder());

        // Test case: Inorder traversal of a left-skewed tree
        MyBST<Integer, Integer> leftSkewedTree = new MyBST<>();
        MyBST.MyBSTNode<Integer, Integer> leftRoot = new MyBST.MyBSTNode<>(10, 100, null);
        MyBST.MyBSTNode<Integer, Integer> leftTwo = new MyBST.MyBSTNode<>(9, 90, leftRoot);
        MyBST.MyBSTNode<Integer, Integer> leftThree = new MyBST.MyBSTNode<>(8, 80, leftTwo);
        leftRoot.setLeft(leftTwo);
        leftTwo.setLeft(leftThree);
        leftSkewedTree.root = leftRoot;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> leftSkewedExpectedRes = new ArrayList<>();
        leftSkewedExpectedRes.add(leftThree);
        leftSkewedExpectedRes.add(leftTwo);
        leftSkewedExpectedRes.add(leftRoot);
        assertEquals(leftSkewedExpectedRes, leftSkewedTree.inorder());

        // Test case: Inorder traversal of a right-skewed tree
        MyBST<Integer, Integer> rightSkewedTree = new MyBST<>();
        MyBST.MyBSTNode<Integer, Integer> rightRoot = new MyBST.MyBSTNode<>(10, 100, null);
        MyBST.MyBSTNode<Integer, Integer> rightTwo = new MyBST.MyBSTNode<>(11, 110, rightRoot);
        MyBST.MyBSTNode<Integer, Integer> rightThree = new MyBST.MyBSTNode<>(12, 120, rightTwo);
        rightRoot.setRight(rightTwo);
        rightTwo.setRight(rightThree);
        rightSkewedTree.root = rightRoot;
        ArrayList<MyBST.MyBSTNode<Integer, Integer>> rightSkewedExpectedRes = new ArrayList<>();
        rightSkewedExpectedRes.add(rightRoot);
        rightSkewedExpectedRes.add(rightTwo);
        rightSkewedExpectedRes.add(rightThree);
        assertEquals(rightSkewedExpectedRes, rightSkewedTree.inorder());

    }

    @Test
    public void testCopy() {

        MyBST<Integer, Integer> actualRes = tree.copy();

        assertEquals(6, actualRes.size);

        MyBST.MyBSTNode<Integer, Integer> rootOrig = tree.root;
        MyBST.MyBSTNode<Integer, Integer> rootCopy = actualRes.root;

        // Check size is the same
        assertEquals(tree.size, actualRes.size);

        // Compare left/right pointers and node values
        assertEquals(rootOrig.getKey(), rootCopy.getKey());
        assertEquals(rootOrig.getValue(), rootCopy.getValue());
    }

}
