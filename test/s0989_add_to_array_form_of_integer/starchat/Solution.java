package g0901_1000.s0989_add_to_array_form_of_integer;

// #Easy #Array #Math #Programming_Skills_II_Day_5
// #2022_03_31_Time_7_ms_(65.92%)_Space_62.4_MB_(29.05%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
//@ requires(*The length of the integer array parameter `num` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `num` are less than or equal to 9 and are greater than or equal to 0.*);
//@ requires(*The integer array parameter `num` does not contain any leading zeros except for the zero itself.*);
//@ requires(*The integer parameter `k` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 1004\. Max Consecutive Ones III*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given a binary array `nums` and an integer `k`.*);
//@ requires(**);
//@ requires(*Find the maximum number of consecutive `1`'s in `nums` if you can flip at most `k` `0`'s.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1,1,1,0,0,0,1,1,1,1,0\], k = 2*);
//@ requires(***Output:** 6*);
//@ requires(***Explanation:** \[1,1,1,0,0,1,1,1,1,1,1\]*);
//@ requires(*Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1\], k = 3*);
//@ requires(***Output:** 10*);
//@ requires(***Explanation:** \[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1\]*);
//@ requires(*Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 105`*);
//@ requires(**   `nums[i]` is either `0` or `1`.*);
//@ requires(**   `0 <= k <= nums.length`*);
//@ requires(**);
//@ requires(***Follow up:** What if the input numbers come in one by one as an infinite stream? In other words, you can't store all numbers coming from the stream as it's too large to hold in memory. Could you solve it efficiently?*);
//@ requires(**);
//@ requires(***Note:** In the follow-up question, integers are read from a stream as an infinite sequence of characters for simplicity. For example, `234` will be read as `2`, `3`, `4`.*);
//@ requires(**);
//@ requires(*Method signature: public int longestOnes(int[] nums, int k)*);
//@ requires(**);
//@ requires(*Method behavioural specifications:*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are either 0 or 1.*);
//@ requires(*The integer parameter `k` is less than or equal to 100000 and is greater than or equal to 0.*);
//@ requires(*If the integer array parameter `nums` is equal to [0,0,1,1,0,0,1,1,1,0,1,1,0,*);
//@ ensures(*The length of the list result is less than or equal to the length of the integer array parameter `num` plus 1.*);
//@ ensures(*All values in the list result are less than or equal to 9 and are greater than or equal to 0.*);
//@ ensures(*The list result does not contain any leading zeros except for the zero itself.*);
//@ ensures(*If the integer array parameter `num` is equal to [1,2,0,0] and the integer parameter `k` is equal to 34, the list result is equal to [1,2,3,4].*);
//@ ensures(*If the integer array parameter `num` is equal to [2,7,4] and the integer parameter `k` is equal to 181, the list result is equal to [4,5,5].*);
//@ ensures(*If the integer array parameter `num` is equal to [2,1,5] and the integer parameter `k` is equal to 806, the list result is equal to [1,0,2,1].*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,1,1,0,0,0,1,1,1,1,0] and the integer parameter `k` is equal to 2, the integer result is equal to 6.*);
    public List<Integer> addToArrayForm(int[] num, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        int carry = 0;
        for (int i = num.length - 1; i >= 0; i--) {
            int temp = num[i] + k % 10 + carry;
            result.add(temp % 10);
            carry = temp / 10;
            k /= 10;
        }
        while (k > 0) {
            int t = k % 10 + carry;
            result.add(t % 10);
            carry = t / 10;
            k /= 10;
        }
        if (carry == 1) {
            result.add(1);
        }
        Collections.reverse(result);
        return result;
    }
}