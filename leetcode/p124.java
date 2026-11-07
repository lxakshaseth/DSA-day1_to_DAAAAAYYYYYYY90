// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 124: Binary Tree Maximum Path Sum
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes has an edge.
 * A node can only appear at most once in the path. The path does not need to pass through the root.
 * Return the maximum path sum of any non-empty path.
 *
 * Post-Order Depth-First Search (DFS) Approach:
 * - At each node, we consider two scenarios:
 *   1. The node acts as the "apex" or turning point of the path:
 *      Path sum = node.val + leftMaxContribution + rightMaxContribution.
 *      Update global maxSum with this value.
 *   2. The node continues upward to its parent:
 *      Can only choose at most one subtree branch (left or right, whichever is greater).
 *      Return node.val + max(leftMaxContribution, rightMaxContribution).
 * - Pruning Negative Contributions:
 *   If a subtree's path sum contribution is negative, we prune it by taking max(0, gain),
 *   as adding a negative sum would only reduce the total.
 *
 * Time Complexity: O(n) where n is the number of nodes (each node visited once)
 * Space Complexity: O(h) recursion call stack depth where h is tree height (O(n) worst-case)
 */
public class p124 {

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

    private int maxSum;

    // Helper method to compute maximum branch gain and update global maximum path sum
    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively compute max contribution of left and right subtrees;
        // prune negative contributions using Math.max(0, ...)
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        // Path sum with current node as the turning apex
        int currentPathSum = node.val + leftGain + rightGain;

        // Update the global maximum path sum
        maxSum = Math.max(maxSum, currentPathSum);

        // Return maximum single-branch path extending to parent
        return node.val + Math.max(leftGain, rightGain);
    }

    // Method to find the maximum path sum in a binary tree
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p124 obj = new p124();

        // Test case 1:
        //      1
        //     / \
        //    2   3
        // Expected: 2 + 1 + 3 = 6
        TreeNode root1 = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println("Max path sum 1: " + obj.maxPathSum(root1)); // 6

        // Test case 2:
        //       -10
        //       /  \
        //      9   20
        //          /  \
        //         15   7
        // Expected: 15 + 20 + 7 = 42
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));
        System.out.println("Max path sum 2: " + obj.maxPathSum(root2)); // 42

        // Test case 3: Single negative node
        // Expected: -3
        TreeNode root3 = new TreeNode(-3);
        System.out.println("Max path sum 3 (single negative): " + obj.maxPathSum(root3)); // -3
    }
}
