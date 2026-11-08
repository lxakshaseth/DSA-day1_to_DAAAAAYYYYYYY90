// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 236: Lowest Common Ancestor of a Binary Tree
 * Given a binary tree, find the lowest common ancestor (LCA) node of two given nodes p and q.
 * The lowest common ancestor is defined between two nodes p and q as the lowest node in T
 * that has both p and q as descendants (where we allow a node to be a descendant of itself).
 *
 * Recursive Post-Order Depth-First Search (DFS) Approach:
 * - In a general binary tree (without BST ordering properties), we recursively search
 *   for p and q in both the left and right subtrees:
 *   1. Base cases:
 *      - If root == null, return null.
 *      - If root == p or root == q, return root (target found).
 *   2. Recursively search left and right children:
 *      - left = lowestCommonAncestor(root.left, p, q)
 *      - right = lowestCommonAncestor(root.right, p, q)
 *   3. Evaluation:
 *      - If both left and right return non-null, p and q are in different subtrees of root;
 *        hence, root is their Lowest Common Ancestor.
 *      - If only one side returns non-null, return that non-null node (both targets or LCA are on that side).
 *      - If both are null, return null.
 *
 * Time Complexity: O(n) where n is the number of nodes in the binary tree
 * Space Complexity: O(h) recursion call stack depth where h is the tree height (O(n) worst-case)
 */
public class p236 {

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

    // Method to find Lowest Common Ancestor in a binary tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // Base case: reached leaf null, or found one of the targets
        if (root == null || root == p || root == q) {
            return root;
        }

        // Search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both left and right returned non-null, root is the LCA split point
        if (left != null && right != null) {
            return root;
        }

        // Otherwise return the non-null result (or null if both are null)
        return (left != null) ? left : right;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p236 obj = new p236();

        // Construct sample binary tree:
        //        3
        //       / \
        //      5   1
        //     / \ / \
        //    6  2 0  8
        //      / \
        //     7   4
        TreeNode node7 = new TreeNode(7);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2, node7, node4);
        TreeNode node6 = new TreeNode(6);
        TreeNode node5 = new TreeNode(5, node6, node2);

        TreeNode node0 = new TreeNode(0);
        TreeNode node8 = new TreeNode(8);
        TreeNode node1 = new TreeNode(1, node0, node8);

        TreeNode root = new TreeNode(3, node5, node1);

        // Test case 1: LCA of node 5 and node 1 -> 3
        TreeNode lca1 = obj.lowestCommonAncestor(root, node5, node1);
        System.out.println("LCA of 5 and 1: " + lca1.val); // 3

        // Test case 2: LCA of node 5 and node 4 -> 5
        TreeNode lca2 = obj.lowestCommonAncestor(root, node5, node4);
        System.out.println("LCA of 5 and 4: " + lca2.val); // 5

        // Test case 3: LCA of node 6 and node 4 -> 5
        TreeNode lca3 = obj.lowestCommonAncestor(root, node6, node4);
        System.out.println("LCA of 6 and 4: " + lca3.val); // 5
    }
}
