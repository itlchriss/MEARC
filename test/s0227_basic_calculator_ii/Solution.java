package g0201_0300.s0227_basic_calculator_ii;

// #Medium #Top_Interview_Questions #String #Math #Stack #Level_2_Day_18_Stack
// #2022_07_04_Time_8_ms_(95.32%)_Space_43.6_MB_(79.36%)

public class Solution {
//@ requires(*The integer parameter `s` represents a valid mathematical expression.*);
//@ requires(*The integer division in the expression truncates toward zero.*);
//@ requires(*The method does not use any built-in function that evaluates strings as mathematical expressions, such as `eval()`.*);
//@ requires(*The length of the string parameter `s` is greater than or equal to 1 and is less than or equal to 3 * 10^5.*);
//@ requires(*The string parameter `s` consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.*);
//@ requires(*All integers in the expression are non-negative integers in the range [0, 2^31 - 1].*);
//@ requires(*The answer will fit in a 32-bit integer.*);
//@ ensures(*The integer result is the value of the evaluated expression represented by the string parameter `s`.*);
//@ ensures(*All intermediate results during the evaluation of the expression will be in the range of [-2^31, 2^31 - 1].*);
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