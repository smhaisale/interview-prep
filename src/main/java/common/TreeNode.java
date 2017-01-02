package common;

/**
 * Created by shahbaaz on 12/31/16.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }
    public TreeNode(int x, TreeNode l, TreeNode r) { val = x; left = l; right = r; }
}
