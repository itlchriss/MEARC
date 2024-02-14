package g0201_0300.s0227_basic_calculator_ii;

// #Medium #Top_Interview_Questions #String #Math #Stack #Level_2_Day_18_Stack
// #2022_07_04_Time_8_ms_(95.32%)_Space_43.6_MB_(79.36%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` is a valid expression.*);
	//@ requires(*3. The input string `s` consists of integers and operators `('+', '-', '*', '/')` separated by some number of spaces.*);
	//@ requires(*4. All the integers in the expression are non-negative integers in the range `[0, 2^31 - 1]`.*);
	//@ ensures(*1. The method returns an integer value.*);
	//@ ensures(*2. The returned integer value is the result of evaluating the expression represented by the input string `s`.*);
	//@ ensures(*3. The integer division in the expression truncates toward zero.*);
	//@ ensures(*4. The returned integer value is guaranteed to fit in a 32-bit integer.*);
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