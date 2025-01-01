package datastructures.external_sorting;

/**
 * A min heap implementation for external sorting. I make it
 * package-private to avoid exposing it to the user for I think
 * it's not necessary to be used outside the package.
 *
 * @param <T> The type of the elements in the heap.
 */
class MinHeap<T extends Comparable<T>> {
    private final T[] data;
    private final int capacity;
    private int heapSize;
    private int totalSize;

    MinHeap(int capacity) {
        //noinspection unchecked
        data = (T[]) new Comparable[capacity];
        this.capacity = capacity;
        heapSize = 0;
        totalSize = 0;
    }

    /**
     * Add a value to the heap.
     * @param value The value to be added.
     */
    void add(T value) {
        if (heapSize == capacity) {
            throw new IndexOutOfBoundsException("Can't add an element to a full heap.");
        }
        data[heapSize] = value;
        heapSize++;
        totalSize++;
        swimUp(heapSize - 1);
    }

    /**
     * Replace the root of the heap with a new value.
     * @param value The new value to replace the root.
     * @return The old root value.
     */
    T replace(T value) {
        if (heapSize == 0) {
            throw new IndexOutOfBoundsException("Can't replace a empty heap.");
        }
        T result = data[0];
        if (less(value, result)) {
            data[0] = data[heapSize - 1];
            data[heapSize - 1] = value;
            heapSize--;
        } else {
            data[0] = value;
        }
        sinkDown(0);
        return result;
    }

    /**
     * Get the root value of the heap.
     * @return The root value.
     */
    T poll() {
        if (heapSize == 0) {
            throw new IndexOutOfBoundsException("Can't poll form an empty heap.");
        }
        T result = data[0];
        data[0] = data[heapSize - 1];
        data[heapSize - 1] = null;
        heapSize--;
        totalSize--;
        sinkDown(0);
        return result;
    }

    int getHeapSize() {
        return heapSize;
    }

    /**
     * Check if the heap is empty.
     * @return True if the heap is empty, false otherwise.
     */
    boolean isEmpty() {
        return heapSize == 0;
    }

    /**
     * Check if the heap is full.
     * @return True if the heap is full, false otherwise.
     */
    boolean isFull() {
        return heapSize == capacity;
    }

    /**
     * Rebuild the heap.
     */
    void reBuild() {
        heapSize = totalSize;
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            sinkDown(i);
        }
    }

    /**
     * Get the root value of the heap.
     *
     * @param i The index of the value to swim up.
     */
    private void swimUp(int i) {
        if (i == 0) {
            return;
        }
        int parent = (i - 1) / 2;
        if (less(data[i], data[parent])) {
            swap(i, parent);
            swimUp(parent);
        }
    }

    /**
     * Sink down the value at index i.
     * @param i The index of the value to sink down.
     */
    private void sinkDown(int i) {
        int left_child = i * 2 + 1;
        int right_child = i * 2 + 2;
        if (left_child >= heapSize) {
            return;
        }
        if (right_child >= heapSize) {
            if (less(data[left_child], data[i])) {
                swap(i, left_child);
                sinkDown(left_child);
            }
            return;
        }
        if (less(data[left_child], data[right_child])) {
            if (less(data[left_child], data[i])) {
                swap(left_child, i);
                sinkDown(left_child);
            }
        } else if (less(data[right_child], data[i])) {
            swap(right_child, i);
            sinkDown(right_child);
        }
    }

    private boolean less(T lhs, T rhs) {
        return lhs.compareTo(rhs) < 0;
    }

    private void swap(int i, int j) {
        T temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}
