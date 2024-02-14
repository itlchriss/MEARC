package g0401_0500.s0459_repeated_substring_pattern;

// #Easy #String #String_Matching #Programming_Skills_II_Day_2
// #2022_07_19_Time_8_ms_(96.64%)_Space_51.2_MB_(47.98%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The length of the input string `s` is greater than or equal to 1 and less than or equal to 10^4.*);
	//@ requires(*3. The input string `s` consists only of lowercase English letters.*);
	//@ ensures(*1. The method returns a boolean value indicating whether the input string `s` can be constructed by taking a substring of it and appending multiple copies of the substring together.*);
	//@ ensures(*2. If the method returns true, it means that the input string `s` can be constructed by taking a substring of it and appending multiple copies of the substring together.*);
	//@ ensures(*3. If the method returns false, it means that the input string `s` cannot be constructed by taking a substring of it and appending multiple copies of the substring together.*);
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        if (n < 2) {
            return false;
        }
        int i = 0;
        while (i < (n + 1) / 2) {
            if (n % (i + 1) != 0) {
                i++;
                continue;
            }
            boolean match = true;
            String substring = s.substring(0, i + 1);
            int skippedI = i;
            for (int j = i + 1; j < n; j += i + 1) {
                if (!s.substring(j, j + i + 1).equals(substring)) {
                    match = false;
                    break;
                }
                skippedI += i + 1;
            }
            if (match) {
                return true;
            }
            i = skippedI;
            i++;
        }
        return false;
    }
}