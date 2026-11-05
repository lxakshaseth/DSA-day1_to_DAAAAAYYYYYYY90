// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * LeetCode 199: Binary Tree Right Side View
 * Given the root of a binary tree, imagine yourself standing on the right side of it.
 * Return the values of the nodes you can see ordered from top to bottom.
 *
 * Breadth-First Search (BFS) / Level-by-Level Queue Approach:
 * - Use a Queue to process nodes level by level.
 * - At each level, record the queue size (the number of nodes in that level).
 * - Process each node in the level:
 *   - The last node processed at each level (index == levelSize - 1) is the rightmost visible node.
 *   - Append its value to the result list.
 *   - Enqueue left and right child nodes for the next level.
 *
 * Time Complexity: O(n) where n is the number of nodes (each node visited once)
 * Space Complexity: O(D) where D is the maximum width / diameter of the tree (up to O(n))
 */
public class p199 {

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

    // Method to return the right side view of a binary tree
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // If this is the last node of the current level, it is visible from the right
                if (i == levelSize - 1) {
                    result.add(current.val);
                }

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p199 obj = new p199();

        // Test case 1:
        //        1
        //       / \
        //      2   3
        //       \   \
        //        5   4
        // Expected right side view: [1, 3, 4]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);
        System.out.println("Right side view 1: " + obj.rightSideView(root1));

        // Test case 2:
        //    1
        //     \
        //      3
        // Expected right side view: [1, 3]
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(3);
        System.out.println("Right side view 2: " + obj.rightSideView(root2));

        // Test case 3:
        //        1
        //       /
        //      2
        //     /
        //    4
        // Expected right side view: [1, 2, 4]
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(4);
        System.out.println("Right side view 3: " + obj.rightSideView(root3));

        // Test case 4: null tree
        System.out.println("Right side view 4 (empty): " + obj.rightSideView(null));
    }
}
