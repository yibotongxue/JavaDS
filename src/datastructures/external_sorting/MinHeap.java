package datastructures.external_sorting;

import java.util.ArrayList;
import java.util.Collections;

class MinHeap<T extends Comparable<T>> {
    private final ArrayList<T> data;
    int capacity;
    int heapSize;
    int totalSize;

    MinHeap(int capacity) {
        data = new ArrayList<>(Collections.nCopies(capacity, null));
        this.capacity = capacity;
        heapSize = 0;
        totalSize = 0;
    }

    void add(T value) {
        if (heapSize == capacity) {
            throw new IndexOutOfBoundsException("Can't add an element to a full heap.");
        }
        data.set(heapSize, value);
        heapSize++;
        totalSize++;
        swimUp(heapSize - 1);
    }

    T replace(T value) {
        if (heapSize == 0) {
            throw new IndexOutOfBoundsException("Can't replace a empty heap.");
        }
        T result = data.getFirst();
        if (less(value, result)) {
            data.set(0, data.get(heapSize - 1));
            data.set(heapSize - 1, value);
            heapSize--;
        } else {
            data.set(0, value);
        }
        sinkDown(0);
        return result;
    }

    T poll() {
        if (heapSize == 0) {
            throw new IndexOutOfBoundsException("Can't poll form an empty heap.");
        }
        T result = data.getFirst();
        data.set(0, data.get(heapSize - 1));
        data.set(heapSize - 1, null);
        heapSize--;
        totalSize--;
        sinkDown(0);
        return result;
    }

    int getHeapSize() {
        return heapSize;
    }

    boolean isEmpty() {
        return heapSize == 0;
    }

    boolean isFull() {
        return heapSize == capacity;
    }

    void clear() {
        heapSize = 0;
    }

    void reBuild() {
        heapSize = totalSize;
        for (int i = heapSize / 2 - 1; i >= 0; i--) {
            sinkDown(i);
        }
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
        if (left_child >= heapSize) {
            return;
        }
        if (right_child >= heapSize) {
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
