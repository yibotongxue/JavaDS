package datastructures.sorting;

public class MergeSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        mergeSort(array, 0, array.length);
    }

    // [left, right)
    private static <T extends Comparable<T>> void mergeSort(T[] array, int left, int right) {
        if (left >= right - 1) {
            return;
        }
        int middle = (left + right) / 2;
        mergeSort(array, left, middle);
        mergeSort(array, middle, right);
        merge(array, left, middle, right);
    }

    // [left, middle), [middle, right)
    private static <T extends Comparable<T>> void merge(T[] array, int left, int middle, int right) {
        T[] tempArray = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), right - left);
        System.arraycopy(array, left, tempArray, 0, right - left);
        int i = 0, j = middle - left;
        int k = left;
        while (i < middle - left && j < right - left) {
            if (less(tempArray[j], tempArray[i])) {
                array[k++] = tempArray[j++];
            } else {
                array[k++] = tempArray[i++];
            }
        }
        while (i < middle - left) {
            array[k++] = tempArray[i++];
        }
        while (j < right - left) {
            array[k++] = tempArray[j++];
        }
    }

    private static <T extends Comparable<T>> boolean less(T lhs, T rhs) {
        return lhs.compareTo(rhs) < 0;
    }
}
