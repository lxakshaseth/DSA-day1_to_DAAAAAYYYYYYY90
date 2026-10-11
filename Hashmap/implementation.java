//implementation on hashmap
//put(), get(), containsKey(), remove(), size() - O(1)
package Hashmap;

import java.util.ArrayList;
import java.util.LinkedList;

public class implementation {

    static class HashMap<K, V> {

        // Node of LinkedList
        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        // Number of key-value pairs (nodes)
        private int n;

        // Number of buckets
        private int N;

        // Array of LinkedLists
        private LinkedList<Node>[] buckets;

        // Constructor
        @SuppressWarnings("unchecked")
        public HashMap() {
            this.N = 4;
            this.buckets = new LinkedList[N];

            for (int i = 0; i < N; i++) {
                this.buckets[i] = new LinkedList<>();
            }
        }

        // Hash Function
        private int hashFunction(K key) {
            int hc = key.hashCode();
            return Math.abs(hc) % N;
        }

        // Search key inside LinkedList
        private int searchInLL(K key, int bi) {
            LinkedList<Node> ll = buckets[bi];

            for (int i = 0; i < ll.size(); i++) {
                Node node = ll.get(i);
                if (node.key.equals(key)) {
                    return i;
                }
            }
            return -1;
        }

        // Rehashing
        @SuppressWarnings("unchecked")
        private void rehash() {
            LinkedList<Node>[] oldBuckets = buckets;
            N = N * 2;
            buckets = new LinkedList[N];

            // Create new empty buckets
            for (int i = 0; i < N; i++) {
                buckets[i] = new LinkedList<>();
            }

            // Re-insert old elements into new buckets
            for (int i = 0; i < oldBuckets.length; i++) {
                LinkedList<Node> ll = oldBuckets[i];
                for (int j = 0; j < ll.size(); j++) {
                    Node node = ll.get(j);
                    put(node.key, node.value);
                }
            }
        }

        // Put - O(1)
        public void put(K key, V value) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di != -1) {
                // Key already exists, update value
                Node node = buckets[bi].get(di);
                node.value = value;
            } else {
                // New key-value pair
                buckets[bi].add(new Node(key, value));
                n++;
            }

            // Check load factor (lambda = n / N)
            double lambda = (double) n / N;
            if (lambda > 2.0) {
                rehash();
            }
        }

        // Get - O(1)
        public V get(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di != -1) {
                Node node = buckets[bi].get(di);
                return node.value;
            } else {
                return null;
            }
        }

        // ContainsKey - O(1)
        public boolean containsKey(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            return di != -1;
        }

        // Remove - O(1)
        public V remove(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);

            if (di != -1) {
                Node node = buckets[bi].remove(di);
                n--;
                return node.value;
            } else {
                return null;
            }
        }

        // KeySet
        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();

            for (int i = 0; i < buckets.length; i++) {
                LinkedList<Node> ll = buckets[i];
                for (Node node : ll) {
                    keys.add(node.key);
                }
            }
            return keys;
        }

        // Size - O(1)
        public int size() {
            return n;
        }

        // IsEmpty - O(1)
        public boolean isEmpty() {
            return n == 0;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();

        hm.put("India", 100);
        hm.put("China", 150);
        hm.put("US", 50);
        hm.put("Nepal", 5);

        System.out.println("Contains India: " + hm.containsKey("India"));
        System.out.println("Contains UK: " + hm.containsKey("UK"));
        System.out.println("Get India: " + hm.get("India"));
        System.out.println("Size: " + hm.size());

        System.out.println("Removed India: " + hm.remove("India"));
        System.out.println("Get India after remove: " + hm.get("India"));
        System.out.println("Size after remove: " + hm.size());

        System.out.println("\nAll keys:");
        ArrayList<String> keys = hm.keySet();
        for (String key : keys) {
            System.out.println(key + " -> " + hm.get(key));
        }

        System.out.println("Is empty: " + hm.isEmpty());
    }
}
