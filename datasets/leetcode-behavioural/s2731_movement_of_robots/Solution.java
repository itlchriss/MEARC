package g2701_2800.s2731_movement_of_robots;

// #Medium #Array #Sorting #Prefix_Sum #Brainteaser
// #2023_09_22_Time_9_ms_(100.00%)_Space_54.7_MB_(63.56%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of `nums` is at least 2.*);
//@ ensures(*The length of `nums` is equal to the length of `s`.*);
//@ ensures(*The values in `nums` are within the range of -2 * 10^9 to 2 * 10^9.*);
//@ ensures(*The value of `d` is within the range of 0 to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned value is the sum of distances between all pairs of robots `d` seconds after the command.*);
//@ ensures(*The returned value is modulo 10^9 + 7.*);
    public int sumDistance(int[] nums, String s, int d) {
        int n = nums.length;
        int mod = (int) (1e9 + 7);
        for (int i = 0; i < n; i++) {
            nums[i] += (s.charAt(i) == 'R') ? d : -d;
        }
        Arrays.sort(nums);
        long res = 0;
        for (int i = 0; i < n; i++) {
            res = (res + (1L + i + i - n) * nums[i]) % mod;
        }
        return (int) ((res + mod) % mod);
    }
}