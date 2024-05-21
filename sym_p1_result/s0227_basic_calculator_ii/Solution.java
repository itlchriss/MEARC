package g0201_0300.s0227_basic_calculator_ii;

// #Medium #Top_Interview_Questions #String #Math #Stack #Level_2_Day_18_Stack
// #2022_07_04_Time_8_ms_(95.32%)_Space_43.6_MB_(79.36%)

public class Solution {
//@ requires(*The integer division should truncate toward zero.*);
//@ requires(*You may assume that the given expression is always valid.*);
//@ requires(*Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as `eval()`.*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "3+2\2"*);
//@ requires(*Output: 7*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = " 3/2 "*);
//@ requires(*Output: 1*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = " 3+5 / 2 "*);
//@ requires(*Output: 5*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= s.length <= 3  10<sup>5</sup></code>*);
//@ requires(*param_s consists of integers and operators `('+', '-', '', '/')` separated by some number of spaces.*);
//@ requires(*param_s represents a valid expression.*);
//@ requires(*All the integers in the expression are non-negative integers in the range <code>[0, 2<sup>31</sup> - 1]</code>.*);
//@ requires(*The answer is guaranteed to fit in a 32-bit integer.*);
//@ ensures(*Given a string param_s which represents an expression, evaluate this expression and the result is its value.*);
//@ ensures(*All intermediate results will be in the range of <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>.*);
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