package g2301_2400.s2309_greatest_english_letter_in_upper_and_lower_case;

// #Easy #Array #2022_06_19_Time_5_ms_(75.00%)_Space_42.3_MB_(100.00%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` is not empty.*);
	//@ requires(*The input string `s` consists of only lowercase and uppercase English letters.*);
	//@ ensures(*The returned string is either an uppercase English letter or an empty string.*);
	//@ ensures(*If there is a letter that appears in both lowercase and uppercase in the input string `s`, the returned string is the greatest letter among them.*);
	//@ ensures(*If there is no letter that appears in both lowercase and uppercase in the input string `s`, the returned string is an empty string.*);
    public String greatestLetter(String s) {
        char gt = ' ';
        boolean[] sA = new boolean[26];
        boolean[] uA = new boolean[26];
        for (char ch : s.toCharArray()) {
            int i;
            if (ch <= 'Z' && ch >= 'A') {
                i = ch - 'A';
                uA[i] = true;
            } else {
                i = ch - 'a';
                sA[i] = true;
            }
            if (uA[i] == sA[i] && gt < ('A' + i)) {
                gt = (char) ('A' + i);
            }
        }
        return gt == ' ' ? "" : gt + "";
    }
}