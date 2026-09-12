// LinkedHashMap maintains the keys in insertion order.

package day3; // Defines the package in which this class belongs.

import java.util.*; // Imports all classes from the java.util package.

public class LinkedHash { // Defines the LinkedHash class.

    public static void main(String args[]) { // Main method: program execution starts here.

        // Creating a LinkedHashMap with String keys and Integer values.
        // LinkedHashMap maintains insertion order.
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

        // Adding "india" as key and 160 as its value.
        map.put("india", 160);

        // Adding "indo" as key and 60 as its value.
        map.put("indo", 60);

        // Adding "nepal" as key and 5 as its value.
        map.put("nepal", 5);

        // Printing the LinkedHashMap.
        // Output will follow insertion order.
        System.out.println(map);


        // ---------------- HASHMAP ----------------

        // Creating a HashMap with String keys and Integer values.
        // HashMap does NOT guarantee insertion order.
        HashMap<String, Integer> mapp = new HashMap<>();

        // Adding "india" as key and 160 as its value.
        mapp.put("india", 160);

        // Adding "indo" as key and 60 as its value.
        mapp.put("indo", 60);

        // Adding "nepal" as key and 5 as its value.
        mapp.put("nepal", 5);

        // Printing the HashMap.
        // NOTE: We should print 'mapp', not 'map'.
        System.out.println(mapp);

    } // End of main method.

} // End of LinkedHash class.