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

    @Test
    public void testLeftLeftRotation() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(30);  // 根节点
        tree.insert(20);  // 插入 20，树不平衡
        tree.insert(10);  // 插入 10，触发左左旋转

        // 验证树的结构
        assertTrue(tree.search(10));
        assertTrue(tree.search(20));
        assertTrue(tree.search(30));

        // 此时树应该已经平衡，根节点应该是 20
        AVLTree.Node<Integer> root = (AVLTree.Node<Integer>) tree.root;
        assertEquals(Integer.valueOf(20), root.getValue());
        assertEquals(Integer.valueOf(10), root.getLeft().getValue());
        assertEquals(Integer.valueOf(30), root.getRight().getValue());

        assertTrue(tree.isAVLTree());
    }

    @Test
    public void testLeftRightRotation() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(30);  // 根节点
        tree.insert(10);  // 插入 10，树不平衡
        tree.insert(20);  // 插入 20，触发左-右双旋转

        // 验证树的结构
        assertTrue(tree.search(10));
        assertTrue(tree.search(20));
        assertTrue(tree.search(30));

        // 此时树应该已经平衡，根节点应该是 20
        AVLTree.Node<Integer> root = (AVLTree.Node<Integer>) tree.root;
        assertEquals(Integer.valueOf(20), root.getValue());
        assertEquals(Integer.valueOf(10), root.getLeft().getValue());
        assertEquals(Integer.valueOf(30), root.getRight().getValue());

        // 验证 AVL 树是否平衡
        assertTrue(tree.isAVLTree());
    }

    @Test
    public void testRightRightRotation() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);  // 根节点
        tree.insert(20);  // 插入 20，树不平衡
        tree.insert(30);  // 插入 30，触发右右旋转

        // 验证树的结构
        assertTrue(tree.search(10));
        assertTrue(tree.search(20));
        assertTrue(tree.search(30));

        // 此时树应该已经平衡，根节点应该是 20
        AVLTree.Node<Integer> root = (AVLTree.Node<Integer>) tree.root;
        assertEquals(Integer.valueOf(20), root.getValue());
        assertEquals(Integer.valueOf(10), root.getLeft().getValue());
        assertEquals(Integer.valueOf(30), root.getRight().getValue());

        // 验证 AVL 树是否平衡
        assertTrue(tree.isAVLTree());
    }

    @Test
    public void testRightLeftRotation() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);  // 根节点
        tree.insert(30);  // 插入 30，树不平衡
        tree.insert(20);  // 插入 20，触发右-左双旋转

        // 验证树的结构
        assertTrue(tree.search(10));
        assertTrue(tree.search(20));
        assertTrue(tree.search(30));

        // 此时树应该已经平衡，根节点应该是 20
        AVLTree.Node<Integer> root = (AVLTree.Node<Integer>) tree.root;
        assertEquals(Integer.valueOf(20), root.getValue());
        assertEquals(Integer.valueOf(10), root.getLeft().getValue());
        assertEquals(Integer.valueOf(30), root.getRight().getValue());

        // 验证 AVL 树是否平衡
        assertTrue(tree.isAVLTree());
    }

    @Test
    public void testInsertLargeNumberOfNodes() {
        AVLTree<Integer> tree = new AVLTree<>();

        // 插入大量数据
        for (int i = 1; i <= 1000; i++) {
            tree.insert(i);
        }

        // 验证树是否平衡
        assertTrue(tree.isAVLTree());

        // 验证是否能够查找所有插入的节点
        for (int i = 1; i <= 1000; i++) {
            assertTrue(tree.search(i));
        }
    }

    @Test
    public void testDeleteLeafNode() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);

        // 删除叶子节点
        tree.delete(5);

        // 验证删除结果
        assertFalse(tree.search(5));
        assertTrue(tree.search(10));
        assertTrue(tree.search(20));
        assertTrue(tree.isAVLTree()); // 确保树仍然是AVL树
    }

    @Test
    public void testDeleteNodeWithOneChild() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(3);

        // 删除只有一个子节点的节点
        tree.delete(5);

        // 验证删除结果
        assertFalse(tree.search(5));
        assertTrue(tree.search(3));
        assertTrue(tree.search(10));
        assertTrue(tree.search(20));
        assertTrue(tree.isAVLTree()); // 确保树仍然是AVL树
    }

    @Test
    public void testDeleteNodeWithTwoChildren() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        tree.insert(15);
        tree.insert(25);

        // 删除有两个子节点的节点
        tree.delete(20);

        // 验证删除结果
        assertFalse(tree.search(20));
        assertTrue(tree.search(10));
        assertTrue(tree.search(5));
        assertTrue(tree.search(15));
        assertTrue(tree.search(25));
        assertTrue(tree.isAVLTree()); // 确保树仍然是AVL树
    }

    @Test
    public void testDeleteCausingLeftLeftRotation() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(10);

        // 删除 40 后会触发左左旋转
        tree.delete(40);

        // 验证删除结果和树结构
        assertFalse(tree.search(40));
        assertTrue(tree.search(30));
        assertTrue(tree.search(20));
        assertTrue(tree.search(10));
        assertTrue(tree.isAVLTree());
    }

    @Test
    public void testDeleteCausingLeftRightRotation() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(30);
        tree.insert(10);
        tree.insert(40);
        tree.insert(20);

        // 删除 40 后会触发左右旋转
        tree.delete(40);

        // 验证删除结果和树结构
        assertFalse(tree.search(40));
        assertTrue(tree.search(30));
        assertTrue(tree.search(10));
        assertTrue(tree.search(20));
        assertTrue(tree.isAVLTree());
    }

    @Test
    public void testDeleteCausingRightRightRotation() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(30);

        // 删除 5 后会触发右右旋转
        tree.delete(5);

        // 验证删除结果和树结构
        assertFalse(tree.search(5));
        assertTrue(tree.search(10));
        assertTrue(tree.search(20));
        assertTrue(tree.search(30));
        assertTrue(tree.isAVLTree());
    }

    @Test
    public void testDeleteCausingRightLeftRotation() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(30);
        tree.insert(20);

        // 删除 5 后会触发右左旋转
        tree.delete(5);

        // 验证删除结果和树结构
        assertFalse(tree.search(5));
        assertTrue(tree.search(10));
        assertTrue(tree.search(30));
        assertTrue(tree.search(20));
        assertTrue(tree.isAVLTree());
    }

    @Test
    public void testDeleteRootNode() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);

        // 删除根节点
        tree.delete(10);

        // 验证删除结果
        assertFalse(tree.search(10));
        assertTrue(tree.search(5));
        assertTrue(tree.search(20));
        assertTrue(tree.isAVLTree()); // 确保树仍然是AVL树
    }

    @Test
    public void testDeleteLargeNumberOfNodes() {
        AVLTree<Integer> tree = new AVLTree<>();
        for (int i = 1; i <= 100; i++) {
            tree.insert(i);
        }

        // 删除一半的节点
        for (int i = 1; i <= 50; i++) {
            tree.delete(i);
        }

        // 验证删除结果
        for (int i = 1; i <= 50; i++) {
            assertFalse(tree.search(i)); // 确保已删除的节点不存在
        }
        for (int i = 51; i <= 100; i++) {
            assertTrue(tree.search(i)); // 确保剩余节点仍然存在
        }
        assertTrue(tree.isAVLTree()); // 确保树仍然是AVL树
    }
}
