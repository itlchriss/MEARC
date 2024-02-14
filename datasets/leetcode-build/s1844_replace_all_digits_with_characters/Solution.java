package g1801_1900.s1844_replace_all_digits_with_characters;

// #Easy #String #2022_05_07_Time_1_ms_(70.12%)_Space_42.3_MB_(43.95%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of `s` is greater than or equal to 1.*);
	//@ requires(*The length of `s` is less than or equal to 100.*);
	//@ requires(*`s` consists only of lowercase English letters and digits.*);
	//@ requires(*`shift(s[i-1], s[i]) <= 'z'` for all odd indices `i`.*);
	//@ ensures(*The returned string is not null.*);
	//@ ensures(*The length of the returned string is the same as the length of the input string `s`.*);
	//@ ensures(*The returned string consists only of lowercase English letters.*);
    public String replaceDigits(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                sb.append(c);
            } else {
                sb.append((char) (sb.charAt(sb.length() - 1) + Character.getNumericValue(c)));
            }
        }
        return sb.toString();
    }
}