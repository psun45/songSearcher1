import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
//placeholder for RBT
/**
 * Represents a collection that stores elements of type T and allows for sorting and retrieval based on multiple keys.
 *
 * @param <T> The type of elements stored in the collection, which must implement Comparable.
 */
public class BackendIterableMultiKeySortedCollection<T extends Comparable<T>> implements IterableMultiKeySortedCollectionInterface {

    private List<T> tree = new ArrayList<>();

    /**
     * Inserts an element with a single key into the collection.
     *
     * @param key The key to be associated with the element.
     * @return true if the element is successfully added, false otherwise.
     */
    @Override
    public boolean insertSingleKey(Comparable key) {
        return tree.add((T) key);
    }

    /**
     * Returns the number of keys in the collection.
     *
     * @return The number of keys in the collection.
     */
    @Override
    public int numKeys() {
        return tree.size();
    }

    /**
     * Returns an iterator over the elements in the collection.
     *
     * @return An iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return tree.iterator();
    }

    /**
     * Sets the iteration start point for the collection.
     *
     * @param startPoint The starting point for iteration.
     */
    @Override
    public void setIterationStartPoint(Comparable startPoint) {
        // No-op for this hard-coded version
    }

    /**
     * Inserts an element into the collection.
     *
     * @param data The element to be inserted.
     * @return true if the element is successfully added, false otherwise.
     * @throws NullPointerException      if the specified element is null.
     * @throws IllegalArgumentException  if some property of the element prevents it from being added to this collection.
     */
    @Override
    public boolean insert(Comparable data) throws NullPointerException, IllegalArgumentException {
        return tree.add((T) data);
    }

    /**
     * Checks if the collection contains a specified element.
     *
     * @param data The element to check for containment.
     * @return true if the collection contains the element, false otherwise.
     */
    @Override
    public boolean contains(Comparable data) {
        return tree.contains((T) data);
    }

    /**
     * Returns the number of elements in the collection.
     *
     * @return The number of elements in the collection.
     */
    @Override
    public int size() {
        return tree.size();
    }

    /**
     * Checks if the collection is empty.
     *
     * @return true if the collection contains no elements, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    /**
     * Removes all elements from the collection.
     */
    @Override
    public void clear() {
        tree.clear();
    }
}