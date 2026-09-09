// Iteration on HashMap
// Set<String> keys = map.keySet();

import java.util.*; // Import all classes from the java.util package

public class iteration { // Define the iteration class

    public static void main(String args[]) { // Main method - program execution starts here

        // Create a HashMap with String as Key and Integer as Value
        HashMap<String, Integer> map = new HashMap<>();

        // Insert key-value pairs into the HashMap
        map.put("india", 100);   // Add India with population 100
        map.put("bhutan", 10);   // Add Bhutan with population 10
        map.put("usa", 105);     // Add USA with population 105
        map.put("nepal", 14);    // Add Nepal with population 14
        map.put("russia", 199);  // Add Russia with population 199
        map.put("pak", 104);     // Add Pakistan with population 104
        map.put("america", 80);  // Add America with population 80

        // Get all the keys from the HashMap
        // keySet() returns all keys as a Set
        Set<String> keys = map.keySet();

        // Print all the keys
        System.out.println(keys);

        // Iterate through each key in the Set
        for (String k : keys) {

            // Get the value associated with the current key using map.get(k)
            // Print both key and its corresponding value
            System.out.println("key =" + k + ",value=" + map.get(k));
        }

        // Get all values from the HashMap
        // values() returns the values as a Collection
        Collection<Integer> values = map.values();

        // Print all the values
        System.out.println(values);
    }
}