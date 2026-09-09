import java.util.*;

public class hashing {
    public static void main(String args[]) {

        // Create a HashMap
        // Key   -> String
        // Value -> Integer
        HashMap<String, Integer> map = new HashMap<>();

        // insert - Add key-value pairs into the HashMap
        map.put("india", 100);
        map.put("bhutan", 10);
        map.put("usa", 105);
        map.put("nepal", 14);
        map.put("russia", 199);
        map.put("pak", 104);
        map.put("america", 80);

        // Print the complete HashMap
        System.out.println(map);

        // get - O(1)
        // get() is used to retrieve the value associated with a key
        int population = map.get("india");
        System.out.println("population of india : " + population);

        // If the key does not exist, get() returns null
        // int ppopulation = map.get("indonesia");
        // System.out.println("population of indonesia : " + ppopulation);


        // containsKey - O(1)
        // Checks whether the given key exists in the HashMap
        // Returns true if the key exists, otherwise false
        System.out.println(map.containsKey("india"));
        System.out.println(map.containsKey("indonesia"));


        // remove - O(1)
        // Removes the key-value pair associated with the given key
        // Returns the value that was removed
        System.out.println(map.remove("pak"));

        // Print the HashMap after removing "pak"
        System.out.println(map);


        // size
        // Returns the total number of key-value pairs in the HashMap
        System.out.println(map.size());


        // isEmpty
        // Returns true if the HashMap contains no elements
        // Returns false if the HashMap contains one or more elements
        System.out.println(map.isEmpty());
    }
}