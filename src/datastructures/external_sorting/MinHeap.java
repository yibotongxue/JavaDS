package datastructures.external_sorting;

import java.util.ArrayList;
import java.util.Collections;

class MinHeap<T extends Comparable<T>> {
    private final ArrayList<T> data;
    int capacity;
    int size;

    MinHeap(int capacity) {
        data = new ArrayList<>(Collections.nCopies(capacity, null));
        this.capacity = capacity;
        size = 0;
    }

    void add(T value) {
        if (size == capacity) {
            throw new IndexOutOfBoundsException("Can't add an element to a full heap.");
        }
        data.set(size, value);
        size++;
        swimUp(size - 1);
    }

    T replace(T value) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Can't replace a empty heap.");
        }
        T result = data.getFirst();
        if (less(result, value)) {
            data.set(0, data.get(size - 1));
            data.set(size - 1, value);
            size--;
        } else {
            data.set(0, value);
        }
        sinkDown(0);
        return result;
    }

    T poll() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Can't poll form an empty heap.");
        }
        T result = data.getFirst();
        data.set(0, data.get(size - 1));
        data.set(size - 1, null);
        size--;
        sinkDown(0);
        return result;
    }

    int getSize() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }

    private void swimUp(int i) {
        if (i == 0) {
            return;
        }
        int parent = (i - 1) / 2;
        if (less(data.get(i), data.get(parent))) {
            swap(i, parent);
            swimUp(parent);
        }
    }

    private void sinkDown(int i) {
        int left_child = i * 2 + 1;
        int right_child = i * 2 + 2;
        if (left_child >= size) {
            return;
        }
        if (right_child >= size) {
            if (less(data.get(left_child), data.get(i))) {
                swap(i, left_child);
                sinkDown(left_child);
            }
            return;
        }
        if (less(data.get(left_child), data.get(right_child))) {
            if (less(data.get(left_child), data.get(i))) {
                swap(left_child, i);
                sinkDown(left_child);
            }
        } else if (less(data.get(right_child), data.get(i))) {
            swap(right_child, i);
            sinkDown(right_child);
        }
    }

    private boolean less(T lhs, T rhs) {
        return lhs.compareTo(rhs) < 0;
    }

    private void swap(int i, int j) {
        T temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }
}
