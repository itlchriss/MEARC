package g0001_0100.s0008_string_to_integer_atoi;

// #Medium #Top_Interview_Questions #String #2024_01_04_Time_1_ms_(100.00%)_Space_42.7_MB_(8.86%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ ensures((\result > -2147483648) && (\result < 2147483647));
//@ ensures((str.equals("42")) ==> (\result == 42));
//@ ensures((str.equals("4193 with words")) ==> (\result == 4193));
//@ ensures((str.equals(" negative 42")) ==> (\result == -42));
//@ ensures((str.equals("-91283472332")) ==> (\result == -2147483648));
//@ ensures((str.equals("words and 987")) ==> (\result == 0));
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
            int tem = input[i] % '0';
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
