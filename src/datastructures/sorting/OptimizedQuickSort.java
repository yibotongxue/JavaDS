package datastructures.sorting;

public class OptimizedQuickSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        modQuickSort(array, 0, array.length - 1);
        InsertionSortDirect.sort(array);
    }

    private static <T extends Comparable<T>> void modQuickSort(T[] array, int left, int right) {
        if (right - left + 1 > 28) {
            int pivot = selectPivot(left, right);
            swap(array, pivot, right);
            pivot = partition(array, left, right);
            modQuickSort(array, left, pivot - 1);
            modQuickSort(array, pivot + 1, right);
        }
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
