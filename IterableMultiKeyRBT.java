// --== CS400 Fall 2023 File Header Information ==--
// Name: ALEX SCHMITT
// Email: ATSCHMITT2@wisc.edu
// Group: B25
// TA: ZHEYANG XIONG
// Lecturer: GARY DAHL
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IterableMultiKeyRBT<T extends Comparable<T>> extends
    BinarySearchTree<KeyListInterface<T>> implements IterableMultiKeySortedCollectionInterface<T> {

  private Comparable<T> iterationStartPoint;
  private int numberOfKeys;

  @Override
  /**
   * Inserts value into tree that can store multiple objects per key by keeping lists of objects in
   * each node of the tree.
   * 
   * @param key object to insert
   * @return true if a new node was inserted, false if the key was added into an existing node
   */
  public boolean insertSingleKey(T key) {
    // Create a KeyList with the new key.
    KeyList<T> newKeyList = new KeyList<T>(key);

    // Find a node containing duplicates using the findNode method.
    BinarySearchTree.Node<KeyListInterface<T>> existingNode = findNode(newKeyList);
    numberOfKeys++;

    if (existingNode != null) {
      // Node with duplicates exists; add the key to the KeyList.
      existingNode.data.addKey(key);
      return false; // Duplicates found, not inserted.
    } else {
      // Node with duplicates doesn't exist; insert the new KeyList.
      insert(newKeyList); // You should have an insert method to insert the new node.
      return true; // New KeyList inserted.
    }
  }

  @Override
  /**
   * @return the number of values in the tree.
   */
  public int numKeys() {
    return numberOfKeys;
  }

  @Override
  /**
   * Returns an iterator that does an in-order iteration over the tree.
   */
  public Iterator<T> iterator() {
    // Initialize a stack of nodes using the getStartStack method.
    Stack<BinarySearchTree.Node<KeyListInterface<T>>> stack = getStartStack();

    // Create an anonymous class implementing the Iterator<T> interface.
    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        // Check if the stack is not empty, which means there are more keys to iterate.
        return !stack.isEmpty();
      }

      @Override
      public T next() {
        if (!hasNext()) {
          // If there are no more elements in the tree, throw a NoSuchElementException.
          throw new NoSuchElementException("No more elements in the tree.");
        }

        // Pop the top node from the stack for processing.
        BinarySearchTree.Node<KeyListInterface<T>> current = stack.pop();
        BinarySearchTree.Node<KeyListInterface<T>> next = null;
        
        T nextKey = current.data.iterator().next();

        
        if (!stack.empty()) {
          next = stack.peek();
        }
        
        if (next != null) {
          if (current.data.iterator().next() == next.data.iterator().next()) {
            return nextKey;
          }
        }



        if (current.down[1] != null) {
          //stack.push(current.down[1]);
          BinarySearchTree.Node<KeyListInterface<T>> tempCurrent = current.down[1];
          Iterator<T> keyListIterator01 = tempCurrent.data.iterator();
          
          //int numKeys01 = 0;
          while(keyListIterator01.hasNext()) {
            keyListIterator01.next();
           // ++numKeys01;
            stack.push(current.down[1]);
          }
          

          while (tempCurrent.down[0] != null) {
            Iterator<T> keyListIterator02 = tempCurrent.down[0].data.iterator();
            while (keyListIterator02.hasNext()) {
              stack.push(tempCurrent.down[0]);
              keyListIterator02.next();
            }
            tempCurrent = tempCurrent.down[0];
          }
        }

        return nextKey;
      }


    };
  }

  @Override
  /**
   * Sets the starting point for iterations. Future iterations will start at the starting point or
   * the key closest to it in the tree. This setting is remembered until it is reset. Passing in
   * null disables the starting point.
   * 
   * @param startPoint the start point to set for iterations
   */
  public void setIterationStartPoint(Comparable<T> startPoint) {
    this.iterationStartPoint = startPoint;
  }

  @Override
  /**
   * override the clear method of the tree. In the new clear method, call the old clear method of
   * BinarySearchTree, then add a line that sets the field that counts the number of keys to zero.
   */
  public void clear() {
    // Call the clear method of the BinarySearchTree to clear the tree.
    super.clear();

    // Reset the field that counts the number of keys to zero.
    numberOfKeys = 0;
  }

  protected Stack<Node<KeyListInterface<T>>> getStartStack() {
    Stack<Node<KeyListInterface<T>>> stack = new Stack<>();
    BinarySearchTree.Node<KeyListInterface<T>> currentNode = root; // Start from the root

    // If the iteration start point is set
    if (iterationStartPoint != null) {

      while (currentNode != null) {

        // Check if currentNode.data is not null
        if (currentNode.data != null) {

          // Compare the start point with the first element in the duplicate list
          Iterator<T> iterator = currentNode.data.iterator();
          if (iterator.hasNext()) {
            T firstElement = iterator.next();
            int comparison = iterationStartPoint.compareTo(firstElement);

            if (comparison == 0) {
              // If start point is found, push it to the stack and break.
              Iterator<T> keyListIterator01 = currentNode.data.iterator();
              while (keyListIterator01.hasNext()) {
                stack.push(currentNode);
                keyListIterator01.next();
              }
              break;
            }

            else if (comparison < 0) {
              // Start point is smaller, so move left if available.
              if (currentNode.down[0] != null) {
                
                Iterator<T> keyListIterator02 = currentNode.data.iterator();
                while (keyListIterator02.hasNext()) {
                  stack.push(currentNode);
                  keyListIterator02.next();
                }
        
                currentNode = currentNode.down[0];
              }

              else {
                Iterator<T> keyListIterator02 = currentNode.data.iterator();
                while (keyListIterator02.hasNext()) {
                  stack.push(currentNode);
                  keyListIterator02.next();
                }
                break;
              }
            }

            else {
              // Start point is bigger, so move right if available
              if (currentNode.down[1] != null) {
                // stack.push(currentNode);
                currentNode = currentNode.down[1];
              }

              else {
                // Neither left nor right child is available; break.
                break;
              }
            }
          }
        }
      }
    }

    // If no iteration start point is set, push nodes along the left path (smallest key).
    
    if (iterationStartPoint == null) {
      while (currentNode != null) {
        Iterator<T> keyListIterator03 = currentNode.data.iterator();
        while (keyListIterator03.hasNext()) {
          stack.push(currentNode);
          keyListIterator03.next();
        }
        currentNode = currentNode.down[0];
      }
    }

    return stack;
  }


  /**
   * 
   * Test Inorder Traversal:
   * In a Binary Search Tree, the inorder traversal should yield elements in sorted order. This test
   * inserts duplicate and non-duplicate keys and tests them on an Iterator with no specified start 
   * point.
   *    
   */
  @Test
  public void testInorderTraversal01() {

    // Create a Red-Black Tree and insert elements
    IterableMultiKeyRBT<Integer> rbt = new IterableMultiKeyRBT<>();
    rbt.insertSingleKey(10);
    rbt.insertSingleKey(5);
    rbt.insertSingleKey(15);
    rbt.insertSingleKey(3);
    rbt.insertSingleKey(7);
    rbt.insertSingleKey(20);
    rbt.insertSingleKey(5);
    rbt.insertSingleKey(4);
    rbt.insertSingleKey(8);
    rbt.insertSingleKey(5);
    rbt.insertSingleKey(5);
    rbt.insertSingleKey(13);
    rbt.insertSingleKey(17);
    rbt.insertSingleKey(20);
    rbt.insertSingleKey(1);
    rbt.insertSingleKey(1);

    Iterator<Integer> iterator = rbt.iterator();
    List<Integer> result = new ArrayList<>();

    while (iterator.hasNext()) {
      result.add(iterator.next());
    }

    // Check if the elements are in ascending order
    List<Integer> expected = Arrays.asList(1, 1, 3, 4, 5, 5, 5, 5, 7, 8, 10, 13, 15, 17, 20, 20);
    assertEquals(expected, result);
  }
  
  

  /**
   * 
   *   Test Empty Tree Iterator:
   *   Test whether the iterator behaves correctly when the Red-Black Tree is empty. It should not 
   *   throw exceptions and should indicate that there are no elements to traverse.
   *    
   */
  @Test
  public void testEmptyTreeIterator() {
    IterableMultiKeyRBT<Integer> rbt = new IterableMultiKeyRBT<>();
    Iterator<Integer> iterator = rbt.iterator();

    assertFalse(iterator.hasNext());
  }

  
  
  /**
   * 
   * Test Inorder Traversal:
   * In a Binary Search Tree, the inorder traversal should yield elements in sorted order. This test
   * inserts only non-duplicate keys and tests them on an Iterator with a specified start 
   * point to the left of the root node but in the tree.
   *    
   */
  @Test
  public void testInorderTraversal02() {

    // Create a Red-Black Tree and insert elements
    IterableMultiKeyRBT<Integer> rbt = new IterableMultiKeyRBT<>();
    rbt.insertSingleKey(10);
    rbt.insertSingleKey(15);
    rbt.insertSingleKey(3);
    rbt.insertSingleKey(7);
    rbt.insertSingleKey(20);
    rbt.insertSingleKey(5);
    rbt.insertSingleKey(4);
    rbt.insertSingleKey(8);
    rbt.insertSingleKey(13);
    rbt.insertSingleKey(17);
    rbt.insertSingleKey(1);
    
    rbt.setIterationStartPoint(5);

    Iterator<Integer> iterator = rbt.iterator();
    List<Integer> result = new ArrayList<>();

    while (iterator.hasNext()) {
      result.add(iterator.next());
    }

    // Check if the elements are in ascending order
    List<Integer> expected = Arrays.asList(5, 7, 8, 10, 13, 15, 17, 20);
    assertEquals(expected, result);
  }
 
  
  /**
   * 
   * Test Inorder Traversal:
   * In a Binary Search Tree, the inorder traversal should yield elements in sorted order. This test
   * inserts duplicate and non-duplicate keys and tests them on an Iterator with a specified start 
   * point to the right of the root node and not in the tree.
   *    
   */
  @Test
  public void testInorderTraversal03() {

    // Create a Red-Black Tree and insert elements
    IterableMultiKeyRBT<Integer> rbt = new IterableMultiKeyRBT<>();
    rbt.insertSingleKey(10);
    rbt.insertSingleKey(15);
    rbt.insertSingleKey(3);
    rbt.insertSingleKey(3);
    rbt.insertSingleKey(7);
    rbt.insertSingleKey(20);
    rbt.insertSingleKey(20);
    rbt.insertSingleKey(20);
    rbt.insertSingleKey(5);
    rbt.insertSingleKey(4);
    rbt.insertSingleKey(8);
    rbt.insertSingleKey(13);
    rbt.insertSingleKey(13);
    rbt.insertSingleKey(17);
    rbt.insertSingleKey(17);
    rbt.insertSingleKey(1);
    
    rbt.setIterationStartPoint(12);

    Iterator<Integer> iterator = rbt.iterator();
    List<Integer> result = new ArrayList<>();

    while (iterator.hasNext()) {
      result.add(iterator.next());
    }

    // Check if the elements are in ascending order
    List<Integer> expected = Arrays.asList(13, 13, 15, 17, 17, 20, 20, 20);
    assertEquals(expected, result);
  } 
  
  /**

  public static void main(String[] args) {
    // Create a Red-Black Tree and insert elements
    IterableMultiKeyRBT<Integer> rbt = new IterableMultiKeyRBT<>();
    rbt.insertSingleKey(10);
    rbt.insertSingleKey(15);
    rbt.insertSingleKey(3);
    rbt.insertSingleKey(5);
    rbt.insertSingleKey(7);
    rbt.insertSingleKey(20);
    rbt.insertSingleKey(20);
    
    rbt.setIterationStartPoint(13);
    
    System.out.println(rbt.size);
    System.out.println(rbt.numKeys());


    Iterator<Integer> iterator = rbt.iterator();
    List<Integer> result = new ArrayList<>();

    while (iterator.hasNext()) {
      result.add(iterator.next());
    }
    System.out.println(result);
  }
  
*/
}


