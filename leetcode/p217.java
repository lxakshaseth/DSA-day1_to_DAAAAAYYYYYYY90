// Defines that this class belongs to the leetcode package
package leetcode;

// Imports the Arrays class from Java's utility package
import java.util.Arrays;

// Defines the class named p217
public class p217 {

    // Method to check whether the array contains duplicate elements
    public boolean containsDuplicate(int[] nums) {

        // Sorts the array in ascending order
        Arrays.sort(nums);

        // Starts loop from index 1 and checks every element
        for (int i = 1; i < nums.length; i++) {

            // Compares current element with the previous element
            if (nums[i] == nums[i - 1]) {

                // If both elements are same, duplicate is found
                return true;
            }
        }

        // If no duplicate is found, return false
        return false;
    }

    // Main method where program execution starts
    public static void main(String[] args) {

        // Creates an object of the p217 class
        p217 obj = new p217();

        // Creates an integer array with duplicate value 1
        int[] nums = {1, 2, 3, 1};

        // Calls containsDuplicate() and prints the result
        System.out.println(obj.containsDuplicate(nums));
    }
}