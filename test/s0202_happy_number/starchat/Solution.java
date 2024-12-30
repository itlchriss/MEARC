package g0201_0300.s0202_happy_number;

// #Easy #Top_Interview_Questions #Hash_Table #Math #Two_Pointers #Algorithm_II_Day_21_Others
// #Programming_Skills_I_Day_4_Loop #Level_2_Day_1_Implementation/Simulation
// #2022_06_28_Time_1_ms_(98.59%)_Space_41_MB_(64.25%)

public class Solution {
//@ requires(*The integer parameter `n` is greater than or equal to 1 and is less than or equal to 2^31 - 1.*);
//@ requires(*If the integer parameter `n` is equal to 1, the method should return true.*);
//@ requires(*If the integer parameter `n` is not equal to 1 and the sum of the squares of its digits is equal to 1, the method should return true.*);
//@ requires(*If the integer parameter `n` is not equal to 1 and the sum of the squares of its digits is not equal to 1, the method should return false.*);
//@ requires(*If the integer parameter `n` is not equal to 1 and the sum of the squares of its digits leads to a cycle which does not include 1, the method should return false.*);
//@ requires(*If the integer parameter `n` is equal to 19, the method should return true.*);
//@ requires(*If the integer parameter `n` is equal to 2, the method should return false.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2527\. Maximum Count of Positive Integer and Negative Integer*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given an array `nums` sorted in **non-decreasing** order, return _the maximum between the number of positive integers and the number of negative integers_.*);
//@ requires(**);
//@ requires(**   In other words, if the number of positive integers in `nums` is `pos` and the number of negative integers is `neg`, then return the maximum of `pos` and `neg`.*);
//@ requires(**);
//@ requires(***Note** that `0` is neither positive nor negative.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** nums = \[4,2,3\]*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** The maximum number of positive integers is 2 ([2,3]), and the maximum number of negative integers is 1 ([-4]). Therefore, return 2.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** nums = \[1\]*);
//@ requires(***Output:** 0*);
//@ requires(***Explanation:** There are no positive integers and no negative integers, so return 0.*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= nums.length <= 100`*);
//@ requires(**   `-100 <= nums[i] <= 100`*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(1)` time complexity?*);
//@ requires(**);
//@ requires(*Method signature: public int maximumCount(int[] nums)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 100 and is greater than or equal to -100.*);
//@ requires(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ ensures(*If the integer result is less than the length of the integer array parameter `nums` divided by 2, all the values in the integer array parameter `nums` are either positive or negative.*);
//@ ensures(*If the integer result is equal to the length of the integer array parameter `nums` divided by 2, there are equal number of positive and negative integers in the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [4,2,3], the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1], the integer result is equal to 0.*);
    public boolean isHappy(int n) {
        boolean happy;
        int a = n;
        int rem;
        int sum = 0;
        if (a == 1 || a == 7) {
            happy = true;
        } else if (a > 1 && a < 10) {
            happy = false;
        } else {
            while (a != 0) {
                rem = a % 10;
                sum = sum + (rem * rem);
                a = a / 10;
            }
            if (sum != 1) {
                happy = isHappy(sum);
            } else {
                happy = true;
            }
        }
        return happy;
    }
}