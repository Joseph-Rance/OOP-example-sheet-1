package uk.ac.cam.jr879.es19;

public class FunctionalArray {

    private BinaryTreeNode mHead;
    int length;

    private BinaryTreeNode constructEmptyArray(int length) {
        if (length == 0) {
            return null;
        } else if (length == 1) {
            return new BinaryTreeNode(0);
        } else {
            BinaryTreeNode head = new BinaryTreeNode(0);
            length--;  // account for head node above
            head.setLeft(constructEmptyArray((length / 2) + (length % 2)));
            head.setRight(constructEmptyArray(length / 2));
            return head;
        }
    }

    public FunctionalArray(int length) {
        this.length = length;
        mHead = constructEmptyArray(length);
    }

    public void set(int index, int value) {
        index++;  // convert between 0 and 1 indexing

        BinaryTreeNode current = mHead;

        while (index != 1) {
            if (index % 2 == 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
            index >>>= 1;
        }

        current.setValue(value);
    }

    public int get(int index) {

        if (index >= length || index < 0) {
            throw new IllegalArgumentException();
        }

        index++;  // convert between 0 and 1 indexing

        BinaryTreeNode current = mHead;

        while (index != 1) {
            if (index % 2 == 0) {
                index >>>= 1;
                current = current.getLeft();
            } else {
                index >>>= 1;
                current = current.getRight();
            }
        }

        return current.getValue();
    }
}