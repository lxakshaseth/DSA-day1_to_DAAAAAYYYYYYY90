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
 * LeetCode 105: Construct Binary Tree from Preorder and Inorder Traversal
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal
 * of a binary tree and inorder is the inorder traversal of the same tree, construct and
 * return the binary tree.
 *
 * Divide and Conquer + HashMap Lookup Approach:
 * - Preorder traversal visits nodes in Root -> Left -> Right order. The first element
 *   (or current preorder pointer) is always the root of the current subtree.
 * - Inorder traversal visits nodes in Left -> Root -> Right order. Finding the root's value
 *   in the inorder array divides the array into:
 *   - Left subtree elements: all values to the left of root's inorder index.
 *   - Right subtree elements: all values to the right of root's inorder index.
 * - Pre-populate a HashMap with (value -> index) from inorder array for O(1) index lookups.
 * - Recursively construct:
 *   1. Root node from preorder[preorderIndex++].
 *   2. Left subtree bounded by [inStart, inIndex - 1].
 *   3. Right subtree bounded by [inIndex + 1, inEnd].
 *
 * Time Complexity: O(n) where n is the number of nodes (each node processed once, O(1) map lookups)
 * Space Complexity: O(n) for the HashMap and O(h) recursion stack depth (up to O(n) worst-case)
 */
public class p105 {

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

    private int preorderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    // Helper recursive method to build the subtree within inorder range [inStart, inEnd]
    private TreeNode arrayToTree(int[] preorder, int inStart, int inEnd) {
        // Base case: no elements left in this subtree
        if (inStart > inEnd) {
            return null;
        }

        // Pick current root from preorder traversal and increment index
        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        // Find root's position in inorder traversal
        int inIndex = inorderIndexMap.get(rootVal);

        // Build left and right subtrees
        root.left = arrayToTree(preorder, inStart, inIndex - 1);
        root.right = arrayToTree(preorder, inIndex + 1, inEnd);

        return root;
    }

    // Method to construct binary tree from preorder and inorder arrays
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderIndexMap = new HashMap<>();

        // Store value to index mapping for O(1) lookups
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(preorder, 0, inorder.length - 1);
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
        p105 obj = new p105();

        // Test case 1:
        // preorder = [3, 9, 20, 15, 7]
        // inorder = [9, 3, 15, 20, 7]
        // Expected tree level order: [3, 9, 20, null, null, 15, 7]
        int[] preorder1 = { 3, 9, 20, 15, 7 };
        int[] inorder1 = { 9, 3, 15, 20, 7 };
        TreeNode root1 = obj.buildTree(preorder1, inorder1);
        System.out.println("Constructed Tree 1 (Level Order): " + levelOrder(root1));

        // Test case 2:
        // preorder = [-1]
        // inorder = [-1]
        // Expected: [-1]
        int[] preorder2 = { -1 };
        int[] inorder2 = { -1 };
        TreeNode root2 = obj.buildTree(preorder2, inorder2);
        System.out.println("Constructed Tree 2 (Level Order): " + levelOrder(root2));

        // Test case 3:
        // preorder = [1, 2, 4, 5, 3]
        // inorder = [4, 2, 5, 1, 3]
        // Expected: [1, 2, 3, 4, 5]
        int[] preorder3 = { 1, 2, 4, 5, 3 };
        int[] inorder3 = { 4, 2, 5, 1, 3 };
        TreeNode root3 = obj.buildTree(preorder3, inorder3);
        System.out.println("Constructed Tree 3 (Level Order): " + levelOrder(root3));
    }
}
