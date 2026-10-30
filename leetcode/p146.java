// Defines that this class belongs to the leetcode package
package leetcode;

// Import utility classes
import java.util.HashMap;
import java.util.Map;

/*
 * LeetCode 146: LRU Cache
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * All operations get() and put() must run in O(1) time complexity.
 *
 * HashMap + Doubly Linked List Approach:
 * - HashMap provides O(1) key-to-node lookup.
 * - Doubly Linked List with dummy head and dummy tail allows O(1) removal and insertion:
 *   - Nodes right before tail are Most Recently Used (MRU).
 *   - Nodes right after head are Least Recently Used (LRU).
 *
 * Time Complexity: O(1) for both get() and put()
 * Space Complexity: O(capacity) for storing up to capacity key-node pairs
 */
public class p146 {

    public static class LRUCache {

        // Doubly Linked List Node
        private static class Node {
            int key;
            int val;
            Node prev;
            Node next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }

        private final int capacity;
        private final Map<Integer, Node> map;
        private final Node head;
        private final Node tail;

        // Constructor to initialize LRU cache with given capacity
        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();

            // Initialize dummy head and tail
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        // Helper method to remove a node from the doubly linked list
        private void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // Helper method to insert a node right before tail (Most Recently Used)
        private void insert(Node node) {
            node.prev = tail.prev;
            node.next = tail;
            tail.prev.next = node;
            tail.prev = node;
        }

        // Get value associated with key, and update recency
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }

            Node node = map.get(key);
            // Move node to most recently used position
            remove(node);
            insert(node);

            return node.val;
        }

        // Put key-value pair into cache
        public void put(int key, int value) {
            if (map.containsKey(key)) {
                // Remove existing node from linked list before re-inserting with updated value
                remove(map.get(key));
            }

            Node newNode = new Node(key, value);
            map.put(key, newNode);
            insert(newNode);

            // Check if capacity exceeded
            if (map.size() > capacity) {
                // Evict the least recently used node (head.next)
                Node lru = head.next;
                remove(lru);
                map.remove(lru.key);
            }
        }
    }

    // Main method to test the solution
    public static void main(String[] args) {

        LRUCache lRUCache = new LRUCache(2);

        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println("get(1): " + lRUCache.get(1));    // returns 1

        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println("get(2): " + lRUCache.get(2));    // returns -1 (not found)

        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {3=3, 4=4}
        System.out.println("get(1): " + lRUCache.get(1));    // returns -1 (not found)
        System.out.println("get(3): " + lRUCache.get(3));    // returns 3
        System.out.println("get(4): " + lRUCache.get(4));    // returns 4
    }
}
