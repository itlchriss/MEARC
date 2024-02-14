package g0401_0500.s0415_add_strings;

// #Easy #String #Math #Simulation #Data_Structure_II_Day_6_String
// #2022_07_16_Time_3_ms_(82.41%)_Space_43.1_MB_(66.56%)

public class Solution {
	//@ requires(*1. The input strings `num1` and `num2` must not be null.*);
	//@ requires(*2. The input strings `num1` and `num2` must consist of only digits.*);
	//@ requires(*3. The input strings `num1` and `num2` must not have any leading zeros except for the zero itself.*);
	//@ ensures(*1. The method should return a string representing the sum of `num1` and `num2`.*);
	//@ ensures(*2. The returned string should not have any leading zeros except for the zero itself.*);
    public String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
                i >= 0 || j >= 0 || carry != 0;
                i--, j--) {
            int sum = carry;
            if (i >= 0) {
                sum += Character.digit(num1.charAt(i), 10);
            }
            if (j >= 0) {
                sum += Character.digit(num2.charAt(j), 10);
            }
            carry = sum / 10;
            result.append(sum % 10);
        }
        return result.reverse().toString();
    }
}