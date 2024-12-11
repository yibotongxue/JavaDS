package datastructures.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    @Test
    void testSortEmptyArray() {
        Integer[] array = {};
        MergeSort.sort(array);
        assertArrayEquals(new Integer[]{}, array);
    }

    @Test
    void testSortSingleElement() {
        Integer[] array = {1};
        MergeSort.sort(array);
        assertArrayEquals(new Integer[]{1}, array);
    }

    @Test
    void testSortTwoElements() {
        Integer[] array = {2, 1};
        MergeSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2}, array);
    }

    @Test
    void testSortAlreadySortedArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        MergeSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testSortReverseOrderArray() {
        Integer[] array = {5, 4, 3, 2, 1};
        MergeSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testSortArrayWithDuplicates() {
        Integer[] array = {3, 5, 3, 2, 5, 1};
        MergeSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 3, 5, 5}, array);
    }

    @Test
    void testSortArrayWithLargeSize() {
        Integer[] array = new Integer[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = array.length - i;
        }
        MergeSort.sort(array);
        Integer[] expected = new Integer[100];
        for (int i = 0; i < expected.length; i++) {
            expected[i] = i + 1;
        }
        assertArrayEquals(expected, array);
    }

    @Test
    void testSortPartiallySortedArray() {
        Integer[] array = {1, 2, 3, 7, 6, 5, 4};
        MergeSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5, 6, 7}, array);
    }

    @Test
    void testSortStringArray() {
        String[] array = {"banana", "apple", "cherry", "date"};
        MergeSort.sort(array);
        assertArrayEquals(new String[]{"apple", "banana", "cherry", "date"}, array);
    }

    @Test
    void testStableSorting() {
        // 自定义类用于测试稳定性
        class StableTestElement implements Comparable<StableTestElement> {
            final int key;
            final String value;

            StableTestElement(int key, String value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public int compareTo(StableTestElement other) {
                return Integer.compare(this.key, other.key);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                StableTestElement that = (StableTestElement) obj;
                return key == that.key && value.equals(that.value);
            }

            @Override
            public String toString() {
                return key + ":" + value;
            }
        }

        StableTestElement[] array = {
                new StableTestElement(3, "A"),
                new StableTestElement(1, "B"),
                new StableTestElement(3, "C"),
                new StableTestElement(2, "D"),
                new StableTestElement(1, "E")
        };

        StableTestElement[] expected = {
                new StableTestElement(1, "B"),
                new StableTestElement(1, "E"),
                new StableTestElement(2, "D"),
                new StableTestElement(3, "A"),
                new StableTestElement(3, "C")
        };

        MergeSort.sort(array);
        assertArrayEquals(expected, array, "MergeSort should maintain stability for elements with the same key");
    }
}
