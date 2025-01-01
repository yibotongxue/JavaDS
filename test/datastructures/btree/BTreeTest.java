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
}
