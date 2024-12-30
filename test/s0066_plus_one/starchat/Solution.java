package g0001_0100.s0066_plus_one;

// #Easy #Top_Interview_Questions #Array #Math #Programming_Skills_II_Day_3 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.8_MB_(76.07%)

public class Solution {
//@ requires(*The length of the integer array parameter `digits` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `digits` are less than or equal to 9 and is greater than or equal to 0.*);
//@ requires(*The integer array parameter `digits` does not contain any leading 0's.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2521\. Find the Peaks*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*You are given a **0-indexed** array `mountain` of length `n` which represents the height of a series of mountains.*);
//@ requires(**);
//@ requires(*You may choose one of the mountains at index `i` (where `0 <= i < n`) as the starting point. From this starting point, you can walk to the left or right, and you must walk **downhill** until you either reach the end of the array or a mountain where there is no longer a lower height present.*);
//@ requires(**);
//@ requires(*You can stop at any mountain, but once you stop, you cannot move left or right at all. You have to walk **downhill** to the end of the array or the next mountain where there is no longer a lower height present.*);
//@ requires(**);
//@ requires(*Return _the index of the mountain where you must stop_. If there are multiple valid answers, return the **smallest** such index.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** mountain = \[0,1,0,2,1,0\]*);
//@ requires(***Output:** 1*);
//@ requires(***Explanation:** You can choose the mountain at index 1. You cannot go to the left, as there is no lower height present. You also cannot go to the right, as you will stop at the mountain 2, and you must walk downhill to the end of the array.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** mountain = \[0,2,1,0\]*);
//@ requires(***Output:** 1*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `n == mountain.length`*);
//@ requires(**   `1 <= n <= 104`*);
//@ requires(**   `0 <= mountain[i] <= 109`*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve it using one pass?*);
//@ requires(**);
//@ requires(*Method signature: public int peakIndexInMountainArray(int[] mountain)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the integer array parameter `mountain` is less than or equal to 104 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `mountain` are less than or equal to 109 and is greater than or equal to 0.*);
//@ ensures(*The length of the integer array result is less than or equal to 100 and is greater than or equal to 1.*);
//@ ensures(*All values in the integer array result are less than or equal to 9 and is greater than or equal to 0.*);
//@ ensures(*The integer array result does not contain any leading 0's.*);
//@ ensures(*If the integer array parameter `digits` is equal to [1,2,3], the integer array result is equal to [1,2,4].*);
//@ ensures(*If the integer array parameter `digits` is equal to [4,3,2,1], the integer array result is equal to [4,3,2,2].*);
//@ ensures(*If the integer array parameter `digits` is equal to [0], the integer array result is equal to [1].*);
//@ ensures(*If the integer array parameter `digits` is equal to [9], the integer array result is equal to [1,0].*);
//@ ensures(*The integer result is less than the length of the integer array parameter `mountain` and is greater than or equal to 0.*);
//@ ensures(*If the integer array parameter `mountain` is equal to [0,1,0,2,1,0], the integer result is equal to 1.*);
//@ ensures(*If the integer array parameter `mountain` is equal to [0,2,1,0], the integer result is equal to 1.*);
//@ ensures(*If the integer array parameter `mountain` is equal to [0,1,2,3,4,5,6,7,8,9], the integer result is equal to 0.*);
//@ ensures(*If the integer array parameter `mountain` is equal to [9,8,7,6,5,4,3,2,1,0], the integer result is equal to 9.*);
//@ ensures(*If the integer array parameter `mountain` is equal to [0,1,2,3,4,5,4,3,2,1,0], the integer result is equal to 1.*);
    public int[] plusOne(int[] digits) {
        int num = 1;
        int carry = 0;
        int sum;
        //@ assume digits.length > 2;
        //@ maintaining -1 <= i <= digits.length - 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (i == digits.length - 1) {
                sum = digits[i] + carry + num;
            } else {
                sum = digits[i] + carry;
            }
            carry = sum / 10;
            digits[i] = sum % 10;
        }
        if (carry != 0) {
            int[] ans = new int[digits.length + 1];
            ans[0] = carry;
            System.arraycopy(digits, 0, ans, 1, ans.length - 1);
            return ans;
        }
        return digits;
    }
}