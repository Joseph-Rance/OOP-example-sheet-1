package uk.ac.cam.jr879.es19;

import java.util.function.BiFunction;

class SearchSet {

    private BinaryTreeNode mHead;
    int mElements;

    public SearchSet() {
        mHead = null;
        mElements = 0;
    }

    public void insert(int value) {

        BinaryTreeNode previous = null;
        BinaryTreeNode current = mHead;

        while (current != null) {

            previous = current;

            if (value < previous.getValue()) {
                current = current.getLeft();
            } else if (value > previous.getValue()) {
                current = current.getRight();
            } else {
                return;  // value already in the set
            }

        }

        mElements++;

        if (previous == null) {
            mHead = new BinaryTreeNode(value);
        } else if (value < previous.getValue()) {
            previous.setLeft(new BinaryTreeNode(value));
        } else {
            previous.setRight(new BinaryTreeNode(value));
        }
    }

    public int getNumberElements() {
        return mElements;
    }

    public boolean contains(int value) {

        BinaryTreeNode current = mHead;

        while (current != null) {

            if (current.getValue() == value) {
                return true;
            } else if (current.getValue() > value) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }

        }

        return false;
    }
}
