package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LeetCode 355: Design Twitter
 * https://leetcode.com/problems/design-twitter/
 *
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user,
 * and is able to see the 10 most recent tweets in the user's news feed.
 */
public class p355 {

    public static class Twitter {

        private static class Tweet {
            final int id;
            final int time;
            final Tweet next;

            Tweet(int id, int time, Tweet next) {
                this.id = id;
                this.time = time;
                this.next = next;
            }
        }

        private int globalTime;
        private final Map<Integer, Tweet> userTweets;
        private final Map<Integer, Set<Integer>> follows;

        public Twitter() {
            this.globalTime = 0;
            this.userTweets = new HashMap<>();
            this.follows = new HashMap<>();
        }

        /**
         * Composes a new tweet with ID tweetId by the user userId.
         * Prepends to the user's tweet linked list.
         * Time Complexity: O(1)
         */
        public void postTweet(int userId, int tweetId) {
            Tweet currentHead = userTweets.get(userId);
            Tweet newTweet = new Tweet(tweetId, globalTime++, currentHead);
            userTweets.put(userId, newTweet);
        }

        /**
         * Retrieves the 10 most recent tweet IDs in the user's news feed.
         * Merges the top tweets across all followed users + self using a Max-Heap.
         * Time Complexity: O(k log k) where k is the number of followees (at most 10 iterations)
         */
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> feed = new ArrayList<>();

            // Max-Heap ordered by tweet timestamp descending
            PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.time, a.time));

            // Include user's own latest tweet
            Tweet userHead = userTweets.get(userId);
            if (userHead != null) {
                maxHeap.offer(userHead);
            }

            // Include latest tweets from all followed users
            Set<Integer> followees = follows.get(userId);
            if (followees != null) {
                for (int followeeId : followees) {
                    if (followeeId == userId) continue; // Skip self if present
                    Tweet followeeHead = userTweets.get(followeeId);
                    if (followeeHead != null) {
                        maxHeap.offer(followeeHead);
                    }
                }
            }

            // Poll the most recent tweets up to 10
            while (!maxHeap.isEmpty() && feed.size() < 10) {
                Tweet top = maxHeap.poll();
                feed.add(top.id);
                if (top.next != null) {
                    maxHeap.offer(top.next);
                }
            }

            return feed;
        }

        /**
         * Follower follows a followee.
         * Time Complexity: O(1)
         */
        public void follow(int followerId, int followeeId) {
            if (followerId == followeeId) {
                return;
            }
            follows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
        }

        /**
         * Follower unfollows a followee.
         * Time Complexity: O(1)
         */
        public void unfollow(int followerId, int followeeId) {
            if (followerId == followeeId) {
                return;
            }
            Set<Integer> followees = follows.get(followerId);
            if (followees != null) {
                followees.remove(followeeId);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Testing LeetCode 355: Design Twitter...");

        Twitter twitter = new Twitter();

        // User 1 posts a new tweet (id = 5).
        twitter.postTweet(1, 5);

        // User 1's news feed should return a list with 1 tweet id -> [5].
        List<Integer> feed1 = twitter.getNewsFeed(1);
        assert feed1.equals(List.of(5)) : "Expected [5], got " + feed1;
        System.out.println("Feed 1: " + feed1 + " (Passed)");

        // User 1 follows user 2.
        twitter.follow(1, 2);

        // User 2 posts a new tweet (id = 6).
        twitter.postTweet(2, 6);

        // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
        List<Integer> feed2 = twitter.getNewsFeed(1);
        assert feed2.equals(List.of(6, 5)) : "Expected [6, 5], got " + feed2;
        System.out.println("Feed 2: " + feed2 + " (Passed)");

        // User 1 unfollows user 2.
        twitter.unfollow(1, 2);

        // User 1's news feed should return a list with 1 tweet id -> [5].
        List<Integer> feed3 = twitter.getNewsFeed(1);
        assert feed3.equals(List.of(5)) : "Expected [5], got " + feed3;
        System.out.println("Feed 3: " + feed3 + " (Passed)");

        // Test 10-tweet truncation
        for (int i = 1; i <= 15; i++) {
            twitter.postTweet(1, 100 + i);
        }
        List<Integer> feed4 = twitter.getNewsFeed(1);
        assert feed4.size() == 10 : "Expected size 10, got " + feed4.size();
        assert feed4.get(0) == 115 : "Expected latest tweet 115, got " + feed4.get(0);
        assert feed4.get(9) == 106 : "Expected 10th tweet 106, got " + feed4.get(9);
        System.out.println("Feed 4 (truncated to 10): " + feed4 + " (Passed)");

        System.out.println("All test cases passed successfully!");
    }
}
