package datastructures.btree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BPlusTreeTest {

    private BPlusTree<Integer, String> bPlusTree;

    @BeforeEach
    public void setUp() {
        // 初始化B+树，假设每个节点最多有3个子节点
        bPlusTree = new BPlusTree<>(3);
    }

    @Test
    public void testInsertAndSearch() {
        // 插入单个元素并验证
        bPlusTree.insert(1, "One");
        bPlusTree.insert(2, "Two");
        bPlusTree.insert(3, "Three");

        // 检索已插入的元素
        assertEquals("One", bPlusTree.search(1));
        assertEquals("Two", bPlusTree.search(2));
        assertEquals("Three", bPlusTree.search(3));

        // 检索不存在的元素，应该返回null
        assertNull(bPlusTree.search(4));
    }

    @Test
    public void testMultipleInsertions() {
        // 插入多个元素，确保顺序和结构正确
        bPlusTree.insert(10, "Ten");
        bPlusTree.insert(20, "Twenty");
        bPlusTree.insert(5, "Five");
        bPlusTree.insert(15, "Fifteen");
        bPlusTree.insert(25, "Twenty-Five");

        assertEquals("Ten", bPlusTree.search(10));
        assertEquals("Twenty", bPlusTree.search(20));
        assertEquals("Five", bPlusTree.search(5));
        assertEquals("Fifteen", bPlusTree.search(15));
        assertEquals("Twenty-Five", bPlusTree.search(25));
    }

    @Test
    public void testSearchRange() {
        // 插入多个元素
        bPlusTree.insert(1, "One");
        bPlusTree.insert(2, "Two");
        bPlusTree.insert(3, "Three");
        bPlusTree.insert(4, "Four");
        bPlusTree.insert(5, "Five");

        // 范围搜索[2, 4]，应该返回 "Two", "Three", "Four"
        List<String> rangeResult = bPlusTree.searchRange(2, 4);
        assertEquals(3, rangeResult.size());
        assertEquals("Two", rangeResult.get(0));
        assertEquals("Three", rangeResult.get(1));
        assertEquals("Four", rangeResult.get(2));
    }

    @Test
    public void testInsertAndSplit() {
        // 插入到分裂的节点
        bPlusTree.insert(1, "One");
        bPlusTree.insert(2, "Two");
        bPlusTree.insert(3, "Three");
        bPlusTree.insert(4, "Four");

        // 检查插入后的树是否分裂，验证是否有新的根节点
        assertEquals("One", bPlusTree.search(1));
        assertEquals("Two", bPlusTree.search(2));
        assertEquals("Three", bPlusTree.search(3));
        assertEquals("Four", bPlusTree.search(4));
    }

    @Test
    public void testInsertAndSearchNull() {
        // 插入元素，检索不存在的元素时应该返回null
        bPlusTree.insert(100, "Hundred");
        bPlusTree.insert(200, "Two Hundred");

        assertNull(bPlusTree.search(50)); // 检索一个不存在的元素
        assertNull(bPlusTree.search(300)); // 检索一个不存在的元素
    }

    @Test
    public void testInsertAndCheckLeafNode() {
        // 插入元素并检查叶子节点是否链接
        bPlusTree.insert(1, "One");
        bPlusTree.insert(2, "Two");
        bPlusTree.insert(3, "Three");
        bPlusTree.insert(4, "Four");

        // 获取根节点的第一个子节点（应该是叶子节点）
        BPlusTreeNode<Integer, String> leafNode = bPlusTree.getRoot().children.getFirst();

        // 验证叶子节点的顺序
        assertEquals(1, leafNode.keys.get(0));
        assertEquals(2, leafNode.keys.get(1));

        BPlusTreeNode<Integer, String> nextLeafNode = leafNode.next;
        assertEquals(3, nextLeafNode.keys.get(0));
        assertEquals(4, nextLeafNode.keys.get(1));
    }
}
