# DSA 90 Days Challenge

A structured and comprehensive 90-day Data Structures and Algorithms repository in **Java**, tracking daily practice, core CS concepts, custom data structure implementations, and optimal solutions to classic **LeetCode** / **Blind 75** / **NeetCode 150** problems.

---

## Repository Structure

```text
├── day1/               # Introduction to Hashing, HashMap operations & iteration
├── day2/               # Custom HashMap implementation using bucket arrays & rehashing
├── day3/               # LinkedHashMap, TreeMap, Majority Element, Valid Anagram
├── day4/               # HashSet, Union & Intersection, Distinct Counts, Subarrays & Itineraries
├── Hashmap/            # Dedicated custom HashMap implementations & revisions
├── String/             # Core String manipulation fundamentals & methods
└── leetcode/           # Optimal solutions to Blind 75 / NeetCode 150 problems
```

---

## Solved LeetCode Problems

| Problem # | Title | Category | Optimal Approach | Time Complexity | Space Complexity |
|:---:|:---|:---|:---|:---:|:---:|
| 1 | Two Sum | Arrays & Hashing | HashMap complement lookup | O(n) | O(n) |
| 3 | Longest Substring Without Repeating Characters | Sliding Window | Sliding Window + HashSet | O(n) | O(min(m, n)) |
| 5 | Longest Palindromic Substring | Two Pointers | Expand Around Center | O(n^2) | O(1) |
| 11 | Container With Most Water | Two Pointers | Two-Pointer Greedy | O(n) | O(1) |
| 12 | Integer to Roman | Math & Geometry | Greedy Value-to-Symbol Mapping | O(1) | O(1) |
| 15 | 3Sum | Two Pointers | Sorting + Two Pointers | O(n^2) | O(1) |
| 19 | Remove Nth Node From End of List | Linked List | Two Pointers (Fast & Slow) + Dummy Head | O(L) | O(1) |
| 20 | Valid Parentheses | Stack | Stack matching | O(n) | O(n) |
| 21 | Merge Two Sorted Lists | Linked List | Dummy Node + Two Pointers | O(n + m) | O(1) |
| 33 | Search in Rotated Sorted Array | Binary Search | Modified Binary Search | O(log n) | O(1) |
| 39 | Combination Sum | Backtracking | Backtracking with element reuse | O(2^(target/min)) | O(target/min) |
| 46 | Permutations | Backtracking | Backtracking with boolean used array | O(n * n!) | O(n) |
| 48 | Rotate Image | Math & Geometry | Matrix Transpose + Row Reverse | O(n^2) | O(1) |
| 49 | Group Anagrams | Arrays & Hashing | Categorization by sorted character key | O(n * k log k) | O(n * k) |
| 50 | Pow(x, n) | Math & Geometry | Binary Exponentiation (Fast Power) | O(log n) | O(1) |
| 53 | Maximum Subarray | Greedy | Kadane's Algorithm | O(n) | O(1) |
| 54 | Spiral Matrix | Math & Geometry | Four-Boundary Simulation | O(m * n) | O(1) |
| 55 | Jump Game | Greedy | Greedy Backward Goal Shift | O(n) | O(1) |
| 56 | Merge Intervals | Intervals | Sorting + Single-Pass Merge | O(n log n) | O(n) |
| 57 | Insert Interval | Intervals | Three-Phase Linear Merge | O(n) | O(n) |
| 62 | Unique Paths | 2-D DP | Space-Optimized 1-D DP Array | O(m * n) | O(n) |
| 66 | Plus One | Math & Geometry | Right-to-Left Carry Propagation | O(n) | O(1) |
| 70 | Climbing Stairs | 1-D DP | Fibonacci Rolling Variables | O(n) | O(1) |
| 73 | Set Matrix Zeroes | Math & Geometry | In-Place Row & Column Markers | O(m * n) | O(1) |
| 74 | Search a 2D Matrix | Binary Search | Virtual 1-D Binary Search | O(log(m * n)) | O(1) |
| 78 | Subsets | Backtracking | Power Set Backtracking | O(n * 2^n) | O(n) |
| 98 | Validate Binary Search Tree | Trees | Recursive Range Validation (min, max) | O(n) | O(h) |
| 100 | Same Tree | Trees | Recursive DFS Structural Equality | O(n) | O(h) |
| 102 | Binary Tree Level Order Traversal | Trees | BFS Queue Level-by-Level | O(n) | O(n) |
| 104 | Maximum Depth of Binary Tree | Trees | Recursive Depth-First Search | O(n) | O(h) |
| 121 | Best Time to Buy and Sell Stock | Sliding Window | One-Pass Minimum Price Tracking | O(n) | O(1) |
| 125 | Valid Palindrome | Two Pointers | Two Pointers with Alphanumeric Filtering | O(n) | O(1) |
| 128 | Longest Consecutive Sequence | Arrays & Hashing | HashSet Sequence Start Check | O(n) | O(n) |
| 133 | Clone Graph | Graphs | Recursive DFS with Visited HashMap | O(V + E) | O(V) |
| 134 | Gas Station | Greedy | Single-Pass Greedy Tank Reset | O(n) | O(1) |
| 136 | Single Number | Bit Manipulation | XOR Cancellation Identity | O(n) | O(1) |
| 139 | Word Break | 1-D DP | Bottom-Up DP with HashSet Lookup | O(n^2 * k) | O(n) |
| 141 | Linked List Cycle | Linked List | Floyd's Tortoise and Hare | O(n) | O(1) |
| 146 | LRU Cache | Linked List | HashMap + Doubly Linked List | O(1) all ops | O(capacity) |
| 150 | Evaluate Reverse Polish Notation | Stack | Stack-based Operand Evaluation | O(n) | O(n) |
| 155 | Min Stack | Stack | Two-Stack Minimum Tracking | O(1) all ops | O(n) |
| 167 | Two Sum II - Input Array Is Sorted | Two Pointers | Two Pointers (Left / Right) | O(n) | O(1) |
| 190 | Reverse Bits | Bit Manipulation | Bitwise Shift and Masking | O(1) | O(1) |
| 191 | Number of 1 Bits | Bit Manipulation | Brian Kernighan's n & (n - 1) | O(k) | O(1) |
| 198 | House Robber | 1-D DP | Space-Optimized Rolling DP | O(n) | O(1) |
| 200 | Number of Islands | Graphs | DFS Island Sinking | O(m * n) | O(m * n) |
| 202 | Happy Number | Math & Geometry | Floyd's Two-Pointer Cycle Detection | O(log n) | O(1) |
| 206 | Reverse Linked List | Linked List | Iterative Three Pointers (prev, curr, next) | O(n) | O(1) |
| 207 | Course Schedule | Graphs | Kahn's Algorithm (Topological Sort BFS) | O(V + E) | O(V + E) |
| 208 | Implement Trie (Prefix Tree) | Tries | 26-Way Array TrieNode Tree | O(L) per op | O(N * L) |
| 215 | Kth Largest Element in an Array | Heap | Min-Heap (PriorityQueue) of size k | O(n log k) | O(k) |
| 217 | Contains Duplicate | Arrays & Hashing | Array Sorting / HashSet | O(n log n) | O(1) |
| 226 | Invert Binary Tree | Trees | Recursive DFS Subtree Swap | O(n) | O(h) |
| 230 | Kth Smallest Element in a BST | Trees | Iterative In-Order Traversal with Stack | O(H + k) | O(H) |
| 235 | Lowest Common Ancestor of a BST | Trees | Iterative BST Split Point Search | O(h) | O(1) |
| 238 | Product of Array Except Self | Arrays & Hashing | Prefix and Suffix Running Products | O(n) | O(1) |
| 242 | Valid Anagram | Arrays & Hashing | 26-Letter Frequency Array | O(n) | O(1) |
| 268 | Missing Number | Bit Manipulation | XOR Index-Value Cancellation | O(n) | O(1) |
| 300 | Longest Increasing Subsequence | 1-D DP | Dynamic Programming (n^2) | O(n^2) | O(n) |
| 322 | Coin Change | 1-D DP | Bottom-Up DP Table | O(amount * n) | O(amount) |
| 338 | Counting Bits | Bit Manipulation | Bit-Shift DP ans[i >> 1] + (i & 1) | O(n) | O(1) |
| 347 | Top K Frequent Elements | Arrays & Hashing | Frequency Map + Bucket Sort | O(n) | O(n) |
| 435 | Non-overlapping Intervals | Intervals | Greedy End-Time Interval Scheduling | O(n log n) | O(1) |
| 572 | Subtree of Another Tree | Trees | Recursive DFS + Same Tree Helper | O(n * m) | O(h) |
| 704 | Binary Search | Binary Search | Classical Two-Pointer Binary Search | O(log n) | O(1) |
| 739 | Daily Temperatures | Stack | Monotonic Decreasing Stack | O(n) | O(n) |
| 743 | Network Delay Time | Advanced Graphs | Dijkstra's Algorithm with Min-Heap | O((V + E) log V) | O(V + E) |

---

## Languages & Tools

- **Language**: Java 21 (JDK 21)
- **Version Control**: Git & GitHub
- **Editor**: VS Code / Antigravity IDE
- **Core Topics Covered**:
  - Hashing & Custom HashMaps
  - Arrays & Two Pointers
  - Sliding Window
  - Stacks & Monotonic Stacks
  - Singly Linked Lists
  - Binary Trees & Binary Search Trees
  - Prefix Trees (Tries)
  - Heaps & Priority Queues
  - Backtracking & Recursion
  - 1-D & 2-D Dynamic Programming
  - Graphs (BFS, DFS, Topological Sort, Dijkstra)
  - Intervals & Greedy Algorithms
  - Bit Manipulation & Math

---

## Author

**Akshat Gupta**
- GitHub: [@lxakshaseth](https://github.com/lxakshaseth)