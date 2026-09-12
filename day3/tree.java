// TreeMap stores key-value pairs in sorted order.
// Keys are automatically sorted according to their natural ordering.

// put(), get(), and remove() operations take O(log n) time.

// Syntax:
// TreeMap<K, V> map = new TreeMap<>();

// TreeMap is implemented using a Red-Black Tree.
// A Red-Black Tree is a self-balancing Binary Search Tree (BST).

package day3; // Defines the package name.

import java.util.*; // Imports TreeMap and other utility classes.

public class tree { // Defines the tree class.

    public static void main(String[] args) { // Main method; program execution starts here.

        // Creates a TreeMap with String keys and Integer values.
        TreeMap<String, Integer> tm = new TreeMap<>();

        // Adds "India" with value 1670.
        tm.put("India", 1670);

        // Adds "vff" with value 544.
        tm.put("vff", 544);

        // Adds "gbcg" with value 6567.
        tm.put("gbcg", 6567);

        // Adds "bgnh" with value 65.
        tm.put("bgnh", 65);

        // Adds "bdrg" with value 1.
        tm.put("bdrg", 1);

        // Prints the TreeMap.
        // Keys will be printed in sorted alphabetical order.
        System.out.println(tm);

    } // End of main method.

} // End of tree class.