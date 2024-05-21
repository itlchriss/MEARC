package g0001_0100.s0008_string_to_integer_atoi;

// #Medium #Top_Interview_Questions #String #2024_01_04_Time_1_ms_(100.00%)_Space_42.7_MB_(8.86%)

public class Solution {
//@ requires(*Implement the `myAtoi(string s)` function, which converts a string to a 32-bit signed integer (similar to C/C++'s `atoi` function).*);
//@ requires(*The algorithm for `myAtoi(string s)` is as follows:*);
//@ requires(*1.*);
//@ requires(*Read in and ignore any leading whitespace.*);
//@ requires(*2.*);
//@ requires(*Check if the next character (if not already at the end of the string) is `'-'` or `'+'`.*);
//@ requires(*Read this character in if it is either.*);
//@ requires(*3.*);
//@ requires(*Read in next the characters until the next non-digit character or the end of the input is reached.*);
//@ requires(*The rest of the string is ignored.*);
//@ requires(*4.*);
//@ requires(*Convert these digits into an integer (i.e. `"123" -> 123`, `"0032" -> 32`).*);
//@ requires(*If no digits were read, then the integer is `0`.*);
//@ requires(*Change the sign as necessary (from step 2).*);
//@ requires(*5.*);
//@ requires(*If the integer is out of the 32-bit signed integer range <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>, then clamp the integer so that it remains in the range.*);
//@ requires(*Specifically, integers less than <code>-2<sup>31</sup></code> should be clamped to <code>-2<sup>31</sup></code>, and integers greater than <code>2<sup>31</sup> - 1</code> should be clamped to <code>2<sup>31</sup> - 1</code>.*);
//@ requires(*6.*);
//@ requires(*Note:*);
//@ requires(*Only the space character `' '` is considered a whitespace character.*);
//@ requires(*Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "42"*);
//@ requires(*Output: 42*);
//@ requires(*Explanation: The underlined characters are what is read in, the caret is the current reader position.*);
//@ requires(*Step 1: "42" (no characters read because there is no leading whitespace)*);
//@ requires(*^*);
//@ requires(*Step 2: "42" (no characters read because there is neither a '-' nor '+')*);
//@ requires(*^*);
//@ requires(*Step 3: "42" ("42" is read in)*);
//@ requires(*^*);
//@ requires(*The parsed integer is 42.*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = " -42"*);
//@ requires(*Output: -42*);
//@ requires(*Explanation:*);
//@ requires(*Step 1: " -42" (leading whitespace is read and ignored)*);
//@ requires(*^*);
//@ requires(*^*);
//@ requires(*Step 3: " -42" ("42" is read in)*);
//@ requires(*^*);
//@ requires(*The parsed integer is -42.*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "4193 with words"*);
//@ requires(*Output: 4193*);
//@ requires(*Explanation:*);
//@ requires(*Step 1: "4193 with words" (no characters read because there is no leading whitespace)*);
//@ requires(*^*);
//@ requires(*Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')*);
//@ requires(*^*);
//@ requires(*Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)*);
//@ requires(*^*);
//@ requires(*The parsed integer is 4193.*);
//@ requires(*Example 4:*);
//@ requires(*Input: s = "words and 987"*);
//@ requires(*Output: 0*);
//@ requires(*Explanation:*);
//@ requires(*Step 1: "words and 987" (no characters read because there is no leading whitespace)*);
//@ requires(*^*);
//@ requires(*Step 2: "words and 987" (no characters read because there is neither a '-' nor '+')*);
//@ requires(*^*);
//@ requires(*Step 3: "words and 987" (reading stops immediately because there is a non-digit 'w')*);
//@ requires(*^*);
//@ requires(*The parsed integer is 0 because no digits were read.*);
//@ requires(*Example 5:*);
//@ requires(*Input: s = "-91283472332"*);
//@ requires(*Output: -2147483648*);
//@ requires(*Explanation:*);
//@ requires(*Step 1: "-91283472332" (no characters read because there is no leading whitespace)*);
//@ requires(*^*);
//@ requires(*^*);
//@ requires(*Step 3: "-91283472332" ("91283472332" is read in)*);
//@ requires(*^*);
//@ requires(*The parsed integer is -91283472332.*);
//@ requires(**);
//@ requires(*Constraints:*);
//@ requires(*`0 <= s.length <= 200`*);
//@ requires(*`s` consists of English letters (lower-case and upper-case), digits (`0-9`), `' '`, `'+'`, `'-'`, and `'.'*);
//@ requires(*`.*);
//@ ensures(*This determines if the final result is negative or positive respectively.*);
//@ ensures(*Assume the result is positive if neither is present.*);
//@ ensures(*Return the integer as the final result.*);
//@ ensures(*Since 42 is in the range [-2<sup>31</sup>, 2<sup>31</sup> - 1], the final result is 42.*);
//@ ensures(*Step 2: " -42" ('-' is read, so the result should be negative)*);
//@ ensures(*Since -42 is in the range [-2<sup>31</sup>, 2<sup>31</sup> - 1], the final result is -42.*);
//@ ensures(*Since 4193 is in the range [-2<sup>31</sup>, 2<sup>31</sup> - 1], the final result is 4193.*);
//@ ensures(*Since 0 is in the range [-2<sup>31</sup>, 2<sup>31</sup> - 1], the final result is 0.*);
//@ ensures(*Step 2: "-91283472332" ('-' is read, so the result should be negative)*);
//@ ensures(*Since -91283472332 is less than the lower bound of the range [-2<sup>31</sup>, 2<sup>31</sup> - 1], the final result is clamped to -2<sup>31</sup> = -2147483648.*);
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