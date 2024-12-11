package datastructures.sorting;

public class BubbleSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length; i++) {
            boolean hasSwap = false;
            for (int j = array.length - 1; j > i; j--) {
                if (less(array[j], array[j - 1])) {
                    swap(array, j, j - 1);
                    hasSwap = true;
                }
            }
            if (!hasSwap) {
                return;
            }
        }
    }

    private static <T extends Comparable<T>> boolean less(final T lhs, final T rhs) {
        return lhs.compareTo(rhs) < 0;
    }

    private static <T> void swap(T[] array, final int i, final int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
