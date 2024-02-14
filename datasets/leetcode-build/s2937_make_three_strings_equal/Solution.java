package g2901_3000.s2937_make_three_strings_equal;

// #Easy #String #2024_01_03_Time_1_ms_(100.00%)_Space_44.7_MB_(5.99%)

public class Solution {
	//@ requires(*The lengths of s1, s2, and s3 are between 1 and 100 (inclusive).*);
	//@ requires(*s1, s2, and s3 consist only of lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the minimum number of operations needed to make the three strings equal, or -1 if it is not possible to make them equal.*);
	//@ ensures(*If there is a way to make the three strings equal, the method performs the minimum number of operations to achieve this.*);
    public int findMinimumOperations(String s1, String s2, String s3) {
        boolean pos = true;
        int n = Math.min(s1.length(), Math.min(s2.length(), s3.length()));
        int ans = 0;
        for (int i = 0; i < n && pos; i++) {
            if (s1.charAt(i) == s2.charAt(i) && s1.charAt(i) == s3.charAt(i)) {
                ans++;
            } else {
                pos = false;
            }
        }
        return ans == 0 ? -1 : s1.length() + s2.length() + s3.length() - (3 * ans);
    }
}