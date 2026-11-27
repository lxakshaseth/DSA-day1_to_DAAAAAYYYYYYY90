package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 30: Substring with Concatenation of All Words
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * You are given a string s and an array of strings words of the same length.
 * Return an array of the starting indices of all the concatenated substrings in s.
 */
public class p30 {

    /**
     * Multi-Offset Sliding Window with Frequency Maps
     *
     * By partitioning starting indices modulo wordLen into wordLen independent chains,
     * each chunk is examined at most twice (by left and right window pointers).
     *
     * Time Complexity: O(n) where n = s.length()
     * Space Complexity: O(m * k) where m = words.length, k = words[0].length()
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0 || s.isEmpty()) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) {
            return result;
        }

        Map<String, Integer> targetFreq = new HashMap<>();
        for (String w : words) {
            targetFreq.put(w, targetFreq.getOrDefault(w, 0) + 1);
        }

        // Loop over all possible word-alignment offsets [0, wordLen - 1]
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            int matchedWords = 0;
            Map<String, Integer> windowFreq = new HashMap<>();

            while (right + wordLen <= s.length()) {
                String sub = s.substring(right, right + wordLen);
                right += wordLen;

                if (targetFreq.containsKey(sub)) {
                    windowFreq.put(sub, windowFreq.getOrDefault(sub, 0) + 1);
                    matchedWords++;

                    // If we have seen sub more times than present in words, shrink from left
                    while (windowFreq.get(sub) > targetFreq.get(sub)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowFreq.put(leftWord, windowFreq.get(leftWord) - 1);
                        matchedWords--;
                        left += wordLen;
                    }

                    // Check if current window matches total wordCount exactly
                    if (matchedWords == wordCount) {
                        result.add(left);
                        // Advance left boundary by one word to search for next match
                        String leftWord = s.substring(left, left + wordLen);
                        windowFreq.put(leftWord, windowFreq.get(leftWord) - 1);
                        matchedWords--;
                        left += wordLen;
                    }
                } else {
                    // Invalid word encountered; reset window state
                    windowFreq.clear();
                    matchedWords = 0;
                    left = right;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 30: Substring with Concatenation of All Words...");
        p30 solver = new p30();

        // Test 1: "barfoothefoobarman", words = ["foo","bar"] -> [0, 9]
        String s1 = "barfoothefoobarman";
        String[] words1 = {"foo", "bar"};
        List<Integer> res1 = solver.findSubstring(s1, words1);
        assert res1.containsAll(List.of(0, 9)) && res1.size() == 2 : "Expected [0, 9], got " + res1;
        System.out.println("Test 1: " + res1 + " (Passed)");

        // Test 2: "wordgoodgoodgoodbestword", words = ["word","good","best","word"] -> []
        String s2 = "wordgoodgoodgoodbestword";
        String[] words2 = {"word", "good", "best", "word"};
        List<Integer> res2 = solver.findSubstring(s2, words2);
        assert res2.isEmpty() : "Expected [], got " + res2;
        System.out.println("Test 2: " + res2 + " (Passed)");

        // Test 3: "barfoofoobarthefoobarman", words = ["bar","foo","the"] -> [6, 9, 12]
        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar", "foo", "the"};
        List<Integer> res3 = solver.findSubstring(s3, words3);
        assert res3.containsAll(List.of(6, 9, 12)) && res3.size() == 3 : "Expected [6, 9, 12], got " + res3;
        System.out.println("Test 3: " + res3 + " (Passed)");

        // Test 4: Duplicate words "wordgoodgoodgoodbestword", words = ["word","good","best","good"] -> [8]
        String s4 = "wordgoodgoodgoodbestword";
        String[] words4 = {"word", "good", "best", "good"};
        List<Integer> res4 = solver.findSubstring(s4, words4);
        assert res4.equals(List.of(8)) : "Expected [8], got " + res4;
        System.out.println("Test 4: " + res4 + " (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
