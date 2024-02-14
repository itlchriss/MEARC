package g2301_2400.s2351_first_letter_to_appear_twice;

// #Easy #String #Hash_Table #Counting #2022_08_14_Time_0_ms_(100.00%)_Space_42.3_MB_(29.00%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is at least 2.*);
	//@ requires(*The input string `s` consists of lowercase English letters.*);
	//@ requires(*The input string `s` has at least one repeated letter.*);
	//@ ensures(*The method returns a character that is the first letter to appear twice in the input string `s`.*);
	//@ ensures(*The returned character is one of the lowercase English letters.*);
	//@ ensures(*The second occurrence of the returned character appears before the second occurrence of any other letter in the input string `s`.*);
    public char repeatedCharacter(String s) {
        int n = s.length();
        int[] hm = new int[26];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            hm[c - 'a']++;
            if (hm[c - 'a'] > 1) {
                return c;
            }
        }
        return '0';
    }
}