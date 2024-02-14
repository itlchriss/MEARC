package g0401_0500.s0482_license_key_formatting;

// #Easy #String #2022_07_20_Time_8_ms_(96.52%)_Space_45.4_MB_(58.75%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` consists of only alphanumeric characters and dashes.*);
	//@ requires(*The input integer `k` is greater than or equal to 1.*);
	//@ ensures(*The output string is not null.*);
	//@ ensures(*The output string consists of only alphanumeric characters and dashes.*);
	//@ ensures(*The output string is formatted such that each group contains exactly `k` characters, except for the first group which could be shorter but still must contain at least one character.*);
	//@ ensures(*All lowercase letters in the output string are converted to uppercase.*);
	//@ ensures(*There is a dash inserted between two groups in the output string.*);
	//@ ensures(*The output string is the reformatted license key.*);
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        int occ = 0;
        for (char c : s.toCharArray()) {
            if (c == '-') {
                continue;
            }
            cnt++;
        }
        int l = cnt % k;
        for (char c : s.toCharArray()) {
            if (c == '-') {
                continue;
            }
            if (occ == k) {
                occ = 0;
                sb.append('-');
            } else if (occ == l && l != 0) {
                l = 0;
                occ = 0;
                sb.append('-');
            }
            sb.append(Character.toUpperCase(c));
            occ++;
        }
        return sb.toString();
    }
}