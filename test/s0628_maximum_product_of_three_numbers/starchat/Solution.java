package g0601_0700.s0628_maximum_product_of_three_numbers;

// #Easy #Array #Math #Sorting #2022_03_21_Time_2_ms_(99.90%)_Space_55.5_MB_(5.19%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000 and is greater than or equal to 3.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000 and is greater than or equal to -1000.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2527\. Maximum Score From Removing Substrings*);
//@ requires(*Hard*);
//@ requires(*You are given a string `s` of lowercase English letters and an array of pairs of integers `pairs` where `pairs[i] = [lefti, righti]`.*);
//@ requires(*You can score the string by removing the substrings at indices `lefti`, `lefti + 1`,..., `righti` and getting `righti - lefti + 1` points. Note that the indices of the string are **0-indexed**.*);
//@ requires(**);
//@ requires(*Return _the maximum score you can get by applying the given pairs_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s = "abcde", pairs = \[\[3,4\],\[2,5\],\[1,6\]\]*);
//@ requires(***Output:** 8*);
//@ requires(***Explanation:** Remove the substring indexed 3, 4, 2, 5, 1, 6 and get 6 + 4 + 3 + 2 + 1 = 16.*);
//@ requires(*The total score is 16 - 3 - 1 = 12.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s = "abcdefg", pairs = \[\[1,3\],\[4,6\],\[8,9\]\]*);
//@ requires(***Output:** 3*);
//@ requires(***Explanation:** Remove the substring indexed 1, 3, 4, 6, 8, 9 and get 3 + 2 + 1 = 6.*);
//@ requires(*The total score is 6 - 1 = 5.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 1000`*);
//@ requires(**   `1 <= pairs.length <= 1000`*);
//@ requires(**   `0 <= lefti <= righti < s.length`*);
//@ requires(**   `pairs` is **sorted** in ascending order by `lefti`.*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(n log n)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int maximumScore(String s, int[][] pairs)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only lowercase English letters.*);
//@ requires(*The length of the integer array parameter `pairs` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `pairs` are less than or equal to 1000 and is greater than or equal to 0.*);
//@ requires(*The first value of each pair in the integer array parameter `pairs` is less than or equal to the second value of the pair.*);
//@ requires(*The integer array parameter `pairs` is sorted in ascending order by the first value of each pair.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2528\. Count Substrings That Differ by One Character and Have Same Length*);
//@ requires(*Medium*);
//@ requires(*Given two strings `s` and `t`, return _the number of substrings in_ `s` _that differ from some substring in_ `t` _by exactly one character and have the same length_.*);
//@ requires(**);
//@ requires(*A substring is a contiguous sequence of characters*);
//@ ensures(*The integer result is greater than or equal to -1000000 and is less than or equal to 1000000.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3], the integer result is equal to 6.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,4], the integer result is equal to 24.*);
//@ ensures(*If the integer array parameter `nums` is equal to [-1,-2,-3], the integer result is equal to -6.*);
//@ ensures(*If the string parameter `s` is equal to "abcde" and the integer array parameter `pairs` is equal to \[\[3,4\],\[2,5\],\[1,6\]\], the integer result is equal to 8.*);
//@ ensures(*If the string parameter `s` is equal to "abcdefg" and the integer array parameter `pairs` is equal to \[\[1,3\],\[4,6\],\[8,9\]\], the integer result is equal to 3.*);
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i > max1) {
                max3 = max2;
                max2 = max1;
                max1 = i;
            } else if (i > max2) {
                max3 = max2;
                max2 = i;
            } else if (i > max3) {
                max3 = i;
            }
            if (i < min1) {
                min2 = min1;
                min1 = i;
            } else if (i < min2) {
                min2 = i;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}