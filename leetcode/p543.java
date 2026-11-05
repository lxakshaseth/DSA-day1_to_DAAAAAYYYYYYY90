// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 543: Diameter of Binary Tree
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes
 * in a tree. This path may or may not pass through the root.
 * The length of a path is represented by the number of edges between them.
 *
 * Post-Order Depth-First Search (DFS) Approach:
 * - At every node, the longest path that uses this node as the turning point
 *   equals the maximum depth of its left subtree plus the maximum depth of its right subtree.
 * - Maintain a running maximum diameter across all visited nodes.
 * - The recursive helper returns the maximum depth (1 + max(leftDepth, rightDepth))
 *   to its parent node.
 *
 * Time Complexity: O(n) where n is the number of nodes (each node visited once)
 * Space Complexity: O(h) recursion call stack depth where h is the tree height (O(n) worst-case)
 */
public class p543 {

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

    private int maxDiameter;

    // Helper method to compute tree depth and update global diameter
    private int computeDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftDepth = computeDepth(node.left);
        int rightDepth = computeDepth(node.right);

        // Longest path through this node is leftDepth + rightDepth edges
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        // Return height of current subtree to parent
        return 1 + Math.max(leftDepth, rightDepth);
    }

    // Method to calculate the diameter of binary tree
    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        computeDepth(root);
        return maxDiameter;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p543 obj = new p543();

        // Test case 1:
        //        1
        //       / \
        //      2   3
        //     / \
        //    4   5
        // Longest path: 4 -> 2 -> 1 -> 3 (or 5 -> 2 -> 1 -> 3), length = 3 edges
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        root1.right = new TreeNode(3);
        System.out.println("Diameter of tree 1: " + obj.diameterOfBinaryTree(root1)); // 3

        // Test case 2:
        //    1
        //   /
        //  2
        // Length = 1 edge
        TreeNode root2 = new TreeNode(1, new TreeNode(2), null);
        System.out.println("Diameter of tree 2: " + obj.diameterOfBinaryTree(root2)); // 1

        // Test case 3: Single node tree
        TreeNode root3 = new TreeNode(1);
        System.out.println("Diameter of tree 3 (single node): " + obj.diameterOfBinaryTree(root3)); // 0
    }
}
