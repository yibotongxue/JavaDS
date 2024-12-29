package datastructures.container;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaxHeapTest {

    @Test
    public void testEmptyHeap() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        assertThrows(IndexOutOfBoundsException.class, heap::getMax); // 空堆调用 getMax 应抛出异常
        assertThrows(IndexOutOfBoundsException.class, heap::pop);    // 空堆调用 pop 应抛出异常
    }

    @Test
    public void testPushAndGetMax() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.push(10);
        heap.push(5);
        heap.push(20);
        heap.push(15);

        assertEquals(20, heap.getMax()); // 最大值应该是 20
    }

    @Test
    public void testPushAndPop() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.push(10);
        heap.push(5);
        heap.push(20);
        heap.push(15);

        assertEquals(20, heap.pop()); // 弹出最大值 20
        assertEquals(15, heap.pop()); // 弹出 15
        assertEquals(10, heap.pop()); // 弹出 10
        assertEquals(5, heap.pop());  // 弹出 5

        assertThrows(IndexOutOfBoundsException.class, heap::pop); // 堆为空时调用 pop 应抛出异常
    }

    @Test
    public void testHeapifyConstructorWithList() {
        List<Integer> data = Arrays.asList(10, 5, 20, 15);
        MaxHeap<Integer> heap = new MaxHeap<>(data);

        assertEquals(20, heap.getMax()); // 最大值应该是 20
        assertEquals(20, heap.pop());   // 弹出最大值 20
        assertEquals(15, heap.pop());   // 弹出 15
        assertEquals(10, heap.pop());   // 弹出 10
        assertEquals(5, heap.pop());    // 弹出 5
    }

    @Test
    public void testHeapifyConstructorWithArray() {
        Integer[] data = {10, 5, 20, 15};
        MaxHeap<Integer> heap = new MaxHeap<>(data);

        assertEquals(20, heap.getMax()); // 最大值应该是 20
        assertEquals(20, heap.pop());   // 弹出最大值 20
        assertEquals(15, heap.pop());   // 弹出 15
        assertEquals(10, heap.pop());   // 弹出 10
        assertEquals(5, heap.pop());    // 弹出 5
    }

    @Test
    public void testMaxHeapWithStrings() {
        MaxHeap<String> heap = new MaxHeap<>();
        heap.push("apple");
        heap.push("banana");
        heap.push("cherry");
        heap.push("date");

        assertEquals("date", heap.getMax()); // 最大值应该是 "date"
        assertEquals("date", heap.pop());    // 弹出 "date"
        assertEquals("cherry", heap.pop());  // 弹出 "cherry"
        assertEquals("banana", heap.pop());  // 弹出 "banana"
        assertEquals("apple", heap.pop());   // 弹出 "apple"
    }

    @Test
    public void testPushPopMix() {
        MaxHeap<Integer> heap = new MaxHeap<>();
        heap.push(10);
        heap.push(15);
        heap.push(5);

        assertEquals(15, heap.pop()); // 弹出最大值 15
        heap.push(20);
        heap.push(25);

        assertEquals(25, heap.pop()); // 弹出 25
        assertEquals(20, heap.pop()); // 弹出 20
        assertEquals(10, heap.pop()); // 弹出 10
        assertEquals(5, heap.pop());  // 弹出 5
    }

    @Test
    public void testHeapifyPerformance() {
        int n = 100_000; // 测试大规模数据
        Integer[] data = new Integer[n];
        for (int i = 0; i < n; i++) {
            data[i] = n - i; // 降序数据
        }

        MaxHeap<Integer> heap = new MaxHeap<>(data);
        for (int i = n; i >= 1; i--) {
            assertEquals(i, heap.pop()); // 验证弹出的顺序是否正确
        }
    }
}
