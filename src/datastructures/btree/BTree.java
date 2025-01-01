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
        if (i < node.keys.size() && equal(node.keys.get(i), key)) {
            return;
        }
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

    public void delete(K key) {
        delete(root, key);
        if (root.keys.isEmpty() && !root.isLeaf) {
            root = root.children.getFirst();
        }
    }

    private void delete(BTreeNode<K> node, K key) {
        int i = node.findIndexOfFirstNotLessThan(key);
        if (node.isLeaf) {
            if (i < node.keys.size() && equal(node.keys.get(i), key)) {
                node.keys.remove(i);
            }
        } else {
            if (i < node.keys.size() && equal(node.keys.get(i), key)) {
                BTreeNode<K> predecessor = node.children.get(i);
                while (!predecessor.isLeaf) {
                    predecessor = predecessor.children.getLast();
                }
                K predecessorKey = predecessor.keys.getLast();
                node.keys.set(i, predecessorKey);
                predecessor.keys.set(predecessor.keys.size() - 1, key);
            }
            delete(node.children.get(i), key);
            if (node.children.get(i).keys.size() < getMinChildrenCount()) {
                borrowOrMerge(node, i);
            }
        }
    }

    private void borrowOrMerge(BTreeNode<K> node, int i) {
        if (i > 0 && node.children.get(i - 1).keys.size() > getMinChildrenCount()) {
            borrowFromLeftSibling(node, i);
        } else if (i < node.children.size() - 1 && node.children.get(i + 1).keys.size() > getMinChildrenCount()) {
            borrowFromRightSibling(node, i);
        } else {
            mergeWithSibling(node, i);
        }
    }

    private void mergeWithSibling(BTreeNode<K> node, int i) {
        if (i > 0) {
            mergeWithLeftSibling(node, i);
        } else {
            mergeWithRightSibling(node, i);
        }
    }

    private void mergeWithLeftSibling(BTreeNode<K> node, int i) {
        BTreeNode<K> child = node.children.get(i);
        BTreeNode<K> leftSibling = node.children.get(i - 1);
        leftSibling.keys.add(node.keys.get(i - 1));
        leftSibling.keys.addAll(child.keys);
        if (!child.isLeaf) {
            leftSibling.children.addAll(child.children);
        }
        node.keys.remove(i - 1);
        node.children.remove(i);
    }

    private void mergeWithRightSibling(BTreeNode<K> node, int i) {
        BTreeNode<K> child = node.children.get(i);
        BTreeNode<K> rightSibling = node.children.get(i + 1);
        child.keys.add(node.keys.get(i));
        child.keys.addAll(rightSibling.keys);
        if (!child.isLeaf) {
            child.children.addAll(rightSibling.children);
        }
        node.keys.remove(i);
        node.children.remove(i + 1);
    }

    private void borrowFromRightSibling(BTreeNode<K> node, int i) {
        BTreeNode<K> child = node.children.get(i);
        BTreeNode<K> rightSibling = node.children.get(i + 1);
        child.keys.addLast(node.keys.get(i));
        node.keys.set(i, rightSibling.keys.getFirst());
        rightSibling.keys.removeFirst();
        if (!child.isLeaf) {
            child.children.addLast(rightSibling.children.getFirst());
            rightSibling.children.removeFirst();
        }
    }

    private void borrowFromLeftSibling(BTreeNode<K> node, int i) {
        BTreeNode<K> child = node.children.get(i);
        BTreeNode<K> leftSibling = node.children.get(i - 1);
        child.keys.addFirst(node.keys.get(i - 1));
        node.keys.set(i - 1, leftSibling.keys.getLast());
        leftSibling.keys.removeLast();
        if (!child.isLeaf) {
            child.children.addFirst(leftSibling.children.getLast());
            leftSibling.children.removeLast();
        }
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

    private int getMinChildrenCount() {
        return (int) Math.ceil(maxChildrenCount / 2.0) - 1;
    }
}
