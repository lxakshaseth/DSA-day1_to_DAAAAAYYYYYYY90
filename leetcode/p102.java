// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * LeetCode 102: Binary Tree Level Order Traversal
 * Given the root of a binary tree, return the level order traversal of its nodes' values.
 * (i.e., from left to right, level by level).
 *
 * Breadth-First Search (BFS) / Queue Approach:
 * - Use a Queue to process nodes level by level.
 * - At each iteration, capture the queue's size to know how many nodes belong to the current level.
 * - Enqueue child nodes for the next level while recording values for the current level.
 *
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) for the queue storing the largest level
 */
public class p102 {

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

    // Method to perform level order traversal
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                // Enqueue left and right children if they exist
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    // Main method to test the solution
    public static void main(String[] args) {

        p102 obj = new p102();

        // Construct tree:
        //     3
        //    / \
        //   9  20
        //      /  \
        //     15   7
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20, new TreeNode(15), new TreeNode(7));

        // Output: [[3], [9, 20], [15, 7]]
        List<List<Integer>> result = obj.levelOrder(root);
        System.out.println("Level order traversal: " + result);
    }
}
