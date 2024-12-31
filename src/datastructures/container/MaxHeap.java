package datastructures.container;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {
    private final List<T> list;

    public MaxHeap() {
        list = new ArrayList<>();
    }

    public MaxHeap(List<T> list) {
        this.list = new ArrayList<>(list);
        for (int i = this.list.size() / 2 - 1; i >= 0; i--) {
            ShiftDown(i);
        }
    }

    public MaxHeap(T[] list) {
        this.list = new ArrayList<>();
        Collections.addAll(this.list, list);
        for (int i = this.list.size() / 2 - 1; i >= 0; i--) {
            ShiftDown(i);
        }
    }

    public T getMax() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Can't get min from a empty heap");
        }
        return list.getFirst();
    }

    public void push(T value) {
        list.add(value);
        ShiftUp(list.size() - 1);
    }

    public T pop() {
        if (list.isEmpty()) {
            throw new IndexOutOfBoundsException("Can't pop from a empty heap");
        }
        T result = list.getFirst();
        swap(0, list.size() - 1);
        list.removeLast();
        ShiftDown(0);
        return result;
    }

    private void ShiftUp(int i) {
        if (i == 0) {
            return;
        }
        int p = getParentIdx(i);
        if (greater(get(i), get(p))) {
            swap(i, p);
            ShiftUp(p);
        }
    }

    private void ShiftDown(int i) {
        int left_idx = i * 2 + 1;
        int right_idx = i * 2 + 2;
        if (left_idx >= list.size()) {
            return;
        }
        if (right_idx >= list.size()) {
            if (greater(get(left_idx), get(i))) {
                swap(left_idx, i);
                ShiftDown(left_idx);
            }
            return;
        }
        if (greater(get(left_idx), get(right_idx))) {
            if (greater(get(left_idx), get(i))) {
                swap(i, left_idx);
                ShiftDown(left_idx);
            }
        } else {
            if (greater(get(right_idx), get(i))) {
                swap(i, right_idx);
                ShiftDown(right_idx);
            }
        }
    }

    private T get(int i) {
        return list.get(i);
    }

    private static int getParentIdx(int i) {
        return (i - 1) / 2;
    }

    private boolean greater(T lhs, T rhs) {
        return lhs.compareTo(rhs) > 0;
    }

    private void swap(int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
