package g0001_0100.s0008_string_to_integer_atoi;

// #Medium #Top_Interview_Questions #String #2024_01_04_Time_1_ms_(100.00%)_Space_42.7_MB_(8.86%)

public class Solution {
//@ requires(*The length of the string parameter `str` is less than or equal to 200 and is greater than or equal to 0.*);
//@ requires(*The string parameter `str` consists of English letters, digits, spaces, plus signs, minus signs, and periods.*);
//@ ensures(*The integer result is greater than or equal to -2147483648 and is less than or equal to 2147483647.*);
//@ ensures(*If the string parameter `str` is equal to "42", the integer result is equal to 42.*);
//@ ensures(*If the string parameter `str` is equal to " -42", the integer result is equal to -42.*);
//@ ensures(*If the string parameter `str` is equal to "4193 with words", the integer result is equal to 4193.*);
//@ ensures(*If the string parameter `str` is equal to "words and 987", the integer result is equal to 0.*);
//@ ensures(*If the string parameter `str` is equal to "-91283472332", the integer result is equal to -2147483648.*);
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