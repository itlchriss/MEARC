package g1101_1200.s1190_reverse_substrings_between_each_pair_of_parentheses;

// #Medium #String #Stack #2022_03_03_Time_1_ms_(98.26%)_Space_42.4_MB_(40.43%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` is not empty.*);
	//@ requires(*3. The input string `s` contains only lowercase English letters and parentheses.*);
	//@ requires(*4. The input string `s` has balanced parentheses.*);
	//@ ensures(*1. The output string is not null.*);
	//@ ensures(*2. The output string does not contain any parentheses.*);
	//@ ensures(*3. The output string is the reverse of the input string, with the substrings between each pair of matching parentheses reversed.*);
    public String reverseParentheses(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '(') {
                int l = 1;
                int r = 0;
                int idx = i;
                while (l != r) {
                    i++;
                    if (s.charAt(i) == '(') {
                        l++;
                    } else if (s.charAt(i) == ')') {
                        r++;
                    }
                }
                String reversed = reverseParentheses(s.substring(idx + 1, i));
                StringBuilder temp = new StringBuilder().append(reversed);
                sb.append(temp.reverse());
            } else {
                sb.append(s.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }
}