package g1501_1600.s1540_can_convert_string_in_k_moves;

// #Medium #String #Hash_Table #2022_04_10_Time_10_ms_(100.00%)_Space_54.6_MB_(53.16%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of string `s` is greater than or equal to the length of string `t`.*);
//@ ensures(*The length of string `s` and string `t` is less than or equal to 10^5.*);
//@ ensures(*The value of `k` is between 0 and 10^9.*);
//@ ensures(*String `s` and string `t` contain only lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if it is possible to convert string `s` into string `t` in no more than `k` moves.*);
//@ ensures(*The method returns `false` if it is not possible to convert string `s` into string `t` in no more than `k` moves.*);
    public boolean canConvertString(String s, String t, int k) {
        int len1 = s.length();
        int len2 = t.length();
        if (len1 != len2) {
            return false;
        }
        if (s.equals(t)) {
            return true;
        }
        int[] freq = new int[26];
        int multiple = k / 26;
        for (int i = 0; i < 26; i++) {
            freq[i] = multiple;
        }
        int rem = k % 26;
        for (int i = 1; i <= rem; i++) {
            freq[i]++;
        }
        int movesRemaining = k;
        for (int i = 0; i < len1; i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            if (ch1 == ch2) {
                movesRemaining--;
                continue;
            }
            int diff = (ch2 - ch1 + 26) % 26;
            if (freq[diff] > 0) {
                freq[diff]--;
                movesRemaining--;
            } else {
                return false;
            }
        }
        return movesRemaining >= 0;
    }
}