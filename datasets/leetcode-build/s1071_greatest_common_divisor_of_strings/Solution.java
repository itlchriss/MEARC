package g1001_1100.s1071_greatest_common_divisor_of_strings;

// #Easy #String #Math #2022_02_27_Time_1_ms_(82.09%)_Space_42.6_MB_(33.55%)

public class Solution {
	//@ requires(*The input strings `str1` and `str2` are not null.*);
	//@ requires(*The lengths of `str1` and `str2` are between 1 and - `str1` and `str2` consist of English uppercase letters.*);
	//@ ensures(*The method returns a string `x` that divides both `str1` and `str2`.*);
	//@ ensures(*The returned string `x` is the largest possible string that divides both `str1` and `str2`.*);
	//@ ensures(*If there is no string `x` that divides both `str1` and `str2`, the method returns an empty string.*);
    public String gcdOfStrings(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return "";
        }
        if (str1.equals(str2)) {
            return str1;
        }
        int m = str1.length();
        int n = str2.length();
        if (m > n && str1.substring(0, n).equals(str2)) {
            return gcdOfStrings(str1.substring(n), str2);
        }
        if (n > m && str2.substring(0, m).equals(str1)) {
            return gcdOfStrings(str2.substring(m), str1);
        }
        return "";
    }
}