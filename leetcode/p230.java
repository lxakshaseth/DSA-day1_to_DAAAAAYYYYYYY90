// Defines that this class belongs to the leetcode package
package leetcode;

// Import Stack utility class
import java.util.Stack;

/*
 * LeetCode 230: Kth Smallest Element in a BST
 * Given the root of a binary search tree, and an integer k, return the kth smallest value
 * (1-indexed) of all the values of the nodes in the tree.
 *
 * In-Order Traversal Approach (Iterative with Stack):
 * - An in-order traversal (Left -> Node -> Right) visits BST nodes in strictly ascending order.
 * - By iteratively performing in-order traversal with a stack, we can stop early as soon
 *   as we reach the kth element.
 *
 * Time Complexity: O(H + k) where H is tree height (O(log n + k) for balanced BST)
 * Space Complexity: O(H) recursion/stack depth
 */
public class p230 {

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

    // Method to find kth smallest element in BST
    public int kthSmallest(TreeNode root, int k) {

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {

            // Traverse all the way down to the leftmost node
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Process node
            curr = stack.pop();
            k--;

            // When k reaches 0, current node is the kth smallest
            if (k == 0) {
                return curr.val;
            }

            // Move to right subtree
            curr = curr.right;
        }

        return -1;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p230 obj = new p230();

        // Construct BST:
        //       5
        //      / \
        //     3   6
        //    / \
        //   2   4
        //  /
        // 1
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4));
        root.right = new TreeNode(6);

        // Test with k = 3 -> 3rd smallest is 3
        int k = 3;
        System.out.println(k + "rd smallest element: " + obj.kthSmallest(root, k));
    }
}
