// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 226: Invert Binary Tree
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Recursive DFS Approach:
 * - Base Case: If current node is null, return null.
 * - Swap the left and right subtrees of current node.
 * - Recursively invert the left and right subtrees.
 *
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(h) where h is the height of the tree (recursion stack)
 */
public class p226 {

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

    // Method to invert binary tree
    public TreeNode invertTree(TreeNode root) {

        // Base case: empty tree
        if (root == null) {
            return null;
        }

        // Temporarily store left child before swapping
        TreeNode temp = root.left;

        // Invert subtrees and swap
        root.left = invertTree(root.right);
        root.right = invertTree(temp);

        return root;
    }

    // Helper method to print tree in in-order traversal
    public static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p226 obj = new p226();

        // Build tree:
        //      4
        //    /   \
        //   2     7
        //  / \   / \
        // 1   3 6   9
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        root.right = new TreeNode(7, new TreeNode(6), new TreeNode(9));

        System.out.print("Original tree (in-order): ");
        printInOrder(root);
        System.out.println();

        TreeNode inverted = obj.invertTree(root);

        System.out.print("Inverted tree (in-order): ");
        printInOrder(inverted);
        System.out.println();
    }
}
