package g0301_0400.s0396_rotate_function;

// #Medium #Array #Dynamic_Programming #Math #2022_07_15_Time_4_ms_(81.33%)_Space_86_MB_(54.94%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is equal to the length of the integer array parameter `nums` in the context.*);
//@ requires(*The integer array parameter `nums` consists of integers.*);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 100 and is greater than or equal to -100.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2521\. Maximum Score After N Operations*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(*You are given `nums`, an array of positive integers of size `2 * n`. You can perform `n` operations on this array. In the `ith` operation (1-indexed), you will:*);
//@ requires(*Choose two elements, `x` and `y`.*);
//@ requires(**);
//@ requires(*Receive a score of `i * min(x, y)`.*);
//@ requires(**);
//@ requires(*Remove `x` and `y` from `nums`.*);
//@ requires(**);
//@ requires(*Return _the maximum score you can receive after performing_ `n` _operations_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,2,3,4,5,6\]*);
//@ requires(***Output:** 11*);
//@ requires(***Explanation:** The optimal choice of operations is:*);
//@ requires(*Choose x = 2 and y = 1, receive a score of 1 * min(2, 1) = 1.*);
//@ requires(**);
//@ requires(*Choose x = 5 and y = 6, receive a score of 2 * min(5, 6) = 2.*);
//@ requires(**);
//@ requires(*Choose x = 4 and y = 3, receive a score of 3 * min(4, 3) = 3.*);
//@ requires(**);
//@ requires(*The total score is 1 + 2 + 3 = 6.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[3,4,6,8,10,2\]*);
//@ requires(***Output:** 18*);
//@ requires(***Explanation:** The optimal choice of operations is:*);
//@ requires(*Choose x = 4 and y = 2, receive a score of 1 * min(4, 2) = 2.*);
//@ requires(**);
//@ requires(*Choose x = 6 and y = 3, receive a score of 2 * min(6, 3) = 4.*);
//@ requires(**);
//@ requires(*Choose x = 10 and y = 8, receive a score of 3 * min(10, 8) = 8.*);
//@ requires(**);
//@ requires(*The total score is 2 + 4 + 8 = 14.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 105`*);
//@ requires(**   `1 <= nums[i] <= 104`*);
//@ requires(**);
//@ requires(*Method signature: public int maximumScore(int[] nums)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 2522\. Maximum Number of Non-Overlapping Substrings*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(*Given a string `s` of lowercase letters and an integer `k`.*);
//@ requires(**);
//@ requires(*You can choose any characters of the string and replace them with any other lowercase English letters. You can perform this operation at most `k` times.*);
//@ requires(**);
//@ requires(*Find the maximum number of non-empty substrings that have the same letters, and return this number modulo `10*);
//@ ensures(*The integer result is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ ensures(*If the integer array parameter `nums` is equal to [4,3,2,6], the integer result is equal to 26.*);
//@ ensures(*If the integer array parameter `nums` is equal to [100], the integer result is equal to 0.*);
//@ ensures(*The integer result is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,4,5,6], the integer result is equal to 11.*);
//@ ensures(*If the integer array parameter `nums` is equal to [3,4,6,8,10,2], the integer result is equal to 18.*);
    public int maxRotateFunction(int[] nums) {
        int allSum = 0;
        int len = nums.length;
        int f = 0;
        //@ maintaining 0 <= i <= nums.length;
        for (int i = 0; i < len; i++) {
            f += i * nums[i];
            allSum += nums[i];
        }
        int max = f;
        //@ maintaining 0 <= i <= nums.length - 1 || i == -1;
        for (int i = len - 1; i >= 1; i--) {
            f = f + allSum - len * nums[i];
            max = Math.max(f, max);
        }
        return max;
    }
}