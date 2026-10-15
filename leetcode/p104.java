// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 104: Maximum Depth of Binary Tree
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 *
 * Recursive Depth-First Search (DFS) Approach:
 * - Base Case: An empty tree (null) has a depth of 0.
 * - Recursively find the maximum depth of the left and right subtrees.
 * - The depth of current node is 1 + max(depth of left, depth of right).
 *
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(h) where h is the tree height (call stack)
 */
public class p104 {

    // Definition for a binary tree node
    public static class TreeNode {
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

    // Method to calculate maximum depth of binary tree
    public int maxDepth(TreeNode root) {

        // Base case: null node has height 0
        if (root == null) {
            return 0;
        }

        // Recursively compute depth of left and right subtrees
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);

        // Maximum depth at current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p104 obj = new p104();

        // Build sample tree:
        //     3
        //    / \
        //   9  20
        //      /  \
        //     15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));

        // Output: 3
        System.out.println("Maximum depth of tree: " + obj.maxDepth(root));
    }
}
