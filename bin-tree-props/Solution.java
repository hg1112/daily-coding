import java.util.*;

class Solution {

  // Problem 1
  public int maxDepth(TreeNode root) {
    if (root == null) return 0;
    Map<TreeNode, Integer> depth = new HashMap<>();
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    depth.put(root, 0);
    int ans = 0;
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      ans = Math.max(depth.get(node), ans);
      if (node.left != null) {
        stack.push(node.left);
        depth.put(node.left, depth.get(node) + 1);
      }
      if (node.right != null) {
        stack.push(node.right);
        depth.put(node.right, depth.get(node) + 1);
      }
    }
    return ans + 1;
  }

  // Problem 2
  public int diameterOfBinaryTree(TreeNode root) {
    int[] ans = {0};
    depth(root, ans);
    return ans[0];
  }

  private int depth(TreeNode node, int[] ans) {
    if (node == null)
      return 0;
    else {
      int left = depth(node.left, ans);
      int right = depth(node.right, ans);
      ans[0] = Math.max(ans[0], left + right);
      return Math.max(left, right) + 1;
    }
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
