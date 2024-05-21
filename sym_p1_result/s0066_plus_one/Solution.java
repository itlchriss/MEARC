package g0001_0100.s0066_plus_one;

// #Easy #Top_Interview_Questions #Array #Math #Programming_Skills_II_Day_3 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.8_MB_(76.07%)

public class Solution {
//@ requires(*You are given a large integer represented as an integer array param_digits, where each `digits[i]` is the `ith` digit of the integer.*);
//@ requires(*The digits are ordered from most significant to least significant in left-to-right order.*);
//@ requires(*The large integer does not contain any leading `0`'s.*);
//@ requires(*Example 1:*);
//@ requires(*Input: digits = [1,2,3]*);
//@ requires(*Output: [1,2,4]*);
//@ requires(*Explanation: The array represents the integer 123.*);
//@ requires(*Incrementing by one gives 123 + 1 = 124.*);
//@ requires(*Example 2:*);
//@ requires(*Input: digits = [4,3,2,1]*);
//@ requires(*Output: [4,3,2,2]*);
//@ requires(*Explanation: The array represents the integer 4321.*);
//@ requires(*Incrementing by one gives 4321 + 1 = 4322.*);
//@ requires(*Example 3:*);
//@ requires(*Input: digits = [0]*);
//@ requires(*Output: [1]*);
//@ requires(*Explanation: The array represents the integer 0.*);
//@ requires(*Incrementing by one gives 0 + 1 = 1.*);
//@ requires(*Example 4:*);
//@ requires(*Input: digits = [9]*);
//@ requires(*Output: [1,0]*);
//@ requires(*Explanation: The array represents the integer 9.*);
//@ requires(*Incrementing by one gives 9 + 1 = 10.*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= digits.length <= 100`*);
//@ requires(*`0 <= digits[i] <= 9`*);
//@ requires(*param_digits does not contain any leading `0`'s.*);
//@ ensures(*Increment the large integer by one and the result is the resulting array of digits.*);
//@ ensures(*Thus, the result should be [1,2,4].*);
//@ ensures(*Thus, the result should be [4,3,2,2].*);
//@ ensures(*Thus, the result should be [1].*);
//@ ensures(*Thus, the result should be [1,0].*);
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