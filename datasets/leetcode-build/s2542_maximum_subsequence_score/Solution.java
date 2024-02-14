package g2501_2600.s2542_maximum_subsequence_score;

// #Medium #Array #Sorting #Greedy #Heap_Priority_Queue
// #2023_05_09_Time_94_ms_(84.75%)_Space_56.5_MB_(81.92%)

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    private static class PairInfo {
        int val1;
        int val2;
	//@ requires(*The length of `nums1` and `nums2` must be equal.*);
	//@ requires(*The length of `nums1` and `nums2` must be greater than or equal to `k`.*);
	//@ requires(*The elements of `nums1` and `nums2` must be non-negative integers.*);
	//@ ensures(*The method returns an integer representing the maximum possible score.*);
	//@ ensures(*The maximum possible score is calculated by choosing a subsequence of indices from `nums1` of length `k` and calculating the score using the given formula.*);
	//@ ensures(*The maximum possible score is the highest score among all possible subsequences.*);

        public PairInfo(int val1, int val2) {
            this.val1 = val1;
            this.val2 = val2;
        }
    }

    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        PairInfo[] nums = new PairInfo[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = new PairInfo(nums1[i], nums2[i]);
        }
        Arrays.sort(
                nums,
                (a, b) -> {
                    if (a.val2 == b.val2) {
                        return a.val1 - b.val1;
                    }
                    return a.val2 - b.val2;
                });
        long sum = 0;
        long ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = n - 1; i >= 0; --i) {
            int minVal = nums[i].val2;
            while (pq.size() > k - 1) {
                sum -= pq.poll();
            }
            sum += nums[i].val1;
            pq.add(nums[i].val1);
            if (pq.size() == k) {
                ans = Math.max(ans, sum * minVal);
            }
        }
        return ans;
    }
}