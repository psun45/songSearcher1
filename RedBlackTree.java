// --== CS400 Fall 2023 File Header Information ==--
// Name: Patrick Sun
// Email: psun45@wisc.edu
// Group: B25
// TA: Zheyang Xiong
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Red-Black Tree implementation that extends Binary Search Tree.
 */
public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {
    protected static class RBTNode<T> extends Node<T> {
        public int blackHeight = 0;

        public RBTNode(T data) {
            super(data);
        }

        public RBTNode<T> getUp() {
            return (RBTNode<T>) this.up;
        }

        public RBTNode<T> getDownLeft() {
            return (RBTNode<T>) this.down[0];
        }

        public RBTNode<T> getDownRight() {
            return (RBTNode<T>) this.down[1];
        }
    }

    /**
     * Enforces Red-Black Tree properties after node insertion.
     *
     * @param node The node that may violate Red-Black Tree properties.
     */
    protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> node) {
        // Implement the logic to handle RBT property violations here
        // Use rotations and color changes as necessary
        //newly inserted node to be red
        node.blackHeight = 0;
        //setting up the parent, grandparent, and parent's sibling for checking the RBT violations
        RBTNode<T> parent = null;
        if (node != root) {
            parent = node.getUp();
        }
        RBTNode<T> grandparent = null;
        if (parent != root && parent != null) {
            grandparent = parent.getUp();
        }
        RBTNode<T> parentSibling = null;
        if (grandparent != null && parent == grandparent.getDownLeft()) {
            parentSibling = grandparent.getDownRight();
        }
        if (grandparent != null && parent == grandparent.getDownRight()) {
            parentSibling = grandparent.getDownLeft();
        }
        //Case 0: If the newNode is the root, then it is black. No violations.
        if (parent == null) {
            node.blackHeight = 1;
        }
        if (parent == root || grandparent == null) {
            return;
        } else {
            //Case 1: parent and its sibling are both red, which violates the RBT property
            if (parentSibling != null && parentSibling.blackHeight == 0 && parent.blackHeight == 0) {
                parent.blackHeight = 1;
                parentSibling.blackHeight = 1;
                grandparent.blackHeight = 0;
                //recursively check the RBT property for the grandparent
                enforceRBTreePropertiesAfterInsert(grandparent);
            }
            //combine case 2 and case 3 together
            if (parentSibling == null || parentSibling.blackHeight == 1 && parent.blackHeight == 0) {
                //Case 2: parent is red which is violating node and its sibling are black
                //Case 2: parent is the left child of grandparent and node is the left child of parent
                //or parent is the right child of grandparent and node is the right child of parent
                if (parent == grandparent.getDownLeft() && node == parent.getDownLeft()
                        || parent == grandparent.getDownRight() && node == parent.getDownRight()) {
                    //rotate right on grandparent
                    rotate(parent, grandparent);
                    //swap the colors of parent and grandparent
                    int temp = parent.blackHeight;
                    parent.blackHeight = grandparent.blackHeight;
                    grandparent.blackHeight = temp;
                }
                //case 3: slightly different from case 2 but the red and black nodes condition is the same as case 2
                //Case 3: parent is the left child of grandparent and node is the right child of parent
                //or parent is the right child of grandparent and node is the left child of parent
                else if (parent == grandparent.getDownLeft() && node == parent.getDownRight()
                        || parent == grandparent.getDownRight() && node == parent.getDownLeft()) {
                    //rotate left on parent
                    rotate(node, parent);
                    //rotate right on grandparent
                    rotate(node, grandparent);
                    //swap the colors of node and grandparent
                    int temp = node.blackHeight;
                    node.blackHeight = grandparent.blackHeight;
                    grandparent.blackHeight = temp;
                }
            }
        }
    }

    /**
     * Inserts a new node into the Red-Black Tree.
     *
     * @param newNode The node to be inserted.
     * @return True if the insertion was successful, false otherwise.
     * @throws NullPointerException if the newNode is null.
     */
    public boolean insert(T newNode) throws NullPointerException {
        //adding the new node to the RBT, and checking the RBT property afterward in
        // enforceRBTreePropertiesAfterInsert()
        if (newNode == null) {
            throw new NullPointerException("Cannot insert data value null into the tree.");
        }

        RBTNode<T> insertedNewNode = new RBTNode<T>(newNode);
        super.insertHelper(insertedNewNode);
        enforceRBTreePropertiesAfterInsert(insertedNewNode);

        return this.insertHelper(insertedNewNode);
    }

    /**
     * This test method checks the behavior of the Red-Black Tree (RBT) insertion
     * when elements are inserted in ascending order. It ensures that the RBT correctly
     * balances itself and maintains the Red-Black Tree properties after inserting
     * elements in sequential, increasing order.
     */
    @Test
    public void testInsertionAscendingOrder() {
        // Create a new Red-Black Tree instance
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Insert elements in ascending order
        rbt.insert(1);
        rbt.insert(2);
        rbt.insert(3);
        rbt.insert(4);
        rbt.insert(5);

        // Define the expected level-order traversal
        String expected = "[ 2, 1, 4, 3, 5 ]";

        // Verify that the level-order traversal matches the expected result
        assertTrue(rbt.toLevelOrderString().equals(expected));
    }

    /**
     * This test method checks the behavior of the Red-Black Tree (RBT) insertion
     * when elements are inserted in descending order. It ensures that the RBT correctly
     * balances itself and maintains the Red-Black Tree properties after inserting
     * elements in sequential, decreasing order.
     */
    @Test
    public void testInsertionDescendingOrder() {
        // Create a new Red-Black Tree instance
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Insert elements in descending order
        rbt.insert(5);
        rbt.insert(4);
        rbt.insert(3);
        rbt.insert(2);
        rbt.insert(1);

        // Define the expected level-order traversal
        String expected = "[ 4, 2, 5, 1, 3 ]";

        // Verify that the level-order traversal matches the expected result
        assertTrue(rbt.toLevelOrderString().equals(expected));
    }

    /**
     * This test method checks the behavior of the Red-Black Tree (RBT) insertion
     * when elements are inserted in a random, non-sequential order. It ensures that
     * the RBT correctly balances itself and maintains the Red-Black Tree properties
     * after inserting elements in a random order.
     */
    @Test
    public void testInsertionRandomOrder() {
        // Create a new Red-Black Tree instance
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Insert elements in a random order
        rbt.insert(2);
        rbt.insert(5);
        rbt.insert(4);
        rbt.insert(1);
        rbt.insert(3);

        // Define the expected level-order traversal
        String expected = "[ 4, 2, 5, 1, 3 ]";

        // Verify that the level-order traversal matches the expected result
        assertTrue(rbt.toLevelOrderString().equals(expected));
    }

    /**
     * This test method checks the behavior of the Red-Black Tree (RBT) insertion
     * when elements are inserted in a random, non-sequential order that tests the third case of insertion.
     * It ensures that the RBT correctly balances itself and maintains the Red-Black Tree properties
     * after inserting elements in a random order.
     */
    @Test
    public void testInsertionCase3() {
        // Create a new Red-Black Tree instance
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        // Insert elements in a random order
        rbt.insert(2);
        rbt.insert(5);
        rbt.insert(4);
        rbt.insert(1);
        rbt.insert(3);
        rbt.insert(6);

        // Define the expected level-order traversal
        String expected = "[ 4, 2, 5, 1, 3, 6 ]";

        // Verify that the level-order traversal matches the expected result
        assertTrue(rbt.toLevelOrderString().equals(expected));
    }

}
