package g1301_1400.s1374_generate_a_string_with_characters_that_have_odd_counts;

// #Easy #String #2022_03_22_Time_1_ms_(90.52%)_Space_39.4_MB_(87.85%)

public class Solution {
	//@ requires(*The input `n` is an integer.*);
	//@ requires(*`n` is greater than or equal to 1 and less than or equal to 500.*);
	//@ ensures(*The output is a string.*);
	//@ ensures(*The length of the output string is equal to `n`.*);
	//@ ensures(*Each character in the output string occurs an odd number of times.*);
	//@ ensures(*The output string contains only lowercase English letters.*);
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if (n > 1 && n % 2 == 0) {
            while (n-- > 1) {
                sb.append("a");
            }
        } else if (n > 1) {
            while (n-- > 2) {
                sb.append("a");
            }
            sb.append("b");
        }
        sb.append("z");
        return sb.toString();
    }
}