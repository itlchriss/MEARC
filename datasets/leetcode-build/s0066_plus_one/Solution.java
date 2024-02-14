package g0001_0100.s0066_plus_one;

// #Easy #Top_Interview_Questions #Array #Math #Programming_Skills_II_Day_3 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.8_MB_(76.07%)

public class Solution {
	//@ requires(*The input array `digits` is not null.*);
	//@ requires(*The length of the input array `digits` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `digits` is an integer between 0 and 9 (inclusive).*);
	//@ requires(*The input array `digits` does not contain any leading zeros.*);
	//@ ensures(*The output array is not null.*);
	//@ ensures(*The length of the output array is equal to the length of the input array.*);
	//@ ensures(*Each element in the output array is an integer between 0 and 9 (inclusive).*);
	//@ ensures(*The output array represents the large integer obtained by incrementing the input array by one.*);
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