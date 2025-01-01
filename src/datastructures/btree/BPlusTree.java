package datastructures.btree;

import java.util.ArrayList;
import java.util.List;

public class BPlusTree<K extends Comparable<K>, V> {
    private BPlusTreeNode<K, V> root;
    private final BPlusTreeNode<K, V> head;
    private final int maxChildrenCount;

    public BPlusTree(int maxChildrenCount) {
        this.maxChildrenCount = maxChildrenCount;
        root = new BPlusTreeNode<>(true);
        head = root;
    }

    /**
     * Get the root node of the B+ tree. Only used for testing.
     * @return the root node of the B+ tree
     */
    BPlusTreeNode<K, V> getRoot() {
        return root;
    }

    public void insert(K key, V value) {
        insert(root, key, value);
        if (root.keys.size() > maxChildrenCount) {
            BPlusTreeNode<K, V> newRoot = new BPlusTreeNode<>(false);
            newRoot.children.add(root);
            newRoot.keys.add(root.keys.getLast());
            splitChild(newRoot, 0);
            root = newRoot;
        }
    }

    private void insert(BPlusTreeNode<K, V> node, K key, V value) {
        int i = node.findIndexOfFirstNotLessThan(key);
        if (node.isLeaf) {
            node.keys.add(i, key);
            node.values.add(i, value);
        } else {
            if (i >= node.children.size()) {
                node.keys.set(i - 1, key);
                i = node.children.size() - 1;
            }
            insert(node.children.get(i), key, value);
            if (node.children.get(i).keys.size() > maxChildrenCount) {
                splitChild(node, i);
            }
        }
    }

    private void splitChild(BPlusTreeNode<K, V> parent, int childIndex) {
        BPlusTreeNode<K, V> child = parent.children.get(childIndex);
        BPlusTreeNode<K, V> newChild = new BPlusTreeNode<>(child.isLeaf);
        int mid = (int) Math.ceil((maxChildrenCount + 1) / 2.0);
        K newKey = child.keys.get(mid - 1);
        newChild.keys.addAll(child.keys.subList(mid, child.keys.size()));
        child.keys.subList(mid, child.keys.size()).clear();
        if (!child.isLeaf) {
            newChild.children.addAll(child.children.subList(mid, child.children.size()));
            child.children.subList(mid, child.children.size()).clear();
        } else {
            newChild.values.addAll(child.values.subList(mid, child.values.size()));
            child.values.subList(mid, child.values.size()).clear();
            newChild.next = child.next;
            child.next = newChild;
        }
        parent.keys.add(childIndex, newKey);
        parent.children.add(childIndex + 1, newChild);
    }

    public V search(K key) {
        return search(root, key);
    }

    private V search(BPlusTreeNode<K, V> node, K key) {
        int i = node.findIndexOfFirstNotLessThan(key);
        if (node.isLeaf) {
            if (i < node.keys.size() && equal(node.keys.get(i), key)) {
                return node.values.get(i);
            } else {
                return null;
            }
        } else {
            return search(node.children.get(i), key);
        }
    }

    public List<V> searchRange(K key1, K key2) {
        List<V> result = new ArrayList<>();
        BPlusTreeNode<K, V> node = head;
        while (node != null) {
            for (int i = 0; i < node.keys.size(); i++) {
                if (node.keys.get(i).compareTo(key1) >= 0 && node.keys.get(i).compareTo(key2) <= 0) {
                    result.add(node.values.get(i));
                }
            }
            node = node.next;
        }
        return result;
    }

    private boolean equal(K key1, K key2) {
        return key1.compareTo(key2) == 0;
    }
}
