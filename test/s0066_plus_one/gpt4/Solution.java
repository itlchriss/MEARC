package g0001_0100.s0066_plus_one;

// #Easy #Top_Interview_Questions #Array #Math #Programming_Skills_II_Day_3 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.8_MB_(76.07%)

public class Solution {
//@ requires(*The length of the integer array parameter `digits` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `digits` are less than or equal to 9 and are greater than or equal to 0.*);
//@ requires(*The first value of the integer array parameter `digits` is not equal to 0.*);
//@ ensures(*The length of the integer array result is greater than or equal to the length of the integer array parameter `digits` and is less than or equal to the length of the integer array parameter `digits` plus 1.*);
//@ ensures(*If the integer array parameter `digits` is equal to [1,2,3], the integer array result is equal to [1,2,4].*);
//@ ensures(*If the integer array parameter `digits` is equal to [4,3,2,1], the integer array result is equal to [4,3,2,2].*);
//@ ensures(*If the integer array parameter `digits` is equal to [0], the integer array result is equal to [1].*);
//@ ensures(*If the integer array parameter `digits` is equal to [9], the integer array result is equal to [1,0].*);
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