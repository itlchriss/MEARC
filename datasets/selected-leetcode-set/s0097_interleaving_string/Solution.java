package g0001_0100.s0097_interleaving_string;

// #Medium #String #Dynamic_Programming #2022_06_21_Time_2_ms_(88.01%)_Space_42.1_MB_(73.59%)

public class Solution {
//@ ensures(*The string parameter `s3` is formed by an interleaving of the string parameters `s1` and `s2`.*);
//@ ensures(*The length of the string parameter `s1` is less than or equal to 100.*);
//@ ensures(*The length of the string parameter `s2` is less than or equal to 100.*);
//@ ensures(*The length of the string parameter `s3` is less than or equal to 200.*);
//@ ensures(*All string parameters `s1`, `s2`, and `s3` consist of lowercase English letters.*);
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != (s1.length() + s2.length())) {
            return false;
        }
        Boolean[][] cache = new Boolean[s1.length() + 1][s2.length() + 1];
        return isInterleave(s1, s2, s3, 0, 0, 0, cache);
    }

    public boolean isInterleave(
            String s1, String s2, String s3, int i1, int i2, int i3, Boolean[][] cache) {
        if (cache[i1][i2] != null) {
            return cache[i1][i2];
        }
        if (i1 == s1.length() && i2 == s2.length() && i3 == s3.length()) {
            return true;
        }
        boolean result = false;
        if (i1 < s1.length() && s1.charAt(i1) == s3.charAt(i3)) {
            result = isInterleave(s1, s2, s3, i1 + 1, i2, i3 + 1, cache);
        }
        if (i2 < s2.length() && s2.charAt(i2) == s3.charAt(i3)) {
            result = result || isInterleave(s1, s2, s3, i1, i2 + 1, i3 + 1, cache);
        }
        cache[i1][i2] = result;
        return result;
    }
}