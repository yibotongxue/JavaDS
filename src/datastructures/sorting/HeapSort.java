package datastructures.sorting;

import datastructures.container.MaxHeap;


public class HeapSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        MaxHeap<T> heap = new MaxHeap<>(array);
        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = heap.pop();
        }
    }
}
