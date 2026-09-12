// Majority Element
// Find all elements that appear more than n/3 times.

// Example:
// arr = {1,3,2,5,1,3,1,5,1}
// n = 9
// n/3 = 3
// 1 appears 4 times, so 1 is the answer.

// Time Complexity: O(n)
// Space Complexity: O(n)
package day3;
import java.util.*; // Import HashMap and Set.

public class majority { // Define the majority class.

    public static void main(String args[]) { // Main method.

        // Create an integer array.
        int arr[] = {1, 3, 2, 5, 1, 3, 1, 5, 1};

        // Create a HashMap.
        // Key   -> Array element
        // Value -> Frequency of that element
        HashMap<Integer, Integer> map = new HashMap<>();

        // Traverse the complete array.
        for (int i = 0; i < arr.length; i++) {

            // Check whether the current element already exists.
            if (map.containsKey(arr[i])) {

                // If it exists, increase its frequency by 1.
                map.put(arr[i], map.get(arr[i]) + 1);

            } else {

                // If it does not exist, add it with frequency 1.
                map.put(arr[i], 1);
            }
        }

        // Get all keys from the HashMap.
        Set<Integer> keySet = map.keySet();

        // Traverse every unique element.
        for (Integer key : keySet) {

            // Check whether its frequency is greater than n/3.
            if (map.get(key) > arr.length / 3) {

                // Print the element satisfying the condition.
                System.out.println(key);
            }
        }
    }
}