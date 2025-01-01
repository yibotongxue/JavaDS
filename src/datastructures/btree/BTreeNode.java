package datastructures.btree;

import java.util.ArrayList;
import java.util.List;

class BTreeNode<K extends Comparable<K>> {
    boolean isLeaf;
    List<K> keys;
    List<BTreeNode<K>> children;

    BTreeNode(boolean isLeaf) {
        this.isLeaf = isLeaf;
        keys = new ArrayList<>();
        children = new ArrayList<>();
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
