import java.util.*;

class Solution {

  // Problem 1
  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null && subRoot == null) {
      return true;
    } else if (root == null && subRoot != null) {
      return false;
    } else if (root != null && subRoot == null) {
      return true;
    } else {
      if (preorder(root, subRoot))
        return true;
      else 
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }
  }

  private boolean preorder(TreeNode root, TreeNode subRoot) {
    if (root == null && subRoot == null)
      return true;
    else if (root == null || subRoot == null)
      return false;
    else 
      return (root.val == subRoot.val) && preorder(root.left, subRoot.left) && preorder(root.right, subRoot.right);
  }


  // Problem 2
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    int max = Math.max(p.val, q.val);
    int min = Math.min(p.val, q.val);
    if (root.val > max)
      return lowestCommonAncestor(root.left, p, q);
    else if (root.val < min) 
      return lowestCommonAncestor(root.right, p, q);
    else 
      return root;
  }
}

public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) { this.val = val; }
  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
