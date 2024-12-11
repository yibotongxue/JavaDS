package datastructures.sorting;

import java.util.Collections;
import java.util.PriorityQueue;

public class HeapSort {
    // TODO Should implement the max heap manually.
    public static <T extends Comparable<T>> void sort(T[] array) {
        PriorityQueue<T> heap = new PriorityQueue<>(Collections.reverseOrder());
        Collections.addAll(heap, array);
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = heap.poll();
        }
    }
}
