package g1201_1300.s1209_remove_all_adjacent_duplicates_in_string_ii;

// #Medium #String #Stack #2022_03_09_Time_15_ms_(88.34%)_Space_46.8_MB_(71.87%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` is not empty.*);
	//@ requires(*3. The input integer `k` is greater than or equal to 2.*);
	//@ requires(*4. The input integer `k` is less than or equal to 10,000.*);
	//@ requires(*5. The input string `s` only contains lower case English letters.*);
	//@ ensures(*1. The output string is not null.*);
	//@ ensures(*2. The output string is not empty.*);
	//@ ensures(*3. The output string is unique, meaning there are no adjacent duplicate letters left after all duplicate removals have been made.*);
    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int dupCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (sb.length() != 0 && sb.charAt(sb.length() - 1) == s.charAt(i)) {
                dupCount++;
            } else {
                dupCount = 1;
            }
            sb.append(s.charAt(i));
            if (dupCount == k) {
                sb.setLength(sb.length() - k);
                if (i + 1 < s.length()) {
                    dupCount = 0;
                    for (int j = sb.length() - 1; j >= 0; j--) {
                        if (sb.charAt(j) == s.charAt(i + 1)) {
                            dupCount++;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return sb.toString();
    }
}