package g0401_0500.s0415_add_strings;

// #Easy #String #Math #Simulation #Data_Structure_II_Day_6_String
// #2022_07_16_Time_3_ms_(82.41%)_Space_43.1_MB_(66.56%)

public class Solution {
//@ requires(*The length of the string parameter `num1` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `num2` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `num1` consists of only digits.*);
//@ requires(*The string parameter `num2` consists of only digits.*);
//@ requires(*The string parameter `num1` and `num2` don't have any leading zeros except for the zero itself.*);
//@ ensures(*The string result is a non-negative integer represented as a string.*);
//@ ensures(*The string result is the sum of the non-negative integers represented by the string parameters `num1` and `num2`.*);
//@ ensures(*If the string parameters `num1` and `num2` are equal to "11" and "123", the string result is equal to "134".*);
//@ ensures(*If the string parameters `num1` and `num2` are equal to "456" and "77", the string result is equal to "533".*);
//@ ensures(*If the string parameters `num1` and `num2` are equal to "0" and "0", the string result is equal to "0".*);
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