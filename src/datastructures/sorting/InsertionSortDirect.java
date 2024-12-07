package datastructures.sorting;

public class InsertionSortDirect {
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= 0 && less(key, array[j])) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    private static <T extends  Comparable<T>> boolean less(T lhs, T rhs) {
        return lhs.compareTo(rhs) < 0;
    }
}
