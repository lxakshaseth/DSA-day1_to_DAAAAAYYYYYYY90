// Package name for this class
package day2;

// Import ArrayList for storing keys
import java.util.ArrayList;

// Import LinkedList for implementing buckets
import java.util.LinkedList;

// Main class
public class implementation {

    // Custom generic HashMap class with Key K and Value V
    static class HashMap<K, V> {

        // Node class represents one key-value pair
        private class Node {

            // Stores the key
            K key;

            // Stores the value
            V value;

            // Constructor to initialize key and value
            public Node(K key, V value) {

                // Assign given key to current node
                this.key = key;

                // Assign given value to current node
                this.value = value;
            }
        }

        // Number of key-value pairs currently stored
        private int n;

        // Number of buckets in the HashMap
        private int N;

        // Array of LinkedLists used as buckets
        private LinkedList<Node>[] buckets;

        // Constructor of custom HashMap
        @SuppressWarnings("unchecked")
        public HashMap() {

            // Initially create 4 buckets
            this.N = 4;

            // Create an array of LinkedLists
            this.buckets = new LinkedList[N];

            // Initialize every bucket
            for (int i = 0; i < N; i++) {

                // Create an empty LinkedList for each bucket
                this.buckets[i] = new LinkedList<>();
            }
        }

        // Function to calculate bucket index for a key
        private int hashFunction(K key) {

            // Get hash code of the key
            int hc = key.hashCode();

            // Convert hash code into a valid bucket index
            return Math.abs(hc) % N;
        }

        // Search for a key inside a particular bucket
        private int searchInLL(K key, int bi) {

            // Get the LinkedList at bucket index bi
            LinkedList<Node> ll = buckets[bi];

            // Traverse all nodes of the LinkedList
            for (int i = 0; i < ll.size(); i++) {

                // Get the current node
                Node node = ll.get(i);

                // Compare current node's key with given key
                if (node.key.equals(key)) {

                    // Return index if key is found
                    return i;
                }
            }

            // Return -1 if key is not found
            return -1;
        }

        // Function to increase bucket size and redistribute elements
        @SuppressWarnings("unchecked")
        private void rehash() {

            // Store reference to old buckets
            LinkedList<Node>[] oldBuckets = buckets;

            // Double the number of buckets
            N = N * 2;

            // Create a new array with increased bucket size
            buckets = new LinkedList[N];

            // Initialize all new buckets
            for (int i = 0; i < N; i++) {

                // Create an empty LinkedList for each new bucket
                buckets[i] = new LinkedList<>();
            }

            // Traverse all old buckets
            for (int i = 0; i < oldBuckets.length; i++) {

                // Get current old bucket
                LinkedList<Node> ll = oldBuckets[i];

                // Traverse all nodes in current bucket
                for (int j = 0; j < ll.size(); j++) {

                    // Get current node
                    Node node = ll.get(j);

                    // Insert node again using the new hash function
                    put(node.key, node.value);
                }
            }
        }

        // Function to insert or update a key-value pair
        public void put(K key, V value) {

            // Calculate bucket index
            int bi = hashFunction(key);

            // Search for key inside the bucket
            int di = searchInLL(key, bi);

            // Check whether key already exists
            if (di != -1) {

                // Get existing node
                Node node = buckets[bi].get(di);

                // Update its value
                node.value = value;

            } else {

                // Add new key-value pair to the bucket
                buckets[bi].add(new Node(key, value));

                // Increase total number of elements
                n++;
            }

            // Calculate load factor
            double lambda = (double) n / N;

            // Rehash when load factor becomes greater than 2
            if (lambda > 2.0) {

                // Increase bucket count and redistribute elements
                rehash();
            }
        }

        // Function to retrieve value associated with a key
        public V get(K key) {

            // Calculate bucket index
            int bi = hashFunction(key);

            // Search for key inside bucket
            int di = searchInLL(key, bi);

            // Check whether key exists
            if (di != -1) {

                // Get the node containing the key
                Node node = buckets[bi].get(di);

                // Return the value
                return node.value;

            } else {

                // Return null when key does not exist
                return null;
            }
        }

        // Function to check whether a key exists
        public boolean containsKey(K key) {

            // Calculate bucket index
            int bi = hashFunction(key);

            // Search for key inside bucket
            int di = searchInLL(key, bi);

            // Return true if key exists, otherwise false
            return di != -1;
        }

        // Function to remove a key-value pair
        public V remove(K key) {

            // Calculate bucket index
            int bi = hashFunction(key);

            // Search for key inside bucket
            int di = searchInLL(key, bi);

            // Check whether key exists
            if (di != -1) {

                // Remove node from the bucket
                Node node = buckets[bi].remove(di);

                // Decrease total number of elements
                n--;

                // Return removed value
                return node.value;

            } else {

                // Return null if key does not exist
                return null;
            }
        }

        // Function to return all keys
        public ArrayList<K> keySet() {

            // Create ArrayList to store keys
            ArrayList<K> keys = new ArrayList<>();

            // Traverse all buckets
            for (int i = 0; i < buckets.length; i++) {

                // Get current bucket
                LinkedList<Node> ll = buckets[i];

                // Traverse every node in current bucket
                for (Node node : ll) {

                    // Add node's key to ArrayList
                    keys.add(node.key);
                }
            }

            // Return all keys
            return keys;
        }

        // Function to return number of key-value pairs
        public int size() {

            // Return total number of elements
            return n;
        }

        // Function to check whether HashMap is empty
        public boolean isEmpty() {

            // Return true when number of elements is zero
            return n == 0;
        }
    }

    // Main method where program execution starts
    public static void main(String[] args) {

        // Create custom HashMap with String keys and Integer values
        HashMap<String, Integer> hm = new HashMap<>();

        // Add India with value 100
        hm.put("india", 100);

        // Add China with value 150
        hm.put("china", 150);

        // Add Nepal with value 6
        hm.put("nepal", 6);

        // Add Indo with value 20
        hm.put("indo", 20);

        // Get and print value associated with India
        System.out.println("India: " + hm.get("india"));

        // Check whether India exists
        System.out.println("Contains India: "
                + hm.containsKey("india"));

        // Check whether USA exists
        System.out.println("Contains USA: "
                + hm.containsKey("usa"));

        // Print total number of key-value pairs
        System.out.println("Size: " + hm.size());

        // Remove China and print its value
        System.out.println("Removed: "
                + hm.remove("china"));

        // Print size after removing China
        System.out.println("Size after remove: "
                + hm.size());

        // Print heading for keys
        System.out.println("Keys:");

        // Get all keys from HashMap
        ArrayList<String> keys = hm.keySet();

        // Traverse all keys
        for (String key : keys) {

            // Print current key
            System.out.println(key);
        }

        // Check whether HashMap is empty
        System.out.println("Is Empty: "
                + hm.isEmpty());
    }
}