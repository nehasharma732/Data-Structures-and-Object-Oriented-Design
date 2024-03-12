import java.util.ArrayList;

public class MyBST<K extends Comparable<K>, V> {

    // CONSTANTS
    public static final String NULL_ARG = "no null arg.";

    MyBSTNode<K, V> root = null;
    int size = 0;

    public int size() {
        return size;
    }

    public V insert(K key, V value) {

        if (key == null) {
            throw new NullPointerException(NULL_ARG);
        }

        V replaced = null;
        MyBSTNode<K, V> newNode = new MyBSTNode<>(key, value, null);

        if (this.root == null) {
            this.root = newNode;

        } else {

            MyBSTNode<K, V> curr = this.root;

            while (curr != null) {

                if (key.compareTo(curr.key) == 0) {
                    // replace the value in the existing node with value
                    replaced = curr.value;
                    curr.setValue(value);

                    curr = null;

                } else if (key.compareTo(curr.key) < 0) {

                    if (curr.left == null) {
                        curr.left = newNode;
                        newNode.setParent(curr);

                        size++;
                        // link the bottm node upwards
                        curr = null;

                    } else {
                        curr = curr.left;
                    }
                } else {

                    if (curr.right == null) {
                        curr.right = newNode;
                        newNode.setParent(curr);
                        size++;
                        curr = null;

                    } else {
                        curr = curr.right;
                    }
                }
            }
        }
        return replaced;
    }

    public V search(K key) {

        if (key == null || root == null) {
            return null;
        }

        MyBSTNode<K, V> current = this.root;

        while (current != null) {

            int cmpr = key.compareTo(current.key);

            if (cmpr < 0) {
                current = current.left;

            } else if (cmpr > 0) {
                current = current.right;

            } else {
                return current.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        if (key == null) {
            return null;
        }

        MyBSTNode<K, V> current = root;
        MyBSTNode<K, V> parent = null;
        V valueRemoved = null;

        while (current != null) { // Search for the node
            int cmp = key.compareTo(current.key);

            if (cmp == 0) {
                valueRemoved = current.value;

                // leaf
                if (current.left == null && current.right == null) {
                    if (parent == null) {
                        root = null;
                    } else if (parent.left == current) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                }
                // node has only a left child
                else if (current.right == null) {
                    if (parent == null) {
                        root = current.left;
                        root.parent = null;
                    } else if (parent.left == current) {
                        parent.left = current.left;
                        parent.left.parent = parent;
                    } else {
                        parent.right = current.left;
                        parent.right.parent = parent;
                    }
                }
                // node has only a right child
                else if (current.left == null) {
                    if (parent == null) {
                        root = current.right;
                        root.parent = null;
                    } else if (parent.left == current) {
                        parent.left = current.right;
                        parent.left.parent = parent;
                    } else {
                        parent.right = current.right;
                        parent.right.parent = parent;
                    }
                }
                // node has two children
                else {
                    MyBSTNode<K, V> successor = current.successor();
                    current.key = successor.key;
                    current.value = successor.value;

                    this.remove(successor.key);
                }

                size--;
                return valueRemoved;

            } else if (cmp > 0) {
                parent = current;
                current = current.right;
            } else { // cmp < 0
                parent = current;
                current = current.left;
            }
        }
        return null; // Node with key arg not found
    }

    public ArrayList<MyBSTNode<K, V>> inorder() {

        ArrayList<MyBSTNode<K, V>> newArrayList = new ArrayList<>();
        MyBSTNode<K, V> current = root;

        if (current != null) {

            // get to the left most node
            while (current.left != null) {
                current = current.left;

            }

            // get the subsequent sucessor
            while (current != null) {
                newArrayList.add(current);
                current = current.successor();
            }
        }

        return newArrayList;
    }

    public MyBST<K, V> copy() {
        MyBST<K, V> newTree = new MyBST<>();
        newTree.root = copyNodes(this.root);

        newTree.size = this.size;

        return newTree;
    }

    // helper method : recursively copy nodes
    private MyBSTNode<K, V> copyNodes(MyBSTNode<K, V> node) {
        if (node == null) {
            return null;
        }

        MyBSTNode<K, V> newNode = new MyBSTNode<>(node.key, node.value, null);

        newNode.left = copyNodes(node.left); // recursively copy
        if (newNode.left != null) {
            newNode.left.parent = newNode;
        }
        newNode.right = copyNodes(node.right);

        if (newNode.right != null) {
            newNode.right.parent = newNode;
        }
        return newNode;
    }

    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         *
         * @param key    the key the MyBSTNode<K,V> will
         * @param value  the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         *
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         *
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        public MyBSTNode<K, V> successor() {

            MyBSTNode<K, V> successor = null;

            if (this.right != null) {
                // when there is a right subtree,
                // the successor is the leftmost node of the right subtree

                successor = this.right;

                while (successor.left != null) {
                    successor = successor.left;
                }

            } else {
                // when there's no right subtree, move up
                successor = this.parent;

                MyBSTNode<K, V> current = this;
                while (successor != null && current == successor.right) {
                    current = successor;
                    successor = successor.parent;
                }

            }
            return successor;
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null : this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null : this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
