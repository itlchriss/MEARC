package g1001_1100.s1016_binary_string_with_substrings_representing_1_to_n;

// #Medium #String #2022_02_25_Time_0_ms_(100.00%)_Space_42.1_MB_(23.40%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is between 1 and - Each character in `s` is either '0' or '1'.*);
//@ ensures(*The input integer `n` is a positive integer.*);
//@ ensures(*`n` is less than or equal to 10^*);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether the binary representation of all integers in the range [1, n] are substrings of `s`.*);
    public boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            String str = Integer.toBinaryString(i);
            if (!s.contains(str)) {
                return false;
            }
        }
        return true;
    }
}