package datastructures;

public class MyArrayList<T> implements MyList<T> {
    private T[] values;
    private int current_length;

    /**
     * The constructor of array list.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        values = (T[]) new Object[8];
        current_length = 0;
    }

    /**
     * Clears all elements from the list.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        current_length = 0;
        values = (T[]) new Object[8];
    }

    /**
     * Resize the array to newSize.
     * @param newSize The new size of the array.
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] temp = (T[]) new Object[newSize];
        System.arraycopy(values, 0, temp, 0, current_length);
        values = temp;
    }

    /**
     * Check if the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    @Override
    final public boolean isEmpty() {
        return current_length == 0;
    }

    /**
     * Append a value in the end of the list.
     *
     * @param value The value to be appended to the list.
     */
    @Override
    public void append(T value) {
        if (current_length == values.length) {
            resize(current_length * 2);
        }
        values[current_length++] = value;
    }

    /**
     * Insert a value to a position of the list.
     *
     * @param position The position of the list the value will be inserted to.
     * @param value    The value to insert to the list.
     * @throws IndexOutOfBoundsException if the position is out of the bounds
     */
    @Override
    public void insert(int position, T value) {
        if (position < 0 || position > current_length) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        if (current_length == values.length) {
            resize(2 * current_length);
        }
        for (int index = current_length; index > position; index--) {
            values[index] = values[index - 1];
        }
        values[position] = value;
        current_length++;
    }

    /**
     * Delete the value in the position of the list.
     *
     * @param position The position to delete.
     * @return The value on the deleted position.
     * @throws IndexOutOfBoundsException if the position is out of the bounds
     */
    @Override
    public T delete(int position) {
        if (position < 0 || position >= current_length) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        T temp = values[position];
        for (int index = position; index < current_length - 1; index++) {
            values[index] = values[index + 1];
        }
        current_length--;
        if (values.length >= 16 && current_length < values.length / 4) {
            resize(values.length / 2);
        }
        return temp;
    }

    /**
     * Get the value in the position of the list.
     *
     * @param position The position of the value to get.
     * @return The value on the position of the list.
     * @throws IndexOutOfBoundsException if the position is out of the bounds
     */
    @Override
    final public T getValue(int position) {
        if (position < 0 || position >= current_length) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        return values[position];
    }

    /**
     * Set the value on the position to another value.
     *
     * @param position The position to set the value.
     * @param value    The value to be set to the list.
     * @throws IndexOutOfBoundsException if the position is out of the bounds
     */
    @Override
    public void setValue(int position, T value) {
        if (position < 0 || position >= current_length) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        values[position] = value;
    }

    /**
     * Get the position of the value in the list.
     *
     * @param value The value to query.
     * @return the position of the value in the list if the value is in the list, -1 otherwise.
     */
    @Override
    public int getPosition(T value) {
        for (int i = 0; i < current_length; i++) {
            if (values[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Get the size of the list.
     *
     * @return the size of the list.
     */
    @Override
    final public int getSize() {
        return current_length;
    }
}
