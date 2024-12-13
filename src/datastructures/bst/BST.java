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
    }

    public BST() {
        root = null;
    }

    public void insert(T value) {
        root = insertRecursive(root, value);
    }

    private Node<T> insertRecursive(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }
        if (less_than(node.value, value)) {
            node.right = insertRecursive(node.right, value);
        } else if (less_than(value, node.value)) {
            node.left = insertRecursive(node.left, value);
        }
        return node;
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
        return node;
    }

    private T findMin(Node<T> node) {
        if (node.left == null) {
            return node.value;
        }
        return findMin(node.left);
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

    protected boolean less_than(T lhs, T rhs) {
        return lhs.compareTo(rhs) < 0;
    }
}
