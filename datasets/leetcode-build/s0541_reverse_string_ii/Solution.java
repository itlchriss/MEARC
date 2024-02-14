package g0501_0600.s0541_reverse_string_ii;

// #Easy #String #Two_Pointers #2022_08_02_Time_1_ms_(100.00%)_Space_45_MB_(13.41%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` consists of only lowercase English letters.*);
	//@ requires(*The length of the input string `s` is between 1 and 10,000.*);
	//@ requires(*The input integer `k` is greater than or equal to 1.*);
	//@ requires(*The input integer `k` is less than or equal to 10,000.*);
	//@ ensures(*The output string is not null.*);
	//@ ensures(*The length of the output string is the same as the length of the input string `s`.*);
	//@ ensures(*The characters in the output string are reversed in groups of `k` characters for every `2k` characters counting from the start of the string.*);
	//@ ensures(*If there are fewer than `k` characters left, all of them are reversed.*);
	//@ ensures(*If there are less than `2k` but greater than or equal to `k` characters, the first `k` characters are reversed and the remaining characters are left unchanged.*);
    public String reverseStr(String s, int k) {
        StringBuilder res = new StringBuilder();
        int p1 = 0;
        int p2 = 2 * k - 1;
        if (s.length() < k) {
            res.append(reverse(s));
            return res.toString();
        }
        while (p1 < s.length()) {
            if (s.length() < p1 + k) {
                res.append(reverse(s.substring(p1)));
            } else {
                res.append(reverse(s.substring(p1, p1 + k)));
                if (s.length() < p2 + 1) {
                    res.append(s.substring(p1 + k));
                } else {
                    res.append(s, p1 + k, p2 + 1);
                }
            }
            p1 = p1 + 2 * k;
            p2 = p2 + 2 * k;
        }
        return res.toString();
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.reverse();
        return sb.toString();
    }
}