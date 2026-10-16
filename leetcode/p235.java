// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 235: Lowest Common Ancestor of a Binary Search Tree
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both
 * p and q as descendants (where we allow a node to be a descendant of itself).
 *
 * BST Property Approach:
 * - If both p and q have values less than current node, the split point (LCA) lies in left subtree.
 * - If both p and q have values greater than current node, the split point (LCA) lies in right subtree.
 * - If p and q lie on different sides (or one equals current node), current node is the LCA.
 *
 * Time Complexity: O(h) where h is the height of the BST (O(log n) for balanced BST)
 * Space Complexity: O(1) iterative
 */
public class p235 {

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Method to find Lowest Common Ancestor in a BST
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        TreeNode curr = root;

        while (curr != null) {

            // Both nodes are in the left subtree
            if (p.val < curr.val && q.val < curr.val) {
                curr = curr.left;
            }
            // Both nodes are in the right subtree
            else if (p.val > curr.val && q.val > curr.val) {
                curr = curr.right;
            }
            // Split point found: curr is the Lowest Common Ancestor
            else {
                return curr;
            }
        }

        return null;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p235 obj = new p235();

        // Construct BST:
        //        6
        //      /   \
        //     2     8
        //    / \   / \
        //   0   4 7   9
        //      / \
        //     3   5
        TreeNode node3 = new TreeNode(3);
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4, node3, node5);
        TreeNode node0 = new TreeNode(0);
        TreeNode node2 = new TreeNode(2, node0, node4);

        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node8 = new TreeNode(8, node7, node9);

        TreeNode root = new TreeNode(6, node2, node8);

        // Test LCA of node 2 and node 8 -> LCA is 6
        TreeNode lca1 = obj.lowestCommonAncestor(root, node2, node8);
        System.out.println("LCA of 2 and 8: " + lca1.val);

        // Test LCA of node 2 and node 4 -> LCA is 2
        TreeNode lca2 = obj.lowestCommonAncestor(root, node2, node4);
        System.out.println("LCA of 2 and 4: " + lca2.val);
    }
}
