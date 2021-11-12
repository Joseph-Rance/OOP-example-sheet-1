package uk.ac.cam.jr879.es19;

public class BinaryTreeNode {

    private int mValue;
    private BinaryTreeNode mLeft = null;  // do I need to do this explicitly?
    private BinaryTreeNode mRight = null;

    public BinaryTreeNode(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int newValue) {
        mValue = newValue;
    }

    public BinaryTreeNode getLeft() {
        return mLeft;
    }

    public BinaryTreeNode getRight() {
        return mRight;
    }

    public void setRight(BinaryTreeNode newRight) {
        this.mRight = newRight;
    }

    public void setLeft(BinaryTreeNode newLeft) {
        this.mLeft = newLeft;
    }
}