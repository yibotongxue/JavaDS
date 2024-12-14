package datastructures.bst;

public class BST<T extends Comparable<T>> {
    Node<T> root;

    protected static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
            this.left = this.right = null;
        }

        public Node<T> getLeft() {
            return left;
        }

        T getValue() {
            return value;
        }

        public Node<T> getRight() {
            return right;
        }

        protected void updateStatus() {}
    }

    public BST() {
        root = null;
    }

    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    protected Node<T> insertRecursive(Node<T> node, T value) {
        if (node == null) {
            return createNewNode(value);
        }
        if (less_than(node.value, value)) {
            node.right = insertRecursive(node.right, value);
        } else if (less_than(value, node.value)) {
            node.left = insertRecursive(node.left, value);
        }
        node.updateStatus();
        return updateStructure(node);
    }

    public void delete(T value) {
        root = deleteRecursive(root, value);
    }

    private Node<T> deleteRecursive(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        if (less_than(node.value, value)) {
            node.right = deleteRecursive(node.right, value);
        } else if (less_than(value, node.value)) {
            node.left = deleteRecursive(node.left, value);
        } else {
            if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                node.value = findMin(node.right);
                node.right = deleteRecursive(node.right, node.value);
            }
        }
        if (node != null) {
            node.updateStatus();
            return updateStructure(node);
        }
        return null;
    }

    protected T findMin(Node<T> node) {
        if (node.left == null) {
            return node.value;
        }
        return findMin(node.left);
    }

    protected Node<T> updateStructure(Node<T> node) {
        return node;
    }

    public boolean search(T value) {
        return searchRecursive(root, value);
    }

    private boolean searchRecursive(Node<T> node, T value) {
        if (node == null) {
            return false;
        }
        if (less_than(node.value, value)) {
            return searchRecursive(node.right, value);
        } else if (less_than(value, node.value)) {
            return searchRecursive(node.left, value);
        }
        return node.value.equals(value);
    }

    // for LL
    protected Node<T> rotateRight(Node<T> node) {
        Node<T> left_child = node.left;
        Node<T> temp = left_child.right;
        left_child.right = node;
        node.left = temp;
        node.updateStatus();
        left_child.updateStatus();
        return left_child;
    }

    // for RR
    protected Node<T> rotateLeft(Node<T> node) {
        Node<T> right_child = node.right;
        Node<T> temp = right_child.left;
        right_child.left = node;
        node.right = temp;
        node.updateStatus();
        right_child.updateStatus();
        return right_child;
    }

    protected BST.Node<T> leftRightRotate(BST.Node<T> node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    protected BST.Node<T> rightLeftRotate(BST.Node<T> node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    protected boolean less_than(T lhs, T rhs) {
        return lhs.compareTo(rhs) < 0;
    }

    protected Node<T> createNewNode(T value) {
        return new Node<>(value);
    }
}
