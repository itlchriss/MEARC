package g0401_0500.s0415_add_strings;

// #Easy #String #Math #Simulation #Data_Structure_II_Day_6_String
// #2022_07_16_Time_3_ms_(82.41%)_Space_43.1_MB_(66.56%)

public class Solution {
//@ requires(*The length of the string parameter `num1` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The length of the string parameter `num2` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `num1` consists of only digits.*);
//@ requires(*The string parameter `num2` consists of only digits.*);
//@ requires(*The string parameter `num1` and `num2` don't have any leading zeros except for the zero itself.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 108\. Convert to Base -2*);
//@ requires(*Medium*);
//@ requires(*Given a number `N`, return a string consisting of `"0"` and `"1"` that represents its value in base `-2` (negative two).*);
//@ requires(**);
//@ requires(*The returned string must have no leading zeroes, unless the string is `"0"`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** 2*);
//@ requires(***Output:** "110"*);
//@ requires(***Explanation:** (-2) ^ 2 + (-2) ^ 1 = 2*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** 3*);
//@ requires(***Output:** "111"*);
//@ requires(***Explanation:** (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** 4*);
//@ requires(***Output:** "100"*);
//@ requires(***Explanation:** (-2) ^ 2 = 4*);
//@ requires(**);
//@ requires(***Note:***);
//@ requires(**);
//@ requires(**   `0 <= N <= 10^9`*);
//@ requires(**);
//@ requires(*Method signature: public String baseNeg2(int N)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The integer parameter `N` is less than or equal to 1000000000 and is greater than or equal to 0.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 1253\. Reconstruct a 3-Row Binary Matrix*);
//@ requires(*Hard*);
//@ requires(*Given the following details of a matrix with `n` columns and `3` rows :*);
//@ requires(**);
//@ requires(**   The matrix is a binary matrix, which means each element in the matrix can be `0` or `1`.*);
//@ requires(**   The sum of elements of the 0-th(upper) row is given as `upper`.*);
//@ requires(**   The sum of elements of the 1-st(middle) row is given as `middle`.*);
//@ requires(**   The sum of elements of the 2-nd(lower) row is given as `lower`.*);
//@ requires(**   The sum of elements in the i-th column(0-indexed) is `colsum[i]`, where `colsum` is given as an integer array with length `n`.*);
//@ requires(**);
//@ requires(*Your task is to reconstruct the matrix with `upper`, `middle`, `lower` and `colsum`.*);
//@ requires(**);
//@ requires(*Return _it as a 2-D integer array_.*);
//@ requires(**);
//@ requires(*If there are more than one valid solution, any of them will be accepted.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** upper = 2, middle = 1, lower = 1, colsum = \[1,1,1,1\]*);
//@ requires(***Output:** \[\[1,1,0,0\],\[0,0,1,1\],\[0,0,0,0\]\]*);
//@ requires(***Explanation:** [[1,0,1,0],[0,1,0,1],[0,0,0,0]] and [[*);
//@ ensures(*The string result is a non-negative integer represented as a string.*);
//@ ensures(*The string result does not have any leading zeros except for the zero itself.*);
//@ ensures(*If the string parameters `num1` and `num2` are equal to "11" and "123", the string result is equal to "134".*);
//@ ensures(*If the string parameters `num1` and `num2` are equal to "456" and "77", the string result is equal to "533".*);
//@ ensures(*If the string parameters `num1` and `num2` are equal to "0" and "0", the string result is equal to "0".*);
//@ ensures(*The string result is a non-negative integer represented as a string.*);
//@ ensures(*The string result does not have any leading zeros except for the zero itself.*);
//@ ensures(*If the integer parameter `N` is equal to 2, the string result is equal to "110".*);
//@ ensures(*If the integer parameter `N` is equal to 3, the string result is equal to "111".*);
//@ ensures(*If the integer parameter `N` is equal to 4, the string result is equal to "100".*);
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