package datastructures.bst;

public class AVLTree<T extends Comparable<T>> extends BST<T> {
    static class Node<T> extends BST.Node<T> {
        int height;
        int balance_factor;

        Node(T value) {
            super(value);
            this.height = 1;
            this.balance_factor = 0;
        }

        Node<T> getCastLeft() {
            return (Node<T>) left;
        }

        Node<T> getCastRight() {
            return (Node<T>) right;
        }

        int getLeftHeight() {
            if (left == null) {
                return 0;
            }
            return getCastLeft().height;
        }

        int getRightHeight() {
            if (right == null) {
                return 0;
            }
            return getCastRight().height;
        }

        int getLeftBalanceFactor() {
            return getCastLeft().balance_factor;
        }

        int getRightBalanceFactor() {
            return getCastRight().balance_factor;
        }

        void updateHeightAndBalanceFactor() {
            height = Math.max(getLeftHeight(), getRightHeight()) + 1;
            balance_factor = getRightHeight() - getLeftHeight();
        }
    }

    @Override
    public void insert(T value) {
        root = insertRecursive((Node<T>) root, value);
    }

    private Node<T> insertRecursive(Node<T> node, T value) {
        if (node == null) {
            return new Node<>(value);
        }
        if (less_than(node.value, value)) {
            node.right = insertRecursive(node.getCastRight(), value);
        } else if (less_than(value, node.value)) {
            node.left = insertRecursive(node.getCastLeft(), value);
        }
        node.updateHeightAndBalanceFactor();
        return balance(node);
    }

    private Node<T> balance(Node<T> node) {
        if (node.balance_factor == -2) {
            if (node.getLeftBalanceFactor() == -1) {
                node = singleRotateLeft(node);
            } else {
                node = doubleRotateLeft(node);
            }
        } else if (node.balance_factor == 2) {
            if (node.getRightBalanceFactor() == 1) {
                node = singleRotateRight(node);
            } else {
                node = doubleRotateRight(node);
            }
        }
        return node;
    }

    // for LL
    private Node<T> singleRotateLeft(Node<T> node) {
        Node<T> left_child = node.getCastLeft();
        Node<T> temp = left_child.getCastRight();
        left_child.right = node;
        node.left = temp;
        node.updateHeightAndBalanceFactor();
        left_child.updateHeightAndBalanceFactor();
        return left_child;
    }

    // for RR
    private Node<T> singleRotateRight(Node<T> node) {
        Node<T> right_child = node.getCastRight();
        Node<T> temp = right_child.getCastLeft();
        right_child.left = node;
        node.right = temp;
        node.updateHeightAndBalanceFactor();
        right_child.updateHeightAndBalanceFactor();
        return right_child;
    }

    // for RL
    private Node<T> doubleRotateRight(Node<T> node) {
        node.right = singleRotateLeft(node.getCastRight());
        return singleRotateRight(node);
    }

    // for LR
    private Node<T> doubleRotateLeft(Node<T> node) {
        node.left = singleRotateRight(node.getCastLeft());
        return singleRotateLeft(node);
    }

    // 判断树是否是 AVL 树
    public boolean isAVLTree() {
        return isAVLRecursive((Node<T>) root) != -1;
    }

    private int isAVLRecursive(Node<T> node) {
        if (node == null) {
            return 0; // 空节点的高度为 0
        }

        // 递归检查左右子树是否符合 AVL 树性质
        int leftHeight = isAVLRecursive((Node<T>) node.left);
        int rightHeight = isAVLRecursive((Node<T>) node.right);

        // 如果左右子树不是 AVL 树，或者平衡因子不符合要求，则返回 -1
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // 更新当前节点的高度
        node.height = 1 + Math.max(leftHeight, rightHeight);

        // 返回当前节点的高度
        return node.height;
    }
}
