package datastructures.external_sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MinHeapTest {

    @Test
    public void testAddAndSize() {
        MinHeap<Integer> heap = new MinHeap<>(10);
        heap.add(10);
        assertEquals(1, heap.getSize());
        heap.add(5);
        assertEquals(2, heap.getSize());
        heap.add(20);
        assertEquals(3, heap.getSize());
    }

    @Test
    public void testPoll() {
        MinHeap<Integer> heap = new MinHeap<>(10);
        heap.add(10);
        heap.add(5);
        heap.add(20);

        assertEquals(5, heap.poll());  // 最小的元素应该是 5
        assertEquals(2, heap.getSize());
        assertEquals(10, heap.poll());  // 下一个最小的元素应该是 10
        assertEquals(1, heap.getSize());
        assertEquals(20, heap.poll());  // 剩下的元素应该是 20
        assertTrue(heap.isEmpty());     // 堆应该是空的
    }

    @Test
    public void testReplaceInsert() {
        MinHeap<Integer> heap = new MinHeap<>(10);
        heap.add(10);
        heap.add(5);
        heap.add(20);

        assertEquals(5, heap.replace(3));  // 堆顶元素 5 被 8 替换
        assertEquals(3, heap.getSize());
        assertEquals(3, heap.poll());  // 替换后的堆顶应该是 8
    }

    @Test
    public void testReplaceNotInsert() {
        MinHeap<Integer> heap = new MinHeap<>(10);
        heap.add(10);
        heap.add(5);
        heap.add(20);

        assertEquals(5, heap.replace(8));  // 堆顶元素 5 被 8 替换
        assertEquals(2, heap.getSize());
        assertEquals(10, heap.poll());  // 替换后的堆顶应该是 8
    }

    @Test
    public void testIsFull() {
        MinHeap<Integer> heap = new MinHeap<>(3);
        heap.add(10);
        heap.add(5);
        heap.add(20);
        assertTrue(heap.isFull());  // 堆已经满了

        // 尝试向已满的堆中添加元素，应该抛出异常
        assertThrows(IndexOutOfBoundsException.class, () -> heap.add(30));
    }

    @Test
    public void testIsEmpty() {
        MinHeap<Integer> heap = new MinHeap<>(10);
        assertTrue(heap.isEmpty());  // 空堆
        heap.add(10);
        assertFalse(heap.isEmpty()); // 堆不为空
    }

    @Test
    public void testPollFromEmptyHeap() {
        MinHeap<Integer> heap = new MinHeap<>(10);
        // 尝试从空堆中poll，应该抛出异常
        assertThrows(IndexOutOfBoundsException.class, heap::poll);
    }

    @Test
    public void testReplaceOnEmptyHeap() {
        MinHeap<Integer> heap = new MinHeap<>(10);
        // 尝试替换空堆的元素，应该抛出异常
        assertThrows(IndexOutOfBoundsException.class, () -> heap.replace(10));
    }

    @Test
    public void testAddMultipleElements() {
        MinHeap<Integer> heap = new MinHeap<>(5);
        heap.add(3);
        heap.add(1);
        heap.add(2);
        heap.add(5);
        heap.add(4);

        // 依次取出最小的元素，应该是 1, 2, 3, 4, 5
        assertEquals(1, heap.poll());
        assertEquals(2, heap.poll());
        assertEquals(3, heap.poll());
        assertEquals(4, heap.poll());
        assertEquals(5, heap.poll());
    }
}


