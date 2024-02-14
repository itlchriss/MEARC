package g0101_0200.s0151_reverse_words_in_a_string;

// #Medium #String #Two_Pointers #Udemy_Strings
// #2022_06_25_Time_2_ms_(99.94%)_Space_42.4_MB_(88.57%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` is not empty.*);
	//@ requires(*3. The input string `s` contains at least one word.*);
	//@ requires(*4. The input string `s` may contain leading or trailing spaces.*);
	//@ requires(*5. The input string `s` may contain multiple spaces between two words.*);
	//@ ensures(*1. The output string is not null.*);
	//@ ensures(*2. The output string is not empty.*);
	//@ ensures(*3. The output string contains all the words from the input string `s` in reverse order.*);
	//@ ensures(*4. The output string has a single space separating the words.*);
	//@ ensures(*5. The output string does not contain leading or trailing spaces.*);
	//@ ensures(*6. The output string does not contain multiple spaces between two words.*);
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                i--;
                continue;
            }
            int start = s.lastIndexOf(' ', i);
            sb.append(' ');
            sb.append(s, start + 1, i + 1);
            i = start - 1;
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}