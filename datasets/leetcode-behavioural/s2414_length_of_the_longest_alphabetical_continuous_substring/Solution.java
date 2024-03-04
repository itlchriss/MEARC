package g2401_2500.s2414_length_of_the_longest_alphabetical_continuous_substring;

// #Medium #String #2022_11_20_Time_19_ms_(74.13%)_Space_54.8_MB_(17.71%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The input string `s` is not empty.*);
//@ ensures(*The input string `s` consists of only lowercase letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the length of the longest alphabetical continuous substring.*);
//@ ensures(*The output is greater than or equal to 0.*);
//@ ensures(*The output is less than or equal to the length of the input string `s`.*);
    public int longestContinuousSubstring(String s) {
        int ans = 0;
        int cnt = 1;
        int j = 1;
        while (j < s.length()) {
            if (s.charAt(j) == s.charAt(j - 1) + 1) {
                cnt++;
            } else {
                ans = Math.max(ans, cnt);
                cnt = 1;
            }
            j++;
        }
        return Math.max(ans, cnt);
    }
}