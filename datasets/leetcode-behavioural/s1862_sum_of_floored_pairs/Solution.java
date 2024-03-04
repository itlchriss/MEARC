package g1801_1900.s1862_sum_of_floored_pairs;

// #Hard #Array #Math #Binary_Search #Prefix_Sum
// #2022_05_08_Time_115_ms_(70.91%)_Space_57.2_MB_(81.82%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `nums` is an integer.*);
//@ ensures(*Each element in the input array `nums` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `nums` is less than or equal to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer.*);
//@ ensures(*The returned integer is the sum of `floor(nums[i] / nums[j])` for all pairs of indices `0 <= i, j < nums.length` in the array.*);
//@ ensures(*The returned integer is modulo 10^9 + 7.*);
    public int sumOfFlooredPairs(int[] nums) {
        long mod = 1000000007;
        Arrays.sort(nums);
        int max = nums[nums.length - 1];
        int[] counts = new int[max + 1];
        long[] qnts = new long[max + 1];
        for (int k : nums) {
            counts[k]++;
        }
        for (int i = 1; i < max + 1; i++) {
            if (counts[i] == 0) {
                continue;
            }
            int j = i;
            while (j <= max) {
                qnts[j] += counts[i];
                j = j + i;
            }
        }
        for (int i = 1; i < max + 1; i++) {
            qnts[i] = (qnts[i] + qnts[i - 1]) % mod;
        }
        long sum = 0;
        for (int k : nums) {
            sum = (sum + qnts[k]) % mod;
        }
        return (int) sum;
    }
}