package g2501_2600.s2515_shortest_distance_to_target_string_in_a_circular_array;

// #Easy #Array #String #2023_03_21_Time_1_ms_(62.21%)_Space_42.6_MB_(84.53%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `words` is not null.*);
//@ ensures(*The input array `words` is not empty.*);
//@ ensures(*The input string `target` is not null.*);
//@ ensures(*The input string `target` is not empty.*);
//@ ensures(*The input integer `startIndex` is a valid index within the range of the `words` array.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned integer is the shortest distance needed to reach the string `target`.*);
//@ ensures(*If the string `target` does not exist in `words`, the method returns -1.*);
    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        if (words[startIndex].equals(target)) {
            return 0;
        }
        int ld = -1;
        int rd;
        int ans = Integer.MAX_VALUE;
        for (int i = (startIndex + 1) % n; i != startIndex; i = (i + 1) % n) {
            if (words[i].equals(target)) {
                ld = i > startIndex ? startIndex + (n - i) : startIndex - i;
                rd = i > startIndex ? i - startIndex : n - startIndex + i;
                ans = Math.min(ans, Math.min(ld, rd));
            }
        }
        if (ld == -1) {
            return -1;
        }
        return ans;
    }
}