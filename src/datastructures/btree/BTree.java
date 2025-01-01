package datastructures.btree;

public class BTree<K extends Comparable<K>> {
    private BTreeNode<K> root;
    private final int maxChildrenCount;

    public BTree(int maxChildrenCount) {
        this.maxChildrenCount = maxChildrenCount;
        root = new BTreeNode<>(true);
    }

    public void insert(K key) {
        insert(root, key);
        if (root.keys.size() == maxChildrenCount) {
            BTreeNode<K> newRoot = new BTreeNode<>(false);
            newRoot.children.add(root);
            splitChild(newRoot, 0);
            root = newRoot;
        }
    }

    private void insert(BTreeNode<K> node, K key) {
        int i = node.findIndexOfFirstNotLessThan(key);
        if (node.isLeaf) {
            node.keys.add(i, key);
        } else {
            insert(node.children.get(i), key);
            if (node.children.get(i).keys.size() == maxChildrenCount) {
                splitChild(node, i);
            }
        }
    }

    private void splitChild(BTreeNode<K> parent, int childIndex) {
        BTreeNode<K> child = parent.children.get(childIndex);
        BTreeNode<K> newChild = new BTreeNode<>(child.isLeaf);
        int mid = maxChildrenCount / 2;
        K newKey = child.keys.get(mid);
        newChild.keys.addAll(child.keys.subList(mid + 1, child.keys.size()));
        child.keys.subList(mid, child.keys.size()).clear();
        if (!child.isLeaf) {
            newChild.children.addAll(child.children.subList(mid + 1, child.children.size()));
            child.children.subList(mid + 1, child.children.size()).clear();
        }
        parent.keys.add(childIndex, newKey);
        parent.children.add(childIndex + 1, newChild);
    }

    public boolean search(K key) {
        return search(root, key);
    }

    private boolean search(BTreeNode<K> node, K key) {
        int i = node.findIndexOfFirstNotLessThan(key);
        if (i < node.keys.size() && equal(node.keys.get(i), key)) {
            return true;
        }
        if (node.isLeaf) {
            return false;
        }
        return search(node.children.get(i), key);
    }

    private boolean equal(K lhs, K rhs) {
        return lhs.compareTo(rhs) == 0;
    }
}
