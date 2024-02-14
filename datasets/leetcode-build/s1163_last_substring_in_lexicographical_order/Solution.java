package g1101_1200.s1163_last_substring_in_lexicographical_order;

// #Hard #String #Two_Pointers #2023_06_02_Time_10_ms_(97.22%)_Space_45.5_MB_(83.33%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*3. The input string `s` contains only lowercase English letters.*);
	//@ ensures(*1. The output string is not null.*);
	//@ ensures(*2. The output string is a substring of the input string `s`.*);
	//@ ensures(*3. The output string is the lexicographically maximum substring of the input string `s`.*);
    public String lastSubstring(String s) {
        int i = 0;
        int j = 1;
        int k = 0;
        int n = s.length();
        char[] ca = s.toCharArray();
        while (j + k < n) {
            if (ca[i + k] == ca[j + k]) {
                k++;
            } else if (ca[i + k] > ca[j + k]) {
                j = j + k + 1;
                k = 0;
            } else {
                i = Math.max(i + k + 1, j);
                j = i + 1;
                k = 0;
            }
        }
        return s.substring(i);
    }
}