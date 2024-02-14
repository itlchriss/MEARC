package g0301_0400.s0327_count_of_range_sum;

// #Hard #Array #Binary_Search #Ordered_Set #Divide_and_Conquer #Segment_Tree #Binary_Indexed_Tree
// #Merge_Sort #2022_07_09_Time_111_ms_(66.02%)_Space_134_MB_(22.66%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The values in the input array `nums` are within the range of -2^31 to 2^31 - 1.*);
	//@ requires(*The lower bound `lower` is within the range of -10^5 to 10^5.*);
	//@ requires(*The upper bound `upper` is within the range of -10^5 to 10^5.*);
	//@ requires(*The lower bound `lower` is less than or equal to the upper bound `upper`.*);
	//@ ensures(*The method returns an integer representing the number of range sums that lie within the inclusive range `[lower, upper]`.*);
	//@ ensures(*The method does not modify the input array `nums`.*);
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1) {
            return 0;
        }
        int mid = (start + end) / 2;
        int count =
                countWhileMergeSort(sums, start, mid, lower, upper)
                        + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid;
        int k = mid;
        int t = mid;
        long[] cache = new long[end - start];
        int r = 0;
        for (int i = start; i < mid; i++) {
            while (k < end && sums[k] - sums[i] < lower) {
                k++;
            }
            while (j < end && sums[j] - sums[i] <= upper) {
                j++;
            }
            while (t < end && sums[t] < sums[i]) {
                cache[r++] = sums[t++];
            }
            cache[r] = sums[i];
            count += j - k;
            r++;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }
}