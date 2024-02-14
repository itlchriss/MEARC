package g0701_0800.s0709_to_lower_case;

// #Easy #String #Programming_Skills_I_Day_9_String
// #2022_03_23_Time_1_ms_(71.74%)_Space_42_MB_(52.94%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is between 1 and 100 (inclusive).*);
	//@ requires(*The input string `s` consists of printable ASCII characters.*);
	//@ ensures(*The output string is not null.*);
	//@ ensures(*The output string has the same length as the input string.*);
	//@ ensures(*The output string is formed by replacing every uppercase letter in the input string with the corresponding lowercase letter.*);
	//@ ensures(*The output string contains only lowercase letters and the same non-alphabetic characters as the input string.*);
    public String toLowerCase(String s) {
        char[] c = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (c[i] <= 'Z' && c[i] >= 'A') {
                c[i] = (char) (c[i] - 'A' + 'a');
            }
        }
        return new String(c);
    }
}