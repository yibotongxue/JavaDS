package datastructures.btree;

import java.util.ArrayList;
import java.util.List;

class BPlusTreeNode<K extends Comparable<K>, V> {
    boolean isLeaf;
    List<K> keys;
    List<V> values;  // Only leaf nodes have values
    List<BPlusTreeNode<K, V>> children;  // Non-leaf nodes have children
    BPlusTreeNode<K, V> next;

    BPlusTreeNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        keys = new ArrayList<>();
        if (isLeaf) {
            values = new ArrayList<>();
            next = null;
        } else {
            children = new ArrayList<>();
        }
    }

    /**
     * Find the index of the first key in the node that is not less than the given key.
     * @param key the key to compare
     * @return the index of the first key in the node that is not less than the given key
     */
    int findIndexOfFirstNotLessThan(K key) {
        int index = 0;
        while (index < keys.size() && keys.get(index).compareTo(key) < 0) {
            index++;
        }
        return index;
    }
}
