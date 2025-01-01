package datastructures.btree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {
    private BTree<Integer> bTree;

    @BeforeEach
    void setUp() {
        // 初始化一个阶数为3的B树 (即每个节点最多有3个子节点，2个关键字)
        bTree = new BTree<>(3);
    }

    @Test
    void testInsertAndSearchSingleElement() {
        bTree.insert(10);
        assertTrue(bTree.search(10), "BTree should contain the inserted element 10.");
        assertFalse(bTree.search(20), "BTree should not contain element 20.");
    }

    @Test
    void testInsertAndSearchMultipleElements() {
        int[] elements = {30, 10, 20, 40, 50};
        for (int element : elements) {
            bTree.insert(element);
        }

        // Ensure all inserted elements are found
        for (int element : elements) {
            assertTrue(bTree.search(element), "BTree should contain the inserted element " + element);
        }

        // Ensure non-inserted element is not found
        assertFalse(bTree.search(60), "BTree should not contain element 60.");
    }

    @Test
    void testDuplicateInsert() {
        bTree.insert(10);
        bTree.insert(10); // Insert duplicate

        // Verify the search still works correctly
        assertTrue(bTree.search(10), "BTree should contain element 10 even after duplicate insert.");
    }

    @Test
    void testTreeSplit() {
        // Insert enough elements to cause a split
        int[] elements = {50, 40, 30, 20, 10};
        for (int element : elements) {
            bTree.insert(element);
        }

        // Verify all elements are present
        for (int element : elements) {
            assertTrue(bTree.search(element), "BTree should contain the inserted element " + element);
        }

        // Verify non-inserted element
        assertFalse(bTree.search(60), "BTree should not contain element 60.");
    }

    @Test
    void testDeepTree() {
        // Insert many elements to create a deeper tree
        int[] elements = {15, 10, 20, 5, 12, 18, 25, 30, 2, 8};
        for (int element : elements) {
            bTree.insert(element);
        }

        // Verify all elements are present
        for (int element : elements) {
            assertTrue(bTree.search(element), "BTree should contain the inserted element " + element);
        }

        // Verify some elements that are not in the tree
        int[] nonElements = {0, 100, 17, 28};
        for (int nonElement : nonElements) {
            assertFalse(bTree.search(nonElement), "BTree should not contain element " + nonElement);
        }
    }

    @Test
    void testBoundaryConditions() {
        // Test empty tree
        assertFalse(bTree.search(10), "Empty BTree should not contain any element.");

        // Test inserting a single element
        bTree.insert(Integer.MAX_VALUE);
        assertTrue(bTree.search(Integer.MAX_VALUE), "BTree should contain the maximum integer value.");

        // Test minimum integer value
        bTree.insert(Integer.MIN_VALUE);
        assertTrue(bTree.search(Integer.MIN_VALUE), "BTree should contain the minimum integer value.");
    }

    @Test
    void testDeleteLeafNode() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);

        assertTrue(bTree.search(20), "BTree should contain 20 before deletion.");
        bTree.delete(20); // 删除叶子节点
        assertFalse(bTree.search(20), "BTree should not contain 20 after deletion.");
    }

    @Test
    void testDeleteInternalNode() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);
        bTree.insert(40);
        bTree.insert(50);

        assertTrue(bTree.search(30), "BTree should contain 30 before deletion.");
        bTree.delete(30); // 删除内部节点
        assertFalse(bTree.search(30), "BTree should not contain 30 after deletion.");
    }

    @Test
    void testDeleteRootNode() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);

        assertTrue(bTree.search(20), "BTree should contain 20 before deletion.");
        bTree.delete(20); // 删除根节点
        assertFalse(bTree.search(20), "BTree should not contain 20 after deletion.");

        assertTrue(bTree.search(10), "BTree should still contain 10.");
        assertTrue(bTree.search(30), "BTree should still contain 30.");
    }

    @Test
    void testDeleteWithMerge() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);
        bTree.insert(40);
        bTree.insert(50);

        bTree.delete(30); // 删除节点以触发合并
        assertFalse(bTree.search(30), "BTree should not contain 30 after deletion.");
        assertTrue(bTree.search(10), "BTree should still contain 10.");
        assertTrue(bTree.search(20), "BTree should still contain 20.");
        assertTrue(bTree.search(40), "BTree should still contain 40.");
        assertTrue(bTree.search(50), "BTree should still contain 50.");
    }

    @Test
    void testDeleteNonExistentKey() {
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);

        bTree.delete(40); // 删除不存在的元素
        assertTrue(bTree.search(10), "BTree should still contain 10.");
        assertTrue(bTree.search(20), "BTree should still contain 20.");
        assertTrue(bTree.search(30), "BTree should still contain 30.");
    }

    @Test
    void testDeleteFromEmptyTree() {
        assertDoesNotThrow(() -> bTree.delete(10), "Deleting from an empty tree should not throw an exception.");
    }

    @Test
    void testDeleteSingleElementTree() {
        bTree.insert(10);
        assertTrue(bTree.search(10), "BTree should contain 10 before deletion.");
        bTree.delete(10); // 删除唯一的元素
        assertFalse(bTree.search(10), "BTree should not contain 10 after deletion.");
    }

    @Test
    void testDeleteCausesHeightReduction() {
        // 插入一组元素使树达到两层
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(30);
        bTree.insert(40);
        bTree.insert(50);
        bTree.insert(60);

        assertTrue(bTree.search(30), "BTree should contain 30 before deletion.");
        bTree.delete(30); // 删除节点，可能导致树高度减少
        assertFalse(bTree.search(30), "BTree should not contain 30 after deletion.");
        assertTrue(bTree.search(10), "BTree should still contain 10.");
        assertTrue(bTree.search(20), "BTree should still contain 20.");
        assertTrue(bTree.search(40), "BTree should still contain 40.");
        assertTrue(bTree.search(50), "BTree should still contain 50.");
        assertTrue(bTree.search(60), "BTree should still contain 60.");
    }
}
