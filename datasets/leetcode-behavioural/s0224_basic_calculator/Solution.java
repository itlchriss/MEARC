package g0201_0300.s0224_basic_calculator;

// #Hard #String #Math #Stack #Recursion #2022_07_04_Time_3_ms_(98.92%)_Space_44.6_MB_(43.19%)

public class Solution {
    private int i = 0;
//@ ensures(*The string parameter `s` must not be null.*);
//@ ensures(*The integer result is the evaluation result of the mathematical expression represented by the string parameter `s`.*);
//@ ensures(*The mathematical expression in the string parameter `s` must be valid.*);
//@ ensures(*The mathematical expression in the string parameter `s` must not use the '+' operator as a unary operation.*);
//@ ensures(*The mathematical expression in the string parameter `s` may use the '-' operator as a unary operation.*);
//@ ensures(*There must be no two consecutive operators in the mathematical expression represented by the string parameter `s`.*);
//@ ensures(*Every number and running calculation in the mathematical expression represented by the string parameter `s` must fit in a signed 32-bit integer.*);

    public int calculate(String s) {
        char[] ca = s.toCharArray();
        return helper(ca);
    }

    private int helper(char[] ca) {
        int num = 0;
        int prenum = 0;
        boolean isPlus = true;
        for (; i < ca.length; i++) {
            char c = ca[i];
            if (c != ' ') {
                if (c >= '0' && c <= '9') {
                    if (num == 0) {
                        num = (c - '0');
                    } else {
                        num = num * 10 + c - '0';
                    }
                } else if (c == '+') {
                    prenum += num * (isPlus ? 1 : -1);
                    isPlus = true;
                    num = 0;
                } else if (c == '-') {
                    prenum += num * (isPlus ? 1 : -1);
                    num = 0;
                    isPlus = false;
                } else if (c == '(') {
                    i++;
                    prenum += helper(ca) * (isPlus ? 1 : -1);
                    isPlus = true;
                    num = 0;
                } else if (c == ')') {
                    return prenum + num * (isPlus ? 1 : -1);
                }
            }
        }
        return prenum + num * (isPlus ? 1 : -1);
    }
}