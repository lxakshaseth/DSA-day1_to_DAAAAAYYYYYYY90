// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * LeetCode 106: Construct Binary Tree from Inorder and Postorder Traversal
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal
 * of a binary tree and postorder is the postorder traversal of the same tree, construct and
 * return the binary tree.
 *
 * Divide and Conquer + HashMap Lookup Approach:
 * - Postorder traversal visits nodes in Left -> Right -> Root order.
 *   Reading postorder backwards (from index n - 1 down to 0) visits nodes in Root -> Right -> Left order.
 * - Inorder traversal visits nodes in Left -> Root -> Right order. Finding the root's value
 *   in the inorder array divides the array into:
 *   - Left subtree elements: in range [inStart, inIndex - 1].
 *   - Right subtree elements: in range [inIndex + 1, inEnd].
 * - Pre-populate a HashMap with (value -> index) from the inorder array for O(1) index lookups.
 * - Recursively construct:
 *   1. Root node from postorder[postorderIndex--].
 *   2. Right subtree bounded by [inIndex + 1, inEnd] (must be built first because reverse postorder visits Right before Left).
 *   3. Left subtree bounded by [inStart, inIndex - 1].
 *
 * Time Complexity: O(n) where n is the number of nodes (each node processed once with O(1) map lookups)
 * Space Complexity: O(n) for the HashMap and O(h) recursion stack depth (up to O(n) worst-case)
 */
public class p106 {

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

    private int postorderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    // Helper recursive method to build the subtree within inorder range [inStart, inEnd]
    private TreeNode arrayToTree(int[] postorder, int inStart, int inEnd) {
        // Base case: no elements left in this subtree
        if (inStart > inEnd) {
            return null;
        }

        // Pick current root from postorder traversal and decrement index
        int rootVal = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootVal);

        // Find root's position in inorder traversal
        int inIndex = inorderIndexMap.get(rootVal);

        // Build right subtree FIRST (since reverse postorder visits Root -> Right -> Left),
        // then build left subtree
        root.right = arrayToTree(postorder, inIndex + 1, inEnd);
        root.left = arrayToTree(postorder, inStart, inIndex - 1);

        return root;
    }

    // Method to construct binary tree from inorder and postorder arrays
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postorderIndex = postorder.length - 1;
        inorderIndexMap = new HashMap<>();

        // Store value to index mapping for O(1) lookups
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(postorder, 0, inorder.length - 1);
    }

    // Helper method to convert tree to level-order list for testing output
    public static List<String> levelOrder(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr != null) {
                result.add(String.valueOf(curr.val));
                queue.offer(curr.left);
                queue.offer(curr.right);
            } else {
                result.add("null");
            }
        }

        // Trim trailing nulls from representation
        int lastNonNull = result.size() - 1;
        while (lastNonNull >= 0 && result.get(lastNonNull).equals("null")) {
            lastNonNull--;
        }
        return result.subList(0, lastNonNull + 1);
    }

    // Main method to test the solution
    public static void main(String[] args) {
        p106 obj = new p106();

        // Test case 1:
        // inorder = [9, 3, 15, 20, 7]
        // postorder = [9, 15, 7, 20, 3]
        // Expected tree level order: [3, 9, 20, null, null, 15, 7]
        int[] inorder1 = { 9, 3, 15, 20, 7 };
        int[] postorder1 = { 9, 15, 7, 20, 3 };
        TreeNode root1 = obj.buildTree(inorder1, postorder1);
        System.out.println("Constructed Tree 1 (Level Order): " + levelOrder(root1));

        // Test case 2:
        // inorder = [-1]
        // postorder = [-1]
        // Expected: [-1]
        int[] inorder2 = { -1 };
        int[] postorder2 = { -1 };
        TreeNode root2 = obj.buildTree(inorder2, postorder2);
        System.out.println("Constructed Tree 2 (Level Order): " + levelOrder(root2));

        // Test case 3:
        // inorder = [4, 2, 5, 1, 3]
        // postorder = [4, 5, 2, 3, 1]
        // Expected: [1, 2, 3, 4, 5]
        int[] inorder3 = { 4, 2, 5, 1, 3 };
        int[] postorder3 = { 4, 5, 2, 3, 1 };
        TreeNode root3 = obj.buildTree(inorder3, postorder3);
        System.out.println("Constructed Tree 3 (Level Order): " + levelOrder(root3));
    }
}
