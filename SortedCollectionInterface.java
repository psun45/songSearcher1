public interface SortedCollectionInterface<T extends Comparable<T>> {

    /**
     * Inserts the specified data into the collection.
     *
     * @param data the data to be inserted
     * @return true if the insertion was successful, false otherwise
     * @throws NullPointerException if the specified data is null
     * @throws IllegalArgumentException if the specified data is not compatible for comparison
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException;

    /**
     * Checks if the collection contains the specified data.
     *
     * @param data the data to be checked for existence
     * @return true if the collection contains the data, false otherwise
     */
    public boolean contains(Comparable<T> data);

    /**
     * Returns the size of the collection.
     *
     * @return the number of elements in the collection
     */
    public int size();

    /**
     * Checks if the collection is empty.
     *
     * @return true if the collection is empty, false otherwise
     */
    public boolean isEmpty();

    /**
     * Clears all elements from the collection.
     */
    public void clear();
}

