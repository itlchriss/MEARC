package g2901_3000.s2958_length_of_longest_subarray_with_at_most_k_frequency;

// #Medium #Array #Hash_Table #Sliding_Window
// #2024_01_15_Time_28_ms_(100.00%)_Space_168.1_MB_(5.02%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The value of `k` is greater than or equal to 1.*);
//@ ensures(*The value of `k` is less than or equal to the length of the input array `nums`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the length of the longest good subarray of `nums`.*);
//@ ensures(*The output is greater than or equal to 0.*);
//@ ensures(*The output is less than or equal to the length of the input array `nums`.*);
    public int maxSubarrayLength(int[] nums, int k) {
        int m1 = Integer.MIN_VALUE;
        int m2 = Integer.MAX_VALUE;
        for (int num : nums) {
            m1 = Math.max(m1, num);
            m2 = Math.min(m2, num);
        }
        int max = 0;
        int[] f = new int[m1 - m2 + 1];
        int l = 0;
        int r = 0;
        while (r < nums.length) {
            f[nums[r] - m2]++;
            while (count(f, nums[r] - m2) > k) {
                f[nums[l] - m2]--;
                l++;
            }
            max = Math.max(max, r - l + 1);
            r++;
        }
        return max;
    }

    private int count(int[] f, int n) {
        return f[n];
    }
}