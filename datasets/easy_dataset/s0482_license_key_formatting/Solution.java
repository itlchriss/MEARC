package g0401_0500.s0482_license_key_formatting;

// #Easy #String #2022_07_20_Time_8_ms_(96.52%)_Space_45.4_MB_(58.75%)

public class Solution {
//@ ensures(*The string parameter `s` must not be null.*);
//@ ensures(*The integer parameter `k` must be greater than 0.*);
//@ ensures(*The string result is the reformatted license key where each group contains exactly `k` characters, except for the first group which could be shorter but must contain at least one character.*);
//@ ensures(*All lowercase letters in the reformatted license key must be converted to uppercase.*);
//@ ensures(*There must be a dash inserted between two groups in the reformatted license key.*);
//@ ensures(*Any extra dashes in the reformatted license key must be removed.*);
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