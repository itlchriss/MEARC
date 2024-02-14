package g0401_0500.s0434_number_of_segments_in_a_string;

// #Easy #String #2022_07_16_Time_0_ms_(100.00%)_Space_42.5_MB_(5.49%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ ensures(*The method returns an integer representing the number of segments in the string `s`.*);
	//@ ensures(*A segment is defined as a contiguous sequence of non-space characters.*);
	//@ ensures(*The method correctly counts the number of segments in the string `s`.*);
	//@ ensures(*The method handles empty strings correctly (returns 0).*);
	//@ ensures(*The method handles strings with only spaces correctly (returns 0).*);
	//@ ensures(*The method handles strings with leading or trailing spaces correctly (ignores them in counting segments).*);
	//@ ensures(*The method handles strings with multiple consecutive spaces correctly (ignores them in counting segments).*);
	//@ ensures(*The method handles strings with special characters correctly (considers them as non-space characters in counting segments).*);
    public int countSegments(String s) {
        s = s.trim();
        if (s.length() == 0) {
            return 0;
        }
        String[] splitted = s.split(" ");
        int result = 0;
        for (String value : splitted) {
            if (value.length() > 0) {
                result++;
            }
        }
        return result;
    }
}