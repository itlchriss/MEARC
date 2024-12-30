package g0101_0200.s0168_excel_sheet_column_title;

// #Easy #String #Math #2022_06_25_Time_0_ms_(100.00%)_Space_41.3_MB_(58.37%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 231 - 1 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 171\. Excel Sheet Column Number*);
//@ requires(**);
//@ requires(*Medium*);
//@ requires(**);
//@ requires(*Given a string `columnTitle` that represents the column title as appears in an Excel sheet, return _its corresponding column number_.*);
//@ requires(**);
//@ requires(*For example:*);
//@ requires(**);
//@ requires(*    A -> 1*);
//@ requires(*    B -> 2*);
//@ requires(*    C -> 3*);
//@ requires(*   ...*);
//@ requires(*    Z -> 26*);
//@ requires(*    AA -> 27*);
//@ requires(*    AB -> 28*);
//@ requires(*   ... *);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** columnTitle = "A"*);
//@ requires(**);
//@ requires(***Output:** 1*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** columnTitle = "AB"*);
//@ requires(**);
//@ requires(***Output:** 28*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** columnTitle = "ZY"*);
//@ requires(**);
//@ requires(***Output:** 701*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= columnTitle.length <= 7`*);
//@ requires(**   `columnTitle` consists only of uppercase English letters.*);
//@ requires(**   `columnTitle` is in the range of "A" to "FXSHRXW".*);
//@ requires(*Method signature: public int titleToNumber(String s)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(*The string parameter `s` is not empty.*);
//@ requires(*The string parameter `s` consists only of uppercase English letters.*);
//@ requires(*The length of the string parameter `s` is less than or equal to 7.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 170\. Reverse Integer*);
//@ requires(**);
//@ requires(*Easy*);
//@ requires(**);
//@ requires(*Given a signed 32-bit integer `x`, return _x with its digits reversed_. If reversing `x` causes the value to go outside the signed 32-bit integer range `[-231, 231 - 1]`, then return `0`.*);
//@ requires(**);
//@ requires(***Assume the environment does not allow you to store 64-bit integers (signed or unsigned).***);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** x = 123*);
//@ requires(**);
//@ requires(***Output:** 321*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** x = -123*);
//@ requires(**);
//@ requires(***Output:** -321*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** x = 120*);
//@ requires(**);
//@ requires(***Output:** 21*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `-231 <= x <= 231 - 1`*);
//@ requires(**);
//@ requires(***Follow up:** Could you solve the problem without converting the integer to a string?*);
//@ ensures(*The string result is a valid Excel sheet column title.*);
//@ ensures(*The string result consists of only English uppercase letters.*);
//@ ensures(*The length of the string result is less than or equal to 26.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the string result is equal to "A".*);
//@ ensures(*If the integer parameter `n` is equal to 28, the string result is equal to "AB".*);
//@ ensures(*If the integer parameter `n` is equal to 701, the string result is equal to "ZY".*);
//@ ensures(*If the integer parameter `n` is equal to 2147483647, the string result is equal to "FXSHRXW".*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to 231 - 1.*);
//@ ensures(*If the string parameter `s` is equal to "A", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to "AB", the integer result is equal to 28.*);
//@ ensures(*If the string parameter `s` is equal to "ZY", the integer result is equal to 701.*);
//@ ensures(*If the string parameter `s` is equal to "FXSHRXW", the integer result is equal to 2147483647.*);
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int remainder = n % 26;
            if (remainder == 0) {
                remainder += 26;
            }
            if (n >= remainder) {
                n -= remainder;
                sb.append((char) (remainder + 64));
            }
            n /= 26;
        }
        return sb.reverse().toString();
    }
}