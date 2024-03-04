package g0801_0900.s0880_decoded_string_at_index;

// #Medium #String #Stack #2022_03_28_Time_0_ms_(100.00%)_Space_42.1_MB_(34.90%)

@SuppressWarnings("java:S3518")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is between 2 and 100.*);
//@ ensures(*`s` consists of lowercase English letters and digits 2 through 9.*);
//@ ensures(*`s` starts with a letter.*);
//@ ensures(*The input integer `k` is between 1 and 10^9.*);
//@ ensures(*`k` is less than or equal to the length of the decoded string.*);
//@ ensures(*The decoded string has less than 2^63 letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a string.*);
//@ ensures(*The returned string is the kth letter (1-indexed) in the decoded string.*);
    public String decodeAtIndex(String s, int k) {
        long length = 0;
        for (char c : s.toCharArray()) {
            if (c >= 48 && c <= 57) {
                length *= c - '0';
            } else {
                ++length;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            k %= length;
            if (c >= 48 && c <= 57) {
                length /= c - '0';
            } else if (k == 0) {
                return String.valueOf(c);
            } else {
                --length;
            }
        }
        return "";
    }
}