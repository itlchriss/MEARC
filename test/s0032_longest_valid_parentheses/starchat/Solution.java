package g0001_0100.s0032_longest_valid_parentheses;

// #Hard #Top_100_Liked_Questions #String #Dynamic_Programming #Stack #Big_O_Time_O(n)_Space_O(1)
// #2023_08_09_Time_1_ms_(100.00%)_Space_41.4_MB_(85.22%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 30000 and is greater than or equal to 0.*);
//@ requires(*The string parameter `s` consists of only '(' and ')'.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2521\. Maximum Score After N Operations*);
//@ requires(*Hard*);
//@ requires(*You are given `nums`, an array of positive integers of size `2 * n`. You can perform `n` operations on this array.*);
//@ requires(**);
//@ requires(*In the `ith` operation (1-indexed), you will:*);
//@ requires(*Choose two elements, `x` and `y`.*);
//@ requires(**);
//@ requires(*Receive a score of `i * min(x, y)`.*);
//@ requires(**);
//@ requires(*Choose two indices `l` and `r` where `l < r` and update `nums` with the values of `nums[l]`, `nums[l + 1],..., nums[r - 1], nums[r]` becomes `nums[l + 1], nums[l + 2],..., nums[r - 1], nums[l] + nums[r]`.*);
//@ requires(**);
//@ requires(*Return _the maximum score you can receive after performing_ `n` _operations_.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,2,3,4,5,6\]*);
//@ requires(***Output:** 11*);
//@ requires(***Explanation:** In the first operation, (1, 2) --> 2, (3, 4) --> 4, (5, 6) --> 6. The score is 1 * 2 + 2 * 4 + 3 * 6 = 2 + 8 + 18 = 28.*);
//@ requires(**);
//@ requires(*In the second operation, (1, 3) --> 4, (2, 5) --> 5, (4, 6) --> 10. The score is 2 * 4 + 3 * 5 + 4 * 10 = 8 + 15 + 40 = 63.*);
//@ requires(**);
//@ requires(*In the third operation, (1, 4) --> 5, (2, 6) --> 7, (3, 5) --> 8. The score is 3 * 5 + 4 * 7 + 5 * 8 = 15 + 28 + 40 = 83.*);
//@ requires(**);
//@ requires(*The total score is 28 + 63 + 83 = 174.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[3,5,6,7,8\]*);
//@ requires(***Output:** 102*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 105`*);
//@ requires(**   `1 <= nums[i] <= 109`*);
//@ requires(**);
//@ requires(***Follow up:** Could you find an `O(n log n)` solution?*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the string parameter `s`.*);
//@ ensures(*If the string parameter `s` is equal to "(()", the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to ")()())", the integer result is equal to 4.*);
//@ ensures(*If the string parameter `s` is equal to "", the integer result is equal to 0.*);
    public int longestValidParentheses(String s) {
        int max = 0;
        int left = 0;
        int right = 0;
        int n = s.length();
        char ch;
        for (int i = 0; i < n; i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else {
                right++;
            }
            if (right > left) {
                left = 0;
                right = 0;
            }
            if (left == right) {
                max = Math.max(max, left + right);
            }
        }
        left = 0;
        right = 0;
        for (int i = n - 1; i >= 0; i--) {
            ch = s.charAt(i);
            if (ch == '(') {
                left++;
            } else {
                right++;
            }
            if (left > right) {
                left = 0;
                right = 0;
            }
            if (left == right) {
                max = Math.max(max, left + right);
            }
        }
        return max;
    }
}