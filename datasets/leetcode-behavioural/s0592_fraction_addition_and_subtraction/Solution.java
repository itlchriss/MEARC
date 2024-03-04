package g0501_0600.s0592_fraction_addition_and_subtraction;

// #Medium #String #Math #Simulation #2022_08_25_Time_6_ms_(91.33%)_Space_40.8_MB_(97.33%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    private String format(int a, int b) {
        int gcd = Math.abs(gcd(a, b));
        return a / gcd + "/" + (b / gcd);
    }

    private int[] parse(String s) {
        int idx = s.indexOf("/");
        return new int[] {
            Integer.parseInt(s.substring(0, idx)), Integer.parseInt(s.substring(idx + 1))
        };
    }
//@ ensures(*The string parameter `expression` must only contain valid characters: '0' to '9', '/', '+', and '-'.*);
//@ ensures(*The fractions in the input expression must have the format ±numerator/denominator.*);
//@ ensures(*If the first input fraction or the output is positive, the '+' sign must be omitted.*);
//@ ensures(*Each fraction in the input and output must be an irreducible fraction.*);
//@ ensures(*The numerator and denominator of each fraction in the input expression will always be in the range [1, 10].*);
//@ ensures(*If the denominator of a fraction is 1, it means the fraction is actually an integer in a fraction format.*);
//@ ensures(*The number of given fractions in the input expression will be in the range [1, 10].*);
//@ ensures(*The numerator and denominator of the final result must be valid and within the range of a 32-bit integer.*);

    public String fractionAddition(String expression) {
        int[] rst = {0, 1};
        List<int[]> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder().append(expression.charAt(0));
        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-') {
                list.add(parse(sb.toString()));
                sb = new StringBuilder().append(c);
            } else {
                sb.append(c);
            }
        }
        list.add(parse(sb.toString()));
        for (int[] num : list) {
            rst = new int[] {rst[0] * num[1] + rst[1] * num[0], rst[1] * num[1]};
        }
        return format(rst[0], rst[1]);
    }
}