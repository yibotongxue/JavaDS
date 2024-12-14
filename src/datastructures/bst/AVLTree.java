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

        @Override
        public void updateStatus() {
            height = Math.max(getLeftHeight(), getRightHeight()) + 1;
            balance_factor = getRightHeight() - getLeftHeight();
        }
    }

    @Override
    protected BST.Node<T> updateStructure(BST.Node<T> node) {
        Node<T> cast_node = (Node<T>) node;
        if (cast_node.balance_factor == -2) {
            if (cast_node.getLeftBalanceFactor() == -1) {
                node = rotateRight(node);
            } else {
                node = rightLeftRotate(node);
            }
        } else if (cast_node.balance_factor == 2) {
            if (cast_node.getRightBalanceFactor() == 1) {
                node = rotateLeft(node);
            } else {
                node = leftRightRotate(node);
            }
        }
        return node;
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

    @Override
    protected BST.Node<T> createNewNode(T value) {
        return new Node<>(value);
    }
}
