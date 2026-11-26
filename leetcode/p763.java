package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 763: Partition Labels
 * https://leetcode.com/problems/partition-labels/
 *
 * You are given a string s. We want to partition the string into as many parts as possible
 * so that each letter appears in at most one part.
 * Return a list of integers representing the size of these parts.
 */
public class p763 {

    /**
     * Greedy Two-Pointer Partitioning using Last Occurrence Indices.
     *
     * Time Complexity: O(n) - Two linear scans of string s.
     * Space Complexity: O(1) - Fixed 26-element array for lowercase English letters.
     */
    public List<Integer> partitionLabels(String s) {
        int[] lastOccurrence = new int[26];
        int n = s.length();

        // Step 1: Record the last occurrence index of each character
        for (int i = 0; i < n; i++) {
            lastOccurrence[s.charAt(i) - 'a'] = i;
        }

        List<Integer> partitions = new ArrayList<>();
        int start = 0;
        int end = 0;

        // Step 2: Greedily expand the current partition end to encompass
        // the last occurrence of any character within the partition
        for (int i = 0; i < n; i++) {
            end = Math.max(end, lastOccurrence[s.charAt(i) - 'a']);

            // When the current index reaches the maximum required boundary,
            // a complete, independent partition is formed.
            if (i == end) {
                partitions.add(end - start + 1);
                start = i + 1;
            }
        }

        return partitions;
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 763: Partition Labels...");
        p763 solver = new p763();

        // Test 1: "ababcbacadefegdehijhklij" -> [9, 7, 8]
        // "ababcbaca", "defegde", "hijhklij"
        String s1 = "ababcbacadefegdehijhklij";
        List<Integer> res1 = solver.partitionLabels(s1);
        assert res1.equals(List.of(9, 7, 8)) : "Expected [9, 7, 8], got " + res1;
        System.out.println("Test 1: " + s1 + " -> " + res1 + " (Passed)");

        // Test 2: "eccbbbbdec" -> [10] (all characters bound together)
        String s2 = "eccbbbbdec";
        List<Integer> res2 = solver.partitionLabels(s2);
        assert res2.equals(List.of(10)) : "Expected [10], got " + res2;
        System.out.println("Test 2: " + s2 + " -> " + res2 + " (Passed)");

        // Test 3: All distinct characters "abcdef" -> [1, 1, 1, 1, 1, 1]
        String s3 = "abcdef";
        List<Integer> res3 = solver.partitionLabels(s3);
        assert res3.equals(List.of(1, 1, 1, 1, 1, 1)) : "Expected [1, 1, 1, 1, 1, 1], got " + res3;
        System.out.println("Test 3: " + s3 + " -> " + res3 + " (Passed)");

        // Test 4: Single character "a" -> [1]
        String s4 = "a";
        List<Integer> res4 = solver.partitionLabels(s4);
        assert res4.equals(List.of(1)) : "Expected [1], got " + res4;
        System.out.println("Test 4: " + s4 + " -> " + res4 + " (Passed)");

        // Test 5: Repeating identical characters "aaaaa" -> [5]
        String s5 = "aaaaa";
        List<Integer> res5 = solver.partitionLabels(s5);
        assert res5.equals(List.of(5)) : "Expected [5], got " + res5;
        System.out.println("Test 5: " + s5 + " -> " + res5 + " (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
