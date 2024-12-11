package datastructures.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class OptimizedQuickSortTest {

    @Test
    void testSortEmptyArray() {
        Integer[] array = {};
        OptimizedQuickSort.sort(array);
        assertArrayEquals(new Integer[]{}, array);
    }

    @Test
    void testSortSingleElement() {
        Integer[] array = {1};
        OptimizedQuickSort.sort(array);
        assertArrayEquals(new Integer[]{1}, array);
    }

    @Test
    void testSortTwoElements() {
        Integer[] array = {2, 1};
        OptimizedQuickSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2}, array);
    }

    @Test
    void testSortAlreadySortedArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        OptimizedQuickSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testSortReverseOrderArray() {
        Integer[] array = {5, 4, 3, 2, 1};
        OptimizedQuickSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testSortArrayWithDuplicates() {
        Integer[] array = {3, 5, 3, 2, 5, 1};
        OptimizedQuickSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 3, 5, 5}, array);
    }

    @Test
    void testSortArrayWithLargeSize() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        OptimizedQuickSort.sort(array);
        Integer[] expected = new Integer[100];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = i + 1;
        }
        assertArrayEquals(expected, array);
    }

    @Test
    void testSortPartiallySortedArray() {
        Integer[] array = {1, 2, 3, 7, 6, 5, 4};
        OptimizedQuickSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7}, array);
    }

    @Test
    void testSortStringArray() {
        String[] array = {"banana", "apple", "cherry", "date"};
        OptimizedQuickSort.sort(array);
        assertArrayEquals(new String[]{"apple", "banana", "cherry", "date"}, array);
    }
}
