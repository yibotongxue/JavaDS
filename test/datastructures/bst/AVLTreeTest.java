package datastructures.bst;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreeTest {

    @Test
    public void testInsertBasic() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);

        // 确保插入后树仍然平衡
        assertTrue(tree.isAVLTree());

        // 进一步检查是否正确插入了节点
        assertTrue(tree.search(10));  // 10 应该在树中
        assertTrue(tree.search(20));  // 20 应该在树中
        assertTrue(tree.search(5));   // 5 应该在树中
    }

    @Test
    public void testInsertWithRotation() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(30); // 这会导致右旋

        // 确保插入后树仍然平衡
        assertTrue(tree.isAVLTree());

        // 确保插入后的结构是平衡的
        assertTrue(tree.search(10));  // 10 应该在树中
        assertTrue(tree.search(20));  // 20 应该在树中
        assertTrue(tree.search(5));   // 5 应该在树中
        assertTrue(tree.search(30));  // 30 应该在树中
    }

    @Test
    public void testInsertAndBalance() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(15); // 需要进行旋转，确保平衡

        assertTrue(tree.isAVLTree());

        // 确保树中所有节点都存在
        assertTrue(tree.search(10));
        assertTrue(tree.search(20));
        assertTrue(tree.search(5));
        assertTrue(tree.search(15));
    }

    @Test
    public void testInsertMultipleElements() {
        AVLTree<Integer> tree = new AVLTree<>();
        // 插入多个元素，确保平衡
        int[] values = {30, 20, 10, 25, 40, 35, 50};
        for (int val : values) {
            tree.insert(val);
        }

        // 确保插入后的树仍然平衡
        assertTrue(tree.isAVLTree());

        // 检查所有插入的节点是否存在
        for (int val : values) {
            assertTrue(tree.search(val));
        }
    }

    @Test
    public void testInsertDuplicate() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(10); // 尝试插入重复元素

        // 检查树中是否仍然只有一个 10
        assertEquals(1, countOccurrences(tree, 10));  // 10 只能出现一次
        assertTrue(tree.isAVLTree());  // 树依然是 AVL 树
    }

    // 辅助方法，用来统计一个值在树中的出现次数
    private int countOccurrences(AVLTree<Integer> tree, Integer value) {
        int[] count = {0};
        traverse((AVLTree.Node<Integer>) tree.root, value, count);
        return count[0];
    }

    // 遍历树并统计某个值的出现次数
    private void traverse(AVLTree.Node<Integer> node, Integer value, int[] count) {
        if (node == null) return;
        if (node.value.equals(value)) count[0]++;
        traverse((AVLTree.Node<Integer>) node.left, value, count);
        traverse((AVLTree.Node<Integer>) node.right, value, count);
    }

}
