package g2401_2500.s2499_minimum_total_cost_to_make_arrays_unequal;

// #Hard #Array #Hash_Table #Greedy #Counting #2023_02_12_Time_3_ms_(100.00%)_Space_59.5_MB_(76.40%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of `nums1` and `nums2` should be equal.*);
//@ ensures(*`nums1` and `nums2` should have a length greater than or equal to - The values in `nums1` and `nums2` should be integers.*);
//@ ensures(*The values in `nums1` and `nums2` should be between 1 and `n`, inclusive.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value should be a long integer.*);
//@ ensures(*If it is not possible to satisfy the given conditions, the method should return -- If it is possible to satisfy the given conditions, the method should return the minimum total cost.*);
    public long minimumTotalCost(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] bucket = new int[n + 1];
        int max = 0;
        int maxKey = -1;
        int totalBucket = 0;
        long cost = 0;
        for (int i = 0; i < n; i++) {
            if (nums1[i] == nums2[i]) {
                if (++bucket[nums1[i]] > max) {
                    max = bucket[nums1[i]];
                    maxKey = nums1[i];
                }
                totalBucket++;
                cost += i;
            }
        }
        int requiredBucket = 2 * max;
        if (requiredBucket > n) {
            return -1;
        }
        int lackBucket = requiredBucket - totalBucket;
        int i = 0;
        while (i < n && lackBucket > 0) {
            if (nums1[i] == maxKey || nums2[i] == maxKey || nums1[i] == nums2[i]) {
                i++;
                continue;
            }
            lackBucket--;
            cost += i;
            i++;
        }
        if (lackBucket > 0) {
            return -1;
        }
        return cost;
    }
}