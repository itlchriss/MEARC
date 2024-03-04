package g2801_2900.s2873_maximum_value_of_an_ordered_triplet_i;

// #Easy #Array #2023_12_21_Time_0_ms_(100.00%)_Space_42_MB_(10.64%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be at least 3.*);
//@ ensures(*The elements of the input array `nums` must be integers.*);
//@ ensures(*The elements of the input array `nums` must be between 1 and 10^6 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the maximum value over all triplets of indices.*);
//@ ensures(*If all triplets of indices have a negative value, the method returns 0.*);
    public long maximumTripletValue(int[] nums) {
        final int n = nums.length;
        final int[] iNumMaxs = new int[n];
        int prev = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= prev) {
                iNumMaxs[i] = prev;
            } else {
                prev = iNumMaxs[i] = nums[i];
            }
        }
        long result = 0;
        int kNumMax = nums[n - 1];
        for (int j = n - 2; j > 0; j--) {
            result = Math.max(result, (long) (iNumMaxs[j - 1] - nums[j]) * kNumMax);
            if (nums[j] > kNumMax) {
                kNumMax = nums[j];
            }
        }
        return result;
    }
}