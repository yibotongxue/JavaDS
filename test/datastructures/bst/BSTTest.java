package datastructures.bst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {
    @Test
    void testInsertAndSearch() {
        BST<Integer> bst = new BST<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        assertTrue(bst.search(10));
        assertTrue(bst.search(5));
        assertTrue(bst.search(15));
        assertFalse(bst.search(20));
    }

    @Test
    void testDeleteLeafNode() {
        BST<Integer> bst = new BST<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        bst.delete(5);
        assertFalse(bst.search(5));
        assertTrue(bst.search(10));
        assertTrue(bst.search(15));
    }

    @Test
    void testDeleteNodeWithOneChild() {
        BST<Integer> bst = new BST<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(12);

        bst.delete(15);
        assertFalse(bst.search(15));
        assertTrue(bst.search(10));
        assertTrue(bst.search(12));
    }

    @Test
    void testDeleteNodeWithTwoChildren() {
        BST<Integer> bst = new BST<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(12);
        bst.insert(18);

        bst.delete(15);
        assertFalse(bst.search(15));
        assertTrue(bst.search(10));
        assertTrue(bst.search(12));
        assertTrue(bst.search(18));
    }

    @Test
    void testDeleteRoot() {
        BST<Integer> bst = new BST<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);

        bst.delete(10);
        assertFalse(bst.search(10));
        assertTrue(bst.search(5));
        assertTrue(bst.search(15));
    }

    @Test
    void testEmptyTree() {
        BST<Integer> bst = new BST<>();
        assertFalse(bst.search(10));

        bst.delete(10);
        assertFalse(bst.search(10));
    }

    @Test
    void testDeleteMiddleNodeOneChild() {
        BST<Integer> bst = new BST<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(12); // 添加 12 作为中间节点

        bst.delete(15); // 删除具有一个子节点的中间节点
        assertFalse(bst.search(15));
        assertTrue(bst.search(10));
        assertTrue(bst.search(5));
        assertTrue(bst.search(12));
    }

    @Test
    void testDeleteMiddleNodeTwoChildren() {
        BST<Integer> bst = new BST<>();
        bst.insert(10);
        bst.insert(5);
        bst.insert(15);
        bst.insert(12);
        bst.insert(18); // 添加更多节点构造更复杂的树

        bst.delete(15); // 删除具有两个子节点的中间节点
        assertFalse(bst.search(15));
        assertTrue(bst.search(10));
        assertTrue(bst.search(5));
        assertTrue(bst.search(12));
        assertTrue(bst.search(18));
    }

    @Test
    void testDeleteNestedMiddleNode() {
        BST<Integer> bst = new BST<>();
        bst.insert(20);
        bst.insert(10);
        bst.insert(30);
        bst.insert(5);
        bst.insert(15);
        bst.insert(25);
        bst.insert(35);
        bst.insert(13);
        bst.insert(18);

        bst.delete(10); // 删除具有嵌套子树的中间节点
        assertFalse(bst.search(10));
        assertTrue(bst.search(20));
        assertTrue(bst.search(5));
        assertTrue(bst.search(15));
        assertTrue(bst.search(13));
        assertTrue(bst.search(18));
        assertTrue(bst.search(30));
        assertTrue(bst.search(25));
        assertTrue(bst.search(35));
    }

    @Test
    void testDeleteMultipleMiddleNodes() {
        BST<Integer> bst = new BST<>();
        bst.insert(20);
        bst.insert(10);
        bst.insert(30);
        bst.insert(5);
        bst.insert(15);
        bst.insert(25);
        bst.insert(35);
        bst.insert(13);
        bst.insert(18);

        bst.delete(10); // 删除第一个中间节点
        bst.delete(15); // 删除另一个中间节点
        assertFalse(bst.search(10));
        assertFalse(bst.search(15));
        assertTrue(bst.search(20));
        assertTrue(bst.search(5));
        assertTrue(bst.search(13));
        assertTrue(bst.search(18));
        assertTrue(bst.search(30));
        assertTrue(bst.search(25));
        assertTrue(bst.search(35));
    }
}
