package g0401_0500.s0415_add_strings;

// #Easy #String #Math #Simulation #Data_Structure_II_Day_6_String
// #2022_07_16_Time_3_ms_(82.41%)_Space_43.1_MB_(66.56%)

public class Solution {
//@ requires(*You must solve the problem without using any built-in library for handling large integers (such as `BigInteger`).*);
//@ requires(*You must also not convert the inputs to integers directly.*);
//@ requires(*Example 1:*);
//@ requires(*Input: num1 = "11", num2 = "123"*);
//@ requires(*Output: "134"*);
//@ requires(*Example 2:*);
//@ requires(*Input: num1 = "456", num2 = "77"*);
//@ requires(*Output: "533"*);
//@ requires(*Example 3:*);
//@ requires(*Input: num1 = "0", num2 = "0"*);
//@ requires(*Output: "0"*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= num1.length, num2.length <= 10<sup>4</sup></code>*);
//@ requires(*param_num1 and param_num2 consist of only digits.*);
//@ requires(*param_num1 and param_num2 don't have any leading zeros except for the zero itself.*);
//@ ensures(*Given two non-negative integers, param_num1 and param_num2 represented as string, the result is the sum of param_num1 and param_num2 as a string.*);
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