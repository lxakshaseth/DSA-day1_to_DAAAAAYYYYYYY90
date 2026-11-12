// Defines that this class belongs to the leetcode package
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * LeetCode 981: Time Based Key-Value Store
 *
 * Design a time-based key-value data structure that can store multiple values for the
 * same key at different time stamps and retrieve the key's value at a certain timestamp.
 *
 * Implement the TimeMap class:
 * - set(String key, String value, int timestamp): Stores the key with the value at timestamp.
 * - get(String key, int timestamp): Returns the value with the largest timestamp_prev <= timestamp,
 *   or "" if no such value exists.
 *
 * Optimal Approach: HashMap + ArrayList + Binary Search
 * - Store a mapping from each key to a chronologically sorted list of (timestamp, value) pairs.
 * - Since set() timestamps are strictly increasing, appending takes O(1) amortized time
 *   and naturally preserves sorted order.
 * - For get(), perform binary search on the key's list to find the rightmost entry
 *   whose timestamp is less than or equal to the target timestamp.
 *
 * Time Complexity:
 * - set(): O(1) amortized
 * - get(): O(log n) where n is the number of entries recorded for the requested key
 * Space Complexity: O(N) total across all stored (key, timestamp, value) entries
 */
public class p981 {

    public static class TimeMap {

        // Immutable container for a timestamped value entry
        private static class TimeValue {
            final int timestamp;
            final String value;

            TimeValue(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }
        }

        private final Map<String, List<TimeValue>> store;

        public TimeMap() {
            this.store = new HashMap<>();
        }

        // Append a new (timestamp, value) entry for the specified key
        public void set(String key, String value, int timestamp) {
            store.computeIfAbsent(key, k -> new ArrayList<>())
                 .add(new TimeValue(timestamp, value));
        }

        // Binary search for the largest timestamp_prev <= timestamp
        public String get(String key, int timestamp) {
            List<TimeValue> entries = store.get(key);
            if (entries == null || entries.isEmpty() || timestamp < entries.get(0).timestamp) {
                return "";
            }

            int low = 0;
            int high = entries.size() - 1;
            String result = "";

            while (low <= high) {
                int mid = low + (high - low) / 2;
                TimeValue candidate = entries.get(mid);

                if (candidate.timestamp == timestamp) {
                    return candidate.value;
                } else if (candidate.timestamp < timestamp) {
                    result = candidate.value;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return result;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: LeetCode standard example
        TimeMap timeMap1 = new TimeMap();
        timeMap1.set("foo", "bar", 1);
        System.out.println("get(foo, 1): \"" + timeMap1.get("foo", 1) + "\"");       // Expected: "bar"
        System.out.println("get(foo, 3): \"" + timeMap1.get("foo", 3) + "\"");       // Expected: "bar"
        timeMap1.set("foo", "bar2", 4);
        System.out.println("get(foo, 4): \"" + timeMap1.get("foo", 4) + "\"");       // Expected: "bar2"
        System.out.println("get(foo, 5): \"" + timeMap1.get("foo", 5) + "\"");       // Expected: "bar2"
        System.out.println("get(foo, 0): \"" + timeMap1.get("foo", 0) + "\"");       // Expected: ""
        System.out.println("get(unknown, 5): \"" + timeMap1.get("unknown", 5) + "\""); // Expected: ""

        // Test Case 2: Multiple timestamps and queries around boundaries
        TimeMap timeMap2 = new TimeMap();
        timeMap2.set("love", "high", 10);
        timeMap2.set("love", "low", 20);
        System.out.println("get(love, 5): \"" + timeMap2.get("love", 5) + "\"");     // Expected: ""
        System.out.println("get(love, 10): \"" + timeMap2.get("love", 10) + "\"");   // Expected: "high"
        System.out.println("get(love, 15): \"" + timeMap2.get("love", 15) + "\"");   // Expected: "high"
        System.out.println("get(love, 20): \"" + timeMap2.get("love", 20) + "\"");   // Expected: "low"
        System.out.println("get(love, 25): \"" + timeMap2.get("love", 25) + "\"");   // Expected: "low"
    }
}
