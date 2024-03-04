package g1601_1700.s1653_minimum_deletions_to_make_string_balanced;

// #Medium #String #Dynamic_Programming #Stack
// #2022_04_22_Time_26_ms_(90.44%)_Space_64.3_MB_(37.05%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is greater than or equal to 1 and less than or equal to 10^- The characters in `s` are either 'a' or 'b'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of deletions needed to make `s` balanced.*);
    public int minimumDeletions(String s) {
        int a = 0;
        int b = 0;
        for (char ch : s.toCharArray()) {
            if (ch == 'a') {
                a++;
            } else {
                b = Math.max(a, b) + 1;
            }
        }

        return s.length() - Math.max(a, b);
    }
}