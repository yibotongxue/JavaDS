/**
 * 单元测试代码基于 ChatGPT 提供的建议进行编写和修改。
 */

package datastructures;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Test
    void testClear() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.append(1);
        list.append(2);
        list.clear();
        assertEquals(0, list.getSize(), "List size should be 0 after clearing.");
        assertTrue(list.isEmpty(), "List should be empty after clearing.");
    }

    @Test
    void testIsEmpty() {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertTrue(list.isEmpty(), "New list should be empty.");
        list.append(1);
        assertFalse(list.isEmpty(), "List with elements should not be empty.");
    }

    @Test
    void testAppend() {
        MyArrayList<String> list = new MyArrayList<>();
        list.append("A");
        list.append("B");
        assertEquals(2, list.getSize(), "List size should be 2 after appending two elements.");
        assertEquals("A", list.getValue(0), "First element should be 'A'.");
        assertEquals("B", list.getValue(1), "Second element should be 'B'.");
    }

    @Test
    void testInsert() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.append(1);
        list.append(3);
        list.insert(1, 2);
        assertEquals(3, list.getSize(), "List size should be 3 after inserting an element.");
        assertEquals(1, list.getValue(0), "First element should be 1.");
        assertEquals(2, list.getValue(1), "Second element should be 2.");
        assertEquals(3, list.getValue(2), "Third element should be 3.");
    }

    @Test
    void testInsertOutOfBounds() {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.insert(1, 42), "Inserting out of bounds should throw exception.");
    }

    @Test
    void testDelete() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        int deleted = list.delete(1);
        assertEquals(2, deleted, "Deleted value should be 2.");
        assertEquals(2, list.getSize(), "List size should be 2 after deletion.");
        assertEquals(1, list.getValue(0), "First element should remain 1.");
        assertEquals(3, list.getValue(1), "Second element should be 3.");
    }

    @Test
    void testDeleteOutOfBounds() {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.delete(0), "Deleting out of bounds should throw exception.");
    }

    @Test
    void testGetValue() {
        MyArrayList<String> list = new MyArrayList<>();
        list.append("A");
        assertEquals("A", list.getValue(0), "Value at index 0 should be 'A'.");
    }

    @Test
    void testGetValueOutOfBounds() {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.getValue(0), "Getting value out of bounds should throw exception.");
    }

    @Test
    void testSetValue() {
        MyArrayList<String> list = new MyArrayList<>();
        list.append("A");
        list.setValue(0, "B");
        assertEquals("B", list.getValue(0), "Value at index 0 should be updated to 'B'.");
    }

    @Test
    void testSetValueOutOfBounds() {
        MyArrayList<String> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.setValue(0, "C"), "Setting value out of bounds should throw exception.");
    }

    @Test
    void testGetPosition() {
        MyArrayList<String> list = new MyArrayList<>();
        list.append("A");
        list.append("B");
        assertEquals(0, list.getPosition("A"), "Position of 'A' should be 0.");
        assertEquals(1, list.getPosition("B"), "Position of 'B' should be 1.");
        assertEquals(-1, list.getPosition("C"), "Position of 'C' should be -1 (not found).");
    }

    @Test
    void testResize() {
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.append(i);
        }
        assertEquals(20, list.getSize(), "List size should be 20 after appending 20 elements.");
        for (int i = 0; i < 20; i++) {
            assertEquals(i, list.getValue(i), "Value should match the appended order.");
        }
    }

    @Test
    public void testIterator() {
        // 创建一个 MyArrayList 实例并添加一些元素
        MyArrayList<Integer> list = new MyArrayList<>();
        list.append(1);
        list.append(2);
        list.append(3);

        // 使用 for-each 循环遍历 list 并检查元素
        int[] expected = {1, 2, 3};
        int index = 0;

        for (Integer val : list) {
            assertEquals(expected[index++], val);
        }

        // 获取迭代器并遍历所有元素
        Iterator<Integer> iterator = list.iterator();

        // 确保迭代器抛出 NoSuchElementException 异常
        assertTrue(iterator.hasNext());  // 应该还有第一个元素
        iterator.next();  // 消耗第一个元素

        assertTrue(iterator.hasNext());  // 还有第二个元素
        iterator.next();  // 消耗第二个元素

        assertTrue(iterator.hasNext());  // 还有第三个元素
        iterator.next();  // 消耗第三个元素

        // 此时没有更多元素了，调用 next() 应该抛出 NoSuchElementException
        assertThrows(java.util.NoSuchElementException.class, iterator::next);
    }
}
