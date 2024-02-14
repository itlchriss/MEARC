package g1401_1500.s1446_consecutive_characters;

// #Easy #String #2022_03_28_Time_3_ms_(30.50%)_Space_42.3_MB_(69.58%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*The input string `s` consists of only lowercase English letters.*);
	//@ ensures(*The method returns an integer value representing the power of the input string `s`.*);
	//@ ensures(*The returned value is greater than or equal to 1.*);
	//@ ensures(*The returned value is the maximum length of a non-empty substring that contains only one unique character.*);
    public int maxPower(String s) {
        int max = 0;
        int i = 0;
        while (i < s.length()) {
            int start = i;
            while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                i++;
            }
            max = Math.max(max, i - start + 1);
            i++;
        }
        return max;
    }
}