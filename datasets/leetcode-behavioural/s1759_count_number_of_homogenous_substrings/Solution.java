package g1701_1800.s1759_count_number_of_homogenous_substrings;

// #Medium #String #Math #2022_04_30_Time_19_ms_(42.40%)_Space_51.3_MB_(28.80%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is greater than or equal to 1.*);
//@ ensures(*The input string `s` consists of lowercase letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned value is the number of homogenous substrings of the input string `s`.*);
//@ ensures(*The returned value is modulo 10^9 + 7.*);
    public int countHomogenous(String s) {
        int total = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                count = 1;
            }
            total = (total + count) % 1000000007;
        }
        return total;
    }
}