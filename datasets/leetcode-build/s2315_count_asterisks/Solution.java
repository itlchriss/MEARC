package g2301_2400.s2315_count_asterisks;

// #Easy #String #2022_06_26_Time_1_ms_(100.00%)_Space_42.2_MB_(57.14%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is at least 1 and at most 1000.*);
	//@ requires(*The input string `s` consists of lowercase English letters, vertical bars `'|'`, and asterisks `'*'`.*);
	//@ requires(*The input string `s` contains an even number of vertical bars `'|'`.*);
	//@ ensures(*The method returns an integer representing the number of asterisks in the input string `s`, excluding the asterisks between each pair of vertical bars `'|'`.*);
    public int countAsterisks(String s) {
        int c = 0;
        int n = s.length();
        int i = 0;
        while (i < n) {
            if (s.charAt(i) == '|') {
                i++;
                while (s.charAt(i) != '|') {
                    i++;
                }
            }
            if (s.charAt(i) == '*') {
                c++;
            }
            i++;
        }
        return c;
    }
}