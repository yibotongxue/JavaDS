package datastructures.sorting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HeapSortTest {

    @Test
    public void testHeapSortAscending() {
        Integer[] array = {4, 10, 3, 5, 1};
        HeapSort.sort(array);

        Integer[] expected = {1, 3, 4, 5, 10};
        assertArrayEquals(expected, array, "The array should be sorted in ascending order.");
    }

    @Test
    public void testHeapSortDescending() {
        Integer[] array = {10, 5, 8, 3, 2};
        HeapSort.sort(array);

        Integer[] expected = {2, 3, 5, 8, 10};
        assertArrayEquals(expected, array, "The array should be sorted in ascending order.");
    }

    @Test
    public void testHeapSortWithNegativeNumbers() {
        Integer[] array = {-1, -3, 4, 2, -2};
        HeapSort.sort(array);

        Integer[] expected = {-3, -2, -1, 2, 4};
        assertArrayEquals(expected, array, "The array should be sorted correctly with negative numbers.");
    }

    @Test
    public void testHeapSortWithDuplicates() {
        Integer[] array = {5, 1, 3, 3, 5};
        HeapSort.sort(array);

        Integer[] expected = {1, 3, 3, 5, 5};
        assertArrayEquals(expected, array, "The array should be sorted correctly with duplicate elements.");
    }

    @Test
    public void testHeapSortEmptyArray() {
        Integer[] array = {};
        HeapSort.sort(array);

        Integer[] expected = {};
        assertArrayEquals(expected, array, "The empty array should remain empty.");
    }

    @Test
    public void testHeapSortSingleElement() {
        Integer[] array = {7};
        HeapSort.sort(array);

        Integer[] expected = {7};
        assertArrayEquals(expected, array, "The array with a single element should remain unchanged.");
    }
}

