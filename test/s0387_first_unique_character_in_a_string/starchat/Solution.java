package g0301_0400.s0387_first_unique_character_in_a_string;

// #Easy #Top_Interview_Questions #String #Hash_Table #Counting #Queue
// #Data_Structure_I_Day_6_String #2022_07_13_Time_1_ms_(100.00%)_Space_42.9_MB_(86.44%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of only lowercase English letters.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2953\. Find the Minimum Number of Jumps to Reach the Last Index with Constraints*);
//@ requires(*Medium*);
//@ requires(*You are given a **0-indexed** array `nums` of `n` integers and an integer `k`. You are initially positioned at index `0`.*);
//@ requires(**);
//@ requires(*In one step, you can jump to index `i` if:*);
//@ requires(**);
//@ requires(**   `i` is in the range `[0, n - 1]`.*);
//@ requires(**   `i + nums[i]` is in the range `[0, n - 1]`.*);
//@ requires(**   `nums[i]` is **strictly** greater than `0`.*);
//@ requires(**   `nums[i]` is **strictly** less than or equal to `k`.*);
//@ requires(**);
//@ requires(*Return _the **minimum** number of jumps to reach the last index_. If it is not possible to reach the last index, return `-1`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[2,3,1,1,4\], k = 3*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** Jump to index 1 (nums\[1\] = 3), then jump to index 4 (nums\[4\] = 4).*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[2,3,0,1,4\], k = 2*);
//@ requires(***Output:** -1*);
//@ requires(***Explanation:** There is no way to reach index 4 with the given constraints.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `n == nums.length`*);
//@ requires(**   `1 <= n <= 1000`*);
//@ requires(**   `1 <= nums[i] <= 1000`*);
//@ requires(**   `1 <= k <= 1000`*);
//@ requires(**);
//@ requires(*Method signature: public int minimumJumps(int[] nums, int k)*);
//@ requires(*Method behavioural specifications:*);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 1000 and is greater than or equal to 1.*);
//@ requires(*The integer parameter `k` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ ensures(*The integer result is greater than or equal to -1 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "leetcode", the integer result is equal to 0.*);
//@ ensures(*If the string parameter `s` is equal to "loveleetcode", the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to "aabb", the integer result is equal to -1.*);
//@ ensures(*If the integer result is less than or equal to the length of the integer array parameter `nums`, it is possible to reach the last index with the given constraints.*);
//@ ensures(*If the integer result is greater than the length of the integer array parameter `nums`, it is not possible to reach the last index with the given constraints.*);
//@ ensures(*If the integer array parameter `nums` is equal to [2,3,1,1,4] and the integer parameter `k` is equal to 3, the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [2,3,0,1,4] and the integer parameter `k` is equal to 2, the integer result is equal to -1.*);
    public int firstUniqChar(String s) {
        int ans = Integer.MAX_VALUE;
        for (char i = 'a'; i <= 'z'; i++) {
            int ind = s.indexOf(i);
            if (ind != -1 && ind == s.lastIndexOf(i)) {
                ans = Math.min(ans, ind);
            }
        }
        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }
}