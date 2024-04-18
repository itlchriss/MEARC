package g0001_0100.s0066_plus_one;

// #Easy #Top_Interview_Questions #Array #Math #Programming_Skills_II_Day_3 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.8_MB_(76.07%)

public class Solution {
// @requires digits != null && digits.length >= 1 && digits.length <= 100;
// @requires (\forall int i; 0 <= i && i < digits.length; 0 <= digits[i] && digits[i] <= 9);
// @requires (\forall int i; 0 <= i && i < digits.length - 1; digits[i] != 0);
// @ensures \result != null;
// @ensures \result.length == digits.length;
// @ensures (\forall int i; 0 <= i && i < digits.length - 1; \result[i] == digits[i]);
// @ensures \result[digits.length - 1] == digits[digits.length - 1] + 1;
// @ensures (\forall int i; 0 <= i && i < digits.length - 1; \result[i] != 0);
// @ensures \result[digits.length - 1] != 10;
    public int[] plusOne(int[] digits) {
        int num = 1;
        int carry = 0;
        int sum;
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