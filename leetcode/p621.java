package leetcode;

/**
 * LeetCode 621: Task Scheduler
 * https://leetcode.com/problems/task-scheduler/
 *
 * Given a characters array tasks, representing the tasks a CPU needs to do,
 * where each letter represents a different task. Tasks could be done in any order.
 * Each task is done in one unit of time. For each unit of time, the CPU could
 * complete either one task or just be idle.
 * However, there is a non-negative integer n that represents the cooldown period
 * between two same tasks (that is, there must be at least n units of time between
 * any two identical tasks).
 * Return the least number of units of times that the CPU will take to finish all the given tasks.
 */
public class p621 {

    /**
     * Greedy / Mathematical Idle Slot Calculation
     *
     * Time Complexity: O(tasks.length) to compute frequencies, O(26) = O(1) arithmetic.
     * Space Complexity: O(1) auxiliary space (fixed 26-element array).
     */
    public int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }

        int[] freq = new int[26];
        int maxFreq = 0;
        for (char task : tasks) {
            freq[task - 'A']++;
            if (freq[task - 'A'] > maxFreq) {
                maxFreq = freq[task - 'A'];
            }
        }

        // Count how many tasks share the maximum frequency
        int maxCount = 0;
        for (int f : freq) {
            if (f == maxFreq) {
                maxCount++;
            }
        }

        // Formula:
        // (maxFreq - 1) frames of size (n + 1) slots, plus the final frame containing all maxFreq tasks
        int minIntervals = (maxFreq - 1) * (n + 1) + maxCount;

        // If tasks exceed the minimum scheduled frame (i.e. more tasks than available idle slots),
        // we can simply interleave them without needing any extra idle slots.
        return Math.max(tasks.length, minIntervals);
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 621: Task Scheduler...");
        p621 solver = new p621();

        // Test 1: ["A","A","A","B","B","B"], n = 2 -> 8 (A -> B -> idle -> A -> B -> idle -> A -> B)
        char[] tasks1 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int res1 = solver.leastInterval(tasks1, 2);
        assert res1 == 8 : "Expected 8, got " + res1;
        System.out.println("Test 1: tasks = [A,A,A,B,B,B], n = 2 -> " + res1 + " (Passed)");

        // Test 2: ["A","A","A","B","B","B"], n = 0 -> 6
        char[] tasks2 = {'A', 'A', 'A', 'B', 'B', 'B'};
        int res2 = solver.leastInterval(tasks2, 0);
        assert res2 == 6 : "Expected 6, got " + res2;
        System.out.println("Test 2: tasks = [A,A,A,B,B,B], n = 0 -> " + res2 + " (Passed)");

        // Test 3: ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2 -> 16
        char[] tasks3 = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int res3 = solver.leastInterval(tasks3, 2);
        assert res3 == 16 : "Expected 16, got " + res3;
        System.out.println("Test 3: Single dominant task -> " + res3 + " (Passed)");

        // Test 4: Diverse tasks where no idle time is needed
        // ["A","A","B","B","C","C","D","D"], n = 2 -> 8
        char[] tasks4 = {'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D'};
        int res4 = solver.leastInterval(tasks4, 2);
        assert res4 == 8 : "Expected 8, got " + res4;
        System.out.println("Test 4: Diverse tasks without idle -> " + res4 + " (Passed)");

        // Test 5: Single task
        char[] tasks5 = {'A'};
        int res5 = solver.leastInterval(tasks5, 10);
        assert res5 == 1 : "Expected 1, got " + res5;
        System.out.println("Test 5: Single task -> " + res5 + " (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
