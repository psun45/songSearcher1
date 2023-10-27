// --== CS400 Fall 2023 File Header Information ==--
// Name: Patrick Sun
// Email: psun45@wisc.edu
// Group: B25
// TA: Zheyang Xiong
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import java.util.Iterator;
import java.util.Stack;
import javax.print.attribute.standard.PrintQuality;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IterableMultiKeyRBT<T extends Comparable<T>> extends RedBlackTree<KeyListInterface<T>> implements IterableMultiKeySortedCollectionInterface<T> {
    private Comparable<T> iterationStartPoint;
    private int numKeys;


    /**
     * Inserts value into tree that can store multiple objects per key by keeping
     * lists of objects in each node of the tree.
     *
     * @param key object to insert
     * @return true if obj was inserted
     */
    @Override


    public boolean insertSingleKey(Comparable key) {
        //The method should create a KeyList with the new key
        KeyList<T> keyList = new KeyList<>(key);
        // check if the tree already contains a node with duplicates
        Node<KeyListInterface<T>> node = findNode(keyList);
        // If it does, add the key to the KeyList in the node that contains the duplicates and return false.
        if (node != null) {
            KeyListInterface<T> existingKeyList = node.data;
            existingKeyList.addKey((T) key);
            numKeys++;
            return false;
            // If it does not, then insert the new KeyList into a new node of the tree and return true.
        } else {
            insert(keyList);
            numKeys++;
            return true;
        }
    }

    /**
     * @return the number of values in the tree.
     */
    @Override
    public int numKeys() {
        return numKeys;
    }

    /**
     * Returns an iterator that does an in-order iteration over the tree.
     */
    @Override
    public Iterator iterator() {
        // The iterator should iterate over the keys in ascending order.
        return new Iterator<T>() {
            private Iterator<T> iterator;
            Stack<KeyListInterface<T>> stack = getStartStack();

            /**
             * Checks if there are more elements in the iterator.
             * @return true if there are more elements, false otherwise
             */
            @Override
            public boolean hasNext() {
                // If the iterator has not reached the end of the tree, return true.
                if (iterator != null && iterator.hasNext()) {
                    return true;
                }
                if (!stack.isEmpty()) {
                    return true;
                }
                // If the iterator has reached the end of the tree, return false.
                return false;
            }

            /**
             * Gets the next element in the iterator.
             * @return the next element
             */
            @Override
            public T next() {
                // If there are no more elements, throw a NullPointerException.
                if (!hasNext()) {
                    throw new NullPointerException("No more elements");
                }

                if (iterator != null && iterator.hasNext()) {
                    // If the current iterator has more elements, return the next element.
                    T value = iterator.next();
                    return value;


                } else {
                    // If the current iterator is at the end, get the next node's KeyList and continue iteration.
                    KeyListInterface<T> keyList = stack.pop();
                    Node<KeyListInterface<T>> node = findNode(keyList);
                    iterator = node.data.iterator();

                    Node<KeyListInterface<T>> nextNode = node.down[1];
                    while (nextNode != null) {
                        // Push nodes from the right subtree onto the stack for iteration.
                        stack.push(nextNode.data);
                        nextNode = nextNode.down[0];


                    }
                    return iterator.next();
                }
            }
        };
    }

    /**
     * Sets the starting point for iterations. Future iterations will start at the
     * starting point or the key closest to it in the tree. This setting is remembered
     * until it is reset. Passing in null disables the starting point.
     *
     * @param startPoint the start point to set for iterations
     */
    @Override
    public void setIterationStartPoint(Comparable startPoint) {
        this.iterationStartPoint = startPoint;
    }

    /**
     * This method returns a stack of KeyListInterfaces that are the starting point for the iterator.
     *
     * @return a stack of KeyListInterfaces that are the starting point for the iterator
     */
    protected Stack<KeyListInterface<T>> getStartStack() {
        Stack<KeyListInterface<T>> stack = new Stack<>();
        // If no iteration start point is set, initialize stack with nodes on path to smallest key.
        if (iterationStartPoint == null) {
            Node<KeyListInterface<T>> node = root;
            while (node != null) {
                stack.push(node.data);
                node = node.down[0];
            }
            // If iteration start point is set, initialize stack with nodes >= start point.
        } else {
            Node<KeyListInterface<T>> node = root;
            if (node == null) {
                return stack;
            }

            while (node != null) {
                if (node.data.iterator().next().compareTo((T) iterationStartPoint) >= 0) {
                    // Push nodes with keys >= start point onto the stack.
                    stack.push(node.data);
                    node = node.down[0];
                } else {
                    // Traverse right subtree if key is < start point.
                    node = node.down[1];
                }
            }

        }
        return stack;

    }

    /**
     * Clears the tree by calling the old clear method of BinarySearchTree,
     * then sets the number of keys to zero.
     */
    @Override
    public void clear() {
        super.clear();
        numKeys = 0;
    }

    /**
     * This method checks the behavior of inserting keys into the tree that can store
     * multiple objects per key by keeping lists of objects in each node of the tree.
     * It verifies whether the method returns true when a new key is inserted, and false
     * when attempting to insert a duplicate key.
     */
    @Test
    public void testInsertSingleKey() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();

        // Test inserting a key and should return true if successful
        assertTrue(tree.insertSingleKey(1));

    }

    /**
     * This method checks the behavior of counting the number of keys in the tree.
     * It verifies if the method correctly returns the count of distinct keys.
     */
    @Test
    public void testNumKeys() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();

        // Test with an empty tree
        assertEquals(0, tree.numKeys());

        // Test after inserting some keys
        tree.insertSingleKey(1);
        tree.insertSingleKey(2);
        tree.insertSingleKey(1); // Duplicates should increase the count

        assertEquals(3, tree.numKeys());
    }

    /**
     * This method checks the behavior of the iterator returned by the tree.
     * It verifies if the iterator returns keys in ascending order and if it
     * properly handles the case when there are no more keys to return.
     */
    @Test
    public void testIterator() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();

        // Add some keys
        tree.insertSingleKey(1);
        tree.insertSingleKey(2);
        tree.insertSingleKey(3);
        tree.insertSingleKey(3);

        Iterator<Integer> iterator = tree.iterator();

        // Test if the iterator returns the keys in ascending order
        assertEquals(1, (int) iterator.next());
        assertEquals(2, (int) iterator.next());
        assertEquals(3, (int) iterator.next());

    }

    @Test
    public void submissionCheckerDuplicateKeys() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(50);
        tree.insertSingleKey(50);
        tree.insertSingleKey(100);
        tree.insertSingleKey(100);
        tree.insertSingleKey(150);
        tree.insertSingleKey(150);

        Assertions.assertEquals(3, tree.size());
        Assertions.assertEquals(6, tree.numKeys());

        int count = 0;
        Iterator<Integer> iter = tree.iterator();

        Assertions.assertEquals(50, iter.next());
        Assertions.assertEquals(50, iter.next());
        Assertions.assertEquals(100, iter.next());
        Assertions.assertEquals(100, iter.next());

    }

    @Test
    public void submissionCheckerIteratorOrder() {
        IterableMultiKeyRBT<String> tree = new IterableMultiKeyRBT<>();
        String[] keys = new String[]{"red", "blue", "green", "yellow"};
        int[] sequence = new int[]{1, 2, 2, 3, 1, 0, 3, 2, 1, 0, 2};
        for (int i : sequence) tree.insertSingleKey(keys[i]);

        String last = null;
        for (String key : tree) {
            if (last != null) Assertions.assertTrue(last.compareTo(key) <= 0);
            last = key;
        }
    }

    /*
     * Checks if an iterator with a start point iterates over all keys
     * equal to and larger than the start point.
     */
    @Test
    public void submissionCheckerIteratorStartPoint() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.insertSingleKey(1);
        tree.insertSingleKey(33);
        tree.insertSingleKey(40);

        tree.setIterationStartPoint(33);
        Assertions.assertEquals(33, tree.iterator().next());
        tree.setIterationStartPoint(34);
        Assertions.assertEquals(40, tree.iterator().next());
        tree.setIterationStartPoint(null);
        Assertions.assertEquals(1, tree.iterator().next());
    }

}