package g0201_0300.s0227_basic_calculator_ii;

// #Medium #Top_Interview_Questions #String #Math #Stack #Level_2_Day_18_Stack
// #2022_07_04_Time_8_ms_(95.32%)_Space_43.6_MB_(79.36%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 300000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.*);
//@ requires(*The string parameter `s` represents a valid expression.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 224\. Basic Calculator*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(**);
//@ requires(***Note:** You are **not** allowed to use any built-in function which evaluates strings as mathematical expressions, such as `eval()`.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "1 + 1 "*);
//@ requires(***Output:** 2*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  " 2-1 + 2  "*);
//@ requires(***Output:** 3*);
//@ requires(**);
//@ requires(***Example 3:***);
//@ requires(**);
//@ requires(***Input:** s =  " (1+(4+5+2)-3)+(6+8) "*);
//@ requires(***Output:** 23*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 3 * 105`*);
//@ requires(**   `s` consists of digits, `'+'`, `'-'`, `'('`, `')'`, and `' '`.*);
//@ requires(**   `s` represents a valid expression.*);
//@ requires(**   `'+'` is **not** used as a unary operation (i.e., `"+1 "` and `"+(2 + 3) "` is invalid).*);
//@ requires(**   `'-'` could be used as a unary operation (i.e., `"-1 "` and `"-(2 + 3) "` is valid).*);
//@ requires(**   There will be no two consecutive operators in the input.*);
//@ requires(**   Every number and running calculation will fit in a signed 32-bit integer.*);
//@ requires(**);
//@ requires(*Method signature: public int calculate(String s)*);
//@ requires(**);
//@ requires(*What are the method behavioural specifications for the given context?*);
//@ requires(**);
//@ requires(*output format: a list with '-' as bullets*);
//@ requires(**);
//@ requires(*The length of the string parameter `s` is less than or equal to 300000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` consists of digits, '+', '-', '(', ')', and ' characters.*);
//@ requires(*The string parameter `s` represents a valid expression.*);
//@ requires(*The '+' is not used as a unary operation.*);
//@ requires(*The '-' could be used as a unary operation.*);
//@ requires(*There will be no two consecutive operators in the input.*);
//@ requires(*Every number and running calculation will fit in a signed 32-bit integer.*);
//@ requires(**);
//@ requires(*Example #20*);
//@ requires(*Software specification: 222\. Basic Calculator III*);
//@ requires(**);
//@ requires(*Hard*);
//@ requires(**);
//@ requires(*Given a string `formula` representing a **quantum circuit**, return _the evaluation of_ `formula`.*);
//@ requires(**);
//@ requires(*The format of a quantum circuit is as follows:*);
//@ requires(**);
//@ requires(**   The input is the only argument `formula`.*);
//@ requires(**   `formula` consists of digits, `'+'`, `'-'`, `'*'`, `'/'`, `'('`, and `')'`.*);
//@ requires(**   The integer division should **truncate toward zero**.*);
//@ requires(**   There will be no two consecutive operators in the input.*);
//@ requires(**   The input represents a **valid** quantum circuit.*);
//@ requires(**   The evaluation of the circuit follows the rules of **quantum mechanics**.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** formula =  "3+5 / 2 "*);
//@ requires(***Output:** 5*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** formula =  "4*(5-(2+3)) "*);
//@ requires(***Output:** 14*);
//@ requires(**);
//@ requires(***Example*);
//@ ensures(*All intermediate results will be in the range of [-231, 231 - 1].*);
//@ ensures(*The integer result is guaranteed to fit in a 32-bit integer.*);
//@ ensures(*If the string parameter `s` is equal to "3+2*2", the integer result is equal to 7.*);
//@ ensures(*If the string parameter `s` is equal to " 3/2 ", the integer result is equal to 1.*);
//@ ensures(*If the string parameter `s` is equal to " 3+5 / 2 ", the integer result is equal to 5.*);
//@ ensures(*Given a string `s` representing a valid expression, implement a basic calculator to evaluate it, and return _the result of the evaluation_.*);
//@ ensures(*If the string parameter `s` is equal to "1 + 1", the integer result is equal to 2.*);
//@ ensures(*If the string parameter `s` is equal to " 2-1 + 2 ", the integer result is equal to 3.*);
//@ ensures(*If the string parameter `s` is equal to " (1+(4+5+2)-3)+(6+8) ", the integer result is equal to 23.*);
    public int calculate(String s) {
        int sum = 0;
        int tempSum = 0;
        int num = 0;
        char lastSign = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            // i == s.length() - 1 will make sure that after last num is
            // made and there is nothing to read anything from 's', the final computation is done
            if (i == s.length() - 1 || !Character.isDigit(c) && c != ' ') {
                switch (lastSign) {
                    case '+':
                        sum += tempSum;
                        tempSum = num;
                        break;
                    case '-':
                        sum += tempSum;
                        tempSum = -num;
                        break;
                    case '*':
                        tempSum *= num;
                        break;
                    case '/':
                        if (num != 0) {
                            tempSum /= num;
                        }
                        break;
                    default:
                        break;
                }
                lastSign = c;
                num = 0;
            }
        }
        // finally, add tempSum to sum
        sum += tempSum;
        return sum;
    }
}