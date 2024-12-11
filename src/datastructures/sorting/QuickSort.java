package datastructures.sorting;

public class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        sortFromTo(array, 0, array.length - 1);
    }

    private static <T extends Comparable<T>> void sortFromTo(T[] array, int from, int to) {
        if (from >= to) {
            return;
        }
        int pivot = selectPivot(from, to);
        swap(array, pivot, to);
        int pivotPos = partition(array, from, to);
        sortFromTo(array, from, pivotPos - 1);
        sortFromTo(array, pivotPos + 1, to);
    }

    private static int selectPivot(int left, int right) {
        return (left + right) / 2;
    }

    /**
     * Sort array from `from` to `to`, [from, to]
     * @param array the array to sort
     * @param from the beginning position
     * @param to then end position, included
     * @param <T> comparable type
     * @return the pivot position
     */
    private static <T extends Comparable<T>> int partition(T[] array, int from, int to) {
        int left_idx = from, right_idx = to;
        T temp = array[right_idx];
        while (left_idx != right_idx) {
            // find the first element that need to be moved to right
            while (less_or_equal(array[left_idx], temp) && right_idx > left_idx) {
                left_idx++;
            }
            if (left_idx < right_idx) {
                array[right_idx] = array[left_idx];
                right_idx--;
            }
            // find the first element that need to be moved to left
            while (less_or_equal(temp, array[right_idx]) && right_idx > left_idx) {
                right_idx--;
            }
            if (left_idx < right_idx) {
                array[left_idx] = array[right_idx];
                left_idx++;
            }
        }
        array[left_idx] = temp;
        return left_idx;
    }

    private static <T extends Comparable<T>> boolean less_or_equal(final T lhs, final T rhs) {
        return lhs.compareTo(rhs) <= 0;
    }

    private static <T> void swap(T[] array, final int i, final int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
