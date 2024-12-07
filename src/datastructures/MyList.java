package datastructures;

/**
 * A generic list interface providing basic list operations.
 * @param <T>
 */
public interface MyList<T> {
    /**
     * Clears all elements from the list.
     */
    void clear();

    /**
     * Check if the list is empty.
     * @return true if the list is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Append a value in the end of the list.
     * @param value The value to be appended to the list.
     */
    void append(final T value);

    /**
     * Insert a value to a position of the list.
     * @param position The position of the list the value will be inserted to.
     * @param value The value to insert to the list.
     */
    void insert(final int position, final T value);

    /**
     * Delete the value in the position of the list.
     * @param position The position to delete.
     * @return The value on the deleted position.
     */
    T delete(final int position);

    /**
     * Get the value in the position of the list.
     * @param position The position of the value to get.
     * @return The value on the position of the list.
     */
    T getValue(final int position);

    /**
     * Set the value on the position to another value.
     * @param position The position to set the value.
     * @param value The value to be set to the list.
     */
    void setValue(final int position, final T value);

    /**
     * Get the position of the value in the list.
     * @param value The value to query.
     * @return the position of the value in the list if the value is in the list, -1 otherwise.
     */
    int getPosition(final T value);
}
