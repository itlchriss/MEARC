package g0001_0100.s0008_string_to_integer_atoi;

// #Medium #Top_Interview_Questions #String #2024_01_04_Time_1_ms_(100.00%)_Space_42.7_MB_(8.86%)

public class Solution {
//@ requires(*The string parameter `s` is less than or equal to 200 characters in length.*);
//@ requires(*The string parameter `s` consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.*);
//@ requires(*If there are leading whitespaces in the string parameter `s`, they are ignored.*);
//@ requires(*The characters are read until the next non-digit character or the end of the input is reached.*);
//@ requires(*The digits are converted into an integer, and if no digits were read, the integer is 0.*);
//@ requires(*The integer is clamped to the range [-2^31, 2^31 - 1] if it exceeds this range.*);
//@ ensures(*If the next character after leading whitespaces is '-' or '+', the result is negative or positive, respectively.*);
//@ ensures(*The final result is the integer after all the above steps have been applied.*);
    public int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int i = 0;
        boolean negetiveSign = false;
        char[] input = str.toCharArray();        
        //@ loop_invariant 0 <= i <= input.length;
        while (i < input.length && input[i] == ' ') {
            i++;
        }
        if (i == input.length) {
            return 0;
        } else if (input[i] == '+') {
            i++;
        } else if (input[i] == '-') {
            i++;
            negetiveSign = true;
        }
        int num = 0;
        //@ loop_invariant 0 <= i <= input.length;
        //@ decreases input.length - i;
        while (i < input.length && input[i] <= '9' && input[i] >= '0') {
            // current char
            int tem = input[i] - '0';
            tem = negetiveSign ? -tem : tem;
            // avoid invalid number like 038
            if (num == 0 && tem == '0') {
                i++;
            } else if (num == Integer.MIN_VALUE / 10 && tem <= -8 || num < Integer.MIN_VALUE / 10) {
                return Integer.MIN_VALUE;
            } else if (num == Integer.MAX_VALUE / 10 && tem >= 7 || num > Integer.MAX_VALUE / 10) {
                return Integer.MAX_VALUE;
            } else {
                num = num * 10 + tem;
                i++;
            }
        }
        return num;
    }
}