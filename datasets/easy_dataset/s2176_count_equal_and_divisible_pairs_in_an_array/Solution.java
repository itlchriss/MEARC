package g2101_2200.s2176_count_equal_and_divisible_pairs_in_an_array;

// #Easy #Array #2022_06_06_Time_4_ms_(78.29%)_Space_43.1_MB_(41.10%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 2.*);
//@ ensures(*The input integer `k` is greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the number of pairs `(i, j)` that meet the requirements.*);
//@ ensures(*The output is non-negative.*);
    public int countPairs(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}