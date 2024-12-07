package datastructures;

import java.util.Iterator;

public class MyArrayList<T> implements MyList<T> {
    private T[] values;
    private int size;

    /**
     * The constructor of array list.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        values = (T[]) new Object[8];
        size = 0;
    }

    /**
     * Clears all elements from the list.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        size = 0;
        values = (T[]) new Object[8];
    }

    /**
     * Resize the array to newSize.
     * @param newSize The new size of the array.
     */
    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        T[] temp = (T[]) new Object[newSize];
        System.arraycopy(values, 0, temp, 0, size);
        values = temp;
    }

    /**
     * Check if the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    @Override
    final public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Append a value in the end of the list.
     *
     * @param value The value to be appended to the list.
     */
    @Override
    public void append(T value) {
        if (size == values.length) {
            resize(size * 2);
        }
        values[size++] = value;
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
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        if (size == values.length) {
            resize(2 * size);
        }
        for (int index = size; index > position; index--) {
            values[index] = values[index - 1];
        }
        values[position] = value;
        size++;
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
        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException("Position out of bounds");
        }
        T temp = values[position];
        for (int index = position; index < size - 1; index++) {
            values[index] = values[index + 1];
        }
        size--;
        if (values.length >= 16 && size < values.length / 4) {
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
        if (position < 0 || position >= size) {
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
        if (position < 0 || position >= size) {
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
        for (int i = 0; i < size; i++) {
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
        return size;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return values[index++];
            }
        };
    }
}
