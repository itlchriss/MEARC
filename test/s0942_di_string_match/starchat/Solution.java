package g0901_1000.s0942_di_string_match;

// #Easy #Array #String #Math #Greedy #Two_Pointers
// #2022_03_30_Time_4_ms_(33.74%)_Space_48.7_MB_(20.18%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All characters in the string parameter `s` are either 'I' or 'D'.*);
//@ requires(*If there are multiple valid permutations perm, return any of them.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1356\. Sort Integers by The Power Value*);
//@ requires(*Medium*);
//@ requires(*The power of an integer `x` is defined as the number of steps needed to transform `x` into `1` using the following steps:*);
//@ requires(*if `x` is even then `x = x / 2`*);
//@ requires(*if `x` is odd then `x = 3 * x + 1`*);
//@ requires(*For example, the power of x = 3 is 7 because 3 needs 7 steps to become 1 (3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1).*);
//@ requires(*Given three integers `lo`, `hi` and `k`. The task is to sort all integers in the interval `[lo, hi]` by the power value in ascending order, if two or more integers have the same power value sort them by ascending order.*);
//@ requires(**);
//@ requires(*Return the `k-th` integer in the range `[lo, hi]` sorted by the power value.*);
//@ requires(**);
//@ requires(*If there is less than `k` integers in the range, return `-1`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** lo = 12, hi = 15, k = 2*);
//@ requires(***Output:** 13*);
//@ requires(***Explanation:** The power of 12 is 4 (12 --> 6 --> 3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1)*);
//@ requires(*The power of 13 is 5 (13 --> 40 --> 20 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1)*);
//@ requires(*The power of 14 is 5 (14 --> 7 --> 22 --> 11 --> 34 --> 17 --> 52 --> 26 --> 13 --> 40 --> 20 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1)*);
//@ requires(*The power of 15 is 5 (15 --> 46 --> 23 --> 70 --> 35 --> 116 --> 58 --> 29 --> 88 --> 44 --> 22 --> 11 --> 34 --> 17 --> 52 --> 26 --> 13 --> 40 --> 20 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1)*);
//@ requires(*The interval sorted by the power value [12,13,14,15]. For k = 2 answer is the second element which is 13.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** lo = 1, hi = 1, k = 1*);
//@ requires(***Output:** 1*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** lo = 7, hi = 11, k = 4*);
//@ requires(***Output:** -1*);
//@ requires(**);
//@ requires(***Example 4:***);
//@ requires(**);
//@ requires(***Input:** lo = 10, hi = 20, k = 5*);
//@ requires(***Output:** 17*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= lo <= hi <= 1000`*);
//@ requires(**   `1 <= k <= hi - lo + 1`*);
//@ ensures(*The integer array result is a permutation of all the integers in the range [0, n] where n is the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "IDID", the integer array result is equal to [0,4,1,3,2].*);
//@ ensures(*If the string parameter `s` is equal to "III", the integer array result is equal to [0,1,2,3].*);
//@ ensures(*If the string parameter `s` is equal to "DDI", the integer array result is equal to [3,2,0,1].*);
    public int[] diStringMatch(String s) {
        int[] arr = new int[s.length() + 1];
        int max = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'D') {
                arr[i] = max;
                max--;
            }
        }
        for (int i = s.length() - 1; i >= 0 && max > 0; i--) {
            if (s.charAt(i) == 'I' && arr[i + 1] == 0) {
                arr[i + 1] = max;
                max--;
            }
        }
        for (int i = 0; i < arr.length && max > 0; i++) {
            if (arr[i] == 0) {
                arr[i] = max;
                max--;
            }
        }

        return arr;
    }
}