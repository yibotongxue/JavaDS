package datastructures.container;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    @Test
    public void testEmptyHeap() {
        MinHeap<Integer> heap = new MinHeap<>();
        assertThrows(IndexOutOfBoundsException.class, heap::getMin); // 堆为空时调用 getMin 应抛出异常
        assertThrows(IndexOutOfBoundsException.class, heap::pop);    // 堆为空时调用 pop 应抛出异常
    }

    @Test
    public void testPushAndGetMin() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.push(10);
        heap.push(5);
        heap.push(20);
        heap.push(3);

        assertEquals(3, heap.getMin()); // 最小元素应该是 3
    }

    @Test
    public void testPushAndPop() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.push(10);
        heap.push(5);
        heap.push(20);
        heap.push(3);

        assertEquals(3, heap.pop()); // 弹出 3
        assertEquals(5, heap.pop()); // 弹出 5
        assertEquals(10, heap.pop()); // 弹出 10
        assertEquals(20, heap.pop()); // 弹出 20

        assertThrows(IndexOutOfBoundsException.class, heap::pop); // 堆为空时调用 pop 应抛出异常
    }

    @Test
    public void testHeapifyConstructor() {
        List<Integer> data = Arrays.asList(10, 5, 20, 3, 15);
        MinHeap<Integer> heap = new MinHeap<>(data);

        // 验证堆的最小值
        assertEquals(3, heap.getMin());

        // 验证弹出的顺序是否正确
        assertEquals(3, heap.pop());
        assertEquals(5, heap.pop());
        assertEquals(10, heap.pop());
        assertEquals(15, heap.pop());
        assertEquals(20, heap.pop());
    }

    @Test
    public void testMinHeapWithStrings() {
        MinHeap<String> heap = new MinHeap<>();
        heap.push("apple");
        heap.push("banana");
        heap.push("cherry");
        heap.push("date");

        assertEquals("apple", heap.getMin()); // 最小值应该是 "apple"
        assertEquals("apple", heap.pop());    // 弹出 "apple"
        assertEquals("banana", heap.pop());   // 弹出 "banana"
        assertEquals("cherry", heap.pop());   // 弹出 "cherry"
        assertEquals("date", heap.pop());     // 弹出 "date"
    }

    @Test
    public void testPushPopMix() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.push(15);
        heap.push(10);
        heap.push(30);

        assertEquals(10, heap.pop()); // 弹出 10
        heap.push(5);
        heap.push(20);

        assertEquals(5, heap.pop());  // 弹出 5
        assertEquals(15, heap.pop()); // 弹出 15
        assertEquals(20, heap.pop()); // 弹出 20
        assertEquals(30, heap.pop()); // 弹出 30
    }

    @Test
    public void testHeapifyPerformance() {
        int n = 100_000; // 测试大规模数据
        List<Integer> data = Arrays.asList(new Integer[n]);
        for (int i = 0; i < n; i++) {
            data.set(i, n - i); // 初始化为降序数据
        }

        MinHeap<Integer> heap = new MinHeap<>(data);
        for (int i = 1; i <= n; i++) {
            assertEquals(i, heap.pop()); // 验证弹出的顺序是否正确
        }
    }
}
