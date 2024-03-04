package g1301_1400.s1375_number_of_times_binary_string_is_prefix_aligned;

// #Medium #Array #2022_03_22_Time_2_ms_(89.02%)_Space_64.5_MB_(21.14%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `flips` is not null.*);
//@ ensures(*The length of `flips` is equal to `n`.*);
//@ ensures(*The elements in `flips` are integers in the range `[1, n]`.*);
//@ ensures(*The binary string is initially all zeros.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of times the binary string is prefix-aligned during the flipping process.*);
    public int numTimesAllBlue(int[] flips) {
        int ans = 0;
        int max = 0;
        for (int i = 0; i < flips.length; i++) {
            max = Math.max(max, flips[i]);
            if (max == i + 1) {
                ++ans;
            }
        }
        return ans;
    }
}