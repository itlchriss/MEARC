package g1901_2000.s1903_largest_odd_number_in_string;

// #Easy #String #Math #Greedy #2022_05_11_Time_6_ms_(23.18%)_Space_43.2_MB_(81.76%)

public class Solution {
	//@ requires(*The input string `num` is not null.*);
	//@ requires(*The input string `num` is not empty.*);
	//@ requires(*The input string `num` consists only of digits.*);
	//@ requires(*The input string `num` does not contain any leading zeros.*);
	//@ ensures(*The output string is either the largest-valued odd integer (as a string) that is a non-empty substring of `num`, or an empty string if no odd integer exists.*);
	//@ ensures(*The output string is not null.*);
    public String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if (Integer.parseInt("" + num.charAt(i)) % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}