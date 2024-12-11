package datastructures.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BubbleSortTest {

    @Test
    void testSortIntegerArray() {
        Integer[] array = {5, 3, 8, 6, 2};
        BubbleSort.sort(array);
        assertArrayEquals(new Integer[]{2, 3, 5, 6, 8}, array);
    }

    @Test
    void testSortStringArray() {
        String[] array = {"banana", "apple", "cherry", "date"};
        BubbleSort.sort(array);
        assertArrayEquals(new String[]{"apple", "banana", "cherry", "date"}, array);
    }

    @Test
    void testSortEmptyArray() {
        Integer[] array = {};
        BubbleSort.sort(array);
        assertArrayEquals(new Integer[]{}, array);
    }

    @Test
    void testSortSingleElementArray() {
        Integer[] array = {42};
        BubbleSort.sort(array);
        assertArrayEquals(new Integer[]{42}, array);
    }

    @Test
    void testSortAlreadySortedArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        BubbleSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testSortReverseSortedArray() {
        Integer[] array = {5, 4, 3, 2, 1};
        BubbleSort.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testStability() {
        class Pair implements Comparable<Pair> {
            final int value;
            final char identifier;

            Pair(int value, char identifier) {
                this.value = value;
                this.identifier = identifier;
            }

            @Override
            public int compareTo(Pair o) {
                return Integer.compare(this.value, o.value);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Pair pair = (Pair) o;
                return value == pair.value && identifier == pair.identifier;
            }
        }

        Pair[] array = {
                new Pair(3, 'a'),
                new Pair(1, 'b'),
                new Pair(3, 'c'),
                new Pair(2, 'd')
        };
        BubbleSort.sort(array);
        Pair[] expected = {
                new Pair(1, 'b'),
                new Pair(2, 'd'),
                new Pair(3, 'a'),
                new Pair(3, 'c')
        };
        assertArrayEquals(expected, array);
    }
}
