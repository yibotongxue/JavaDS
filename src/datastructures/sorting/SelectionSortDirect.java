package datastructures.sorting;

public class SelectionSortDirect {
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int ith_smallest = find_ith_smallest_pos_from_i(array, i);
            swap(array, i, ith_smallest);
        }
    }

    private static <T extends Comparable<T>> int find_ith_smallest_pos_from_i(T[] array, int i) {
        int result = i;
        for (int j = i + 1; j < array.length; j++) {
            if (less(array[j], array[result])) {
                result = j;
            }
        }
        return result;
    }

    private static <T> void swap(T[] array, int i, int j) {
        if (i == j) {
            return;
        }
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static <T extends  Comparable<T>> boolean less(T lhs, T rhs) {
        return lhs.compareTo(rhs) < 0;
    }
}
