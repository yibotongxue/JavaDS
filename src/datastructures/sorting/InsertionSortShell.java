/**
 * 单元测试代码基于 ChatGPT 提供的建议进行编写和修改。
 */

package datastructures.sorting;

public class InsertionSortShell {
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int delta = array.length / 2; delta > 0; delta /= 2) {
            for (int i = 0; i < delta; i++) {
                ShellSortFrom(array, i, delta);
            }
        }
    }

    private static <T extends Comparable<T>> void ShellSortFrom(T[] array, int begin, int delta) {
        for (int i = begin + delta; i < array.length; i += delta) {
            T temp = array[i];
            int j = i - delta;
            while (j >= begin && less(temp, array[j])) {
                array[j + delta] = array[j];
                j -= delta;
            }
            array[j + delta] = temp;
        }
    }

    private static <T extends  Comparable<T>> boolean less(T lhs, T rhs) {
        return lhs.compareTo(rhs) < 0;
    }
}
