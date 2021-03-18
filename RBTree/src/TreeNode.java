/**
 * @Author: DEUSJIN
 * @Date: 2021/2/4 9:42
 */
public class TreeNode<T extends Comparable<T>>{
    private T val;
    private boolean color;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;

    public TreeNode(T val, boolean color, TreeNode left, TreeNode right, TreeNode parent) {
        this.color = color;
        this.val = val;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "color=" + color +
                ", val=" + val+"}";
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

}
