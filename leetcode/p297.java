// Defines that this class belongs to the leetcode package
package leetcode;

/*
 * LeetCode 297: Serialize and Deserialize Binary Tree
 *
 * Design an algorithm to serialize and deserialize a binary tree into a string representation
 * such that the reconstructed tree is structurally identical with identical node values.
 *
 * Optimal Approach: Pre-Order Depth-First Search (DFS) with Null Markers
 * - Serialization:
 *   - Traverse the tree in pre-order (root -> left -> right).
 *   - Append node values separated by a delimiter (",") and use a sentinel ("#") for null nodes.
 * - Deserialization:
 *   - Split the serialized string by the delimiter into tokens.
 *   - Maintain a cursor index array (int[1]) as we reconstruct nodes in pre-order:
 *     - If token is "#", return null.
 *     - Otherwise create TreeNode(val), then recursively attach left and right subtrees.
 * - Including explicit null markers makes pre-order traversal uniquely invertible without
 *   needing a companion in-order traversal.
 *
 * Time Complexity: O(n) for both serialize() and deserialize(), visiting each node once.
 * Space Complexity: O(n) for the serialized string, token array, and O(h) recursion stack.
 */
public class p297 {

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;
        }
    }

    public static class Codec {

        private static final String NULL_MARKER = "#";
        private static final String DELIMITER = ",";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            serializePreOrder(root, sb);
            return sb.toString();
        }

        private void serializePreOrder(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NULL_MARKER).append(DELIMITER);
                return;
            }
            sb.append(node.val).append(DELIMITER);
            serializePreOrder(node.left, sb);
            serializePreOrder(node.right, sb);
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data.isEmpty()) {
                return null;
            }
            String[] tokens = data.split(DELIMITER);
            int[] index = new int[1];
            return deserializePreOrder(tokens, index);
        }

        private TreeNode deserializePreOrder(String[] tokens, int[] index) {
            if (index[0] >= tokens.length) {
                return null;
            }
            String token = tokens[index[0]++];
            if (NULL_MARKER.equals(token)) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(token));
            node.left = deserializePreOrder(tokens, index);
            node.right = deserializePreOrder(tokens, index);
            return node;
        }
    }

    // Helper method to verify structural and value equality between two trees
    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        Codec codec = new Codec();

        // Test Case 1: Standard tree [1, 2, 3, null, null, 4, 5]
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3, new TreeNode(4), new TreeNode(5)));
        String serialized1 = codec.serialize(root1);
        TreeNode deserialized1 = codec.deserialize(serialized1);
        System.out.println("Test Case 1 Serialized: " + serialized1);
        System.out.println("Test Case 1 Identical: " + isSameTree(root1, deserialized1)); // Expected: true

        // Test Case 2: Empty tree []
        TreeNode root2 = null;
        String serialized2 = codec.serialize(root2);
        TreeNode deserialized2 = codec.deserialize(serialized2);
        System.out.println("Test Case 2 Serialized: " + serialized2);
        System.out.println("Test Case 2 Identical: " + isSameTree(root2, deserialized2)); // Expected: true

        // Test Case 3: Negative values and skewed structure
        TreeNode root3 = new TreeNode(-10,
                new TreeNode(-20, null, new TreeNode(0)),
                null);
        String serialized3 = codec.serialize(root3);
        TreeNode deserialized3 = codec.deserialize(serialized3);
        System.out.println("Test Case 3 Serialized: " + serialized3);
        System.out.println("Test Case 3 Identical: " + isSameTree(root3, deserialized3)); // Expected: true
    }
}
