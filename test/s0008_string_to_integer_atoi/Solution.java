package g0001_0100.s0008_string_to_integer_atoi;

// #Medium #Top_Interview_Questions #String #2024_01_04_Time_1_ms_(100.00%)_Space_42.7_MB_(8.86%)

public class Solution {
//@ requires(*The string parameter `str` consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.*);
//@ requires(*The length of the string parameter `str` is less than or equal to 200.*);
//@ requires(*Read in and ignore any leading whitespace.*);
//@ requires(*Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.*);
//@ requires(*Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).*);
//@ requires(*If the integer is out of the 32-bit signed integer range [-2^31, 2^31 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -2^31 should be clamped to -2^31, and integers greater than 2^31 - 1 should be clamped to 2^31 - 1.*);
//@ ensures(*Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.*);
//@ ensures(*Return the integer as the final result.*);
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