package g0201_0300.s0224_basic_calculator;

// #Hard #String #Math #Stack #Recursion #2022_07_04_Time_3_ms_(98.92%)_Space_44.6_MB_(43.19%)

public class Solution {
    private int i = 0;
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` represents a valid expression.*);
	//@ requires(*The input string `s` consists of digits, '+', '-', '(', ')', and ' '.*);
	//@ requires(*The input string `s` does not contain two consecutive operators.*);
	//@ requires(*Every number and running calculation in `s` will fit in a signed 32-bit integer.*);
	//@ ensures(*The method returns an integer, which is the result of evaluating the expression represented by the input string `s`.*);

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