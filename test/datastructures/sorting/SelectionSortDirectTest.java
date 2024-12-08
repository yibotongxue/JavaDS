package datastructures.sorting;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SelectionSortDirectTest {

    @Test
    public void testEmptyArray() {
        Integer[] array = {};
        SelectionSortDirect.sort(array);
        assertArrayEquals(new Integer[]{}, array, "空数组应该保持为空");
    }

    @Test
    public void testSingleElementArray() {
        Integer[] array = {42};
        SelectionSortDirect.sort(array);
        assertArrayEquals(new Integer[]{42}, array, "单元素数组应该保持不变");
    }

    @Test
    public void testSortedArray() {
        Integer[] array = {1, 2, 3, 4, 5};
        SelectionSortDirect.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array, "已排序数组应该保持不变");
    }

    @Test
    public void testReverseSortedArray() {
        Integer[] array = {5, 4, 3, 2, 1};
        SelectionSortDirect.sort(array);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array, "降序数组应该正确排序");
    }

    @Test
    public void testArrayWithDuplicates() {
        Integer[] array = {3, 1, 2, 3, 2, 1};
        SelectionSortDirect.sort(array);
        assertArrayEquals(new Integer[]{1, 1, 2, 2, 3, 3}, array, "包含重复元素的数组应该正确排序");
    }

    @Test
    public void testStringArray() {
        String[] array = {"banana", "apple", "cherry"};
        SelectionSortDirect.sort(array);
        assertArrayEquals(new String[]{"apple", "banana", "cherry"}, array, "字符串数组应该按照字母顺序排序");
    }
}

