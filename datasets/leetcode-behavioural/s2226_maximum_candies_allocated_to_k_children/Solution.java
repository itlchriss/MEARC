package g2201_2300.s2226_maximum_candies_allocated_to_k_children;

// #Medium #Array #Binary_Search #2022_06_08_Time_46_ms_(78.19%)_Space_57.9_MB_(94.92%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `candies` is not null.*);
//@ ensures(*The length of the input array `candies` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `candies` is a positive integer.*);
//@ ensures(*The input integer `k` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the maximum number of candies each child can get.*);
//@ ensures(*Each child receives the same number of candies.*);
//@ ensures(*Each child can take at most one pile of candies.*);
//@ ensures(*Some piles of candies may go unused.*);
    public int maximumCandies(int[] candies, long k) {
        int max = Integer.MIN_VALUE;
        long sum = 0;
        for (int num : candies) {
            max = Math.max(max, num);
            sum += num;
        }
        if (sum < k) {
            return 0;
        }
        int left = 1;
        int right = max;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canBeDistributed(mid, candies, k)) {
                if (!canBeDistributed(mid + 1, candies, k)) {
                    return mid;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean canBeDistributed(int num, int[] candies, long k) {
        for (int i = 0; i < candies.length && k > 0; ++i) {
            k -= candies[i] / num;
        }
        return k <= 0;
    }
}