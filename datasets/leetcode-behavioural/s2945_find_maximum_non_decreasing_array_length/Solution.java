package g2901_3000.s2945_find_maximum_non_decreasing_array_length;

// #Hard #Array #Dynamic_Programming #Binary_Search #Stack #Monotonic_Stack #Queue #Monotonic_Queue
// #2023_12_26_Time_11_ms_(98.10%)_Space_60_MB_(40.30%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are less than or equal to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer.*);
//@ ensures(*The returned value is greater than or equal to 1.*);
//@ ensures(*The returned value is less than or equal to the length of the input array `nums`.*);
//@ ensures(*The returned value represents the maximum length of a non-decreasing array that can be made after applying operations.*);
    public int findMaximumLength(int[] nums) {
        int n = nums.length;
        int[] que = new int[n + 1];
        int write = 0;
        int read = 0;
        long[] prefixSum = new long[n + 1];
        long[] sums = new long[n + 1];
        int[] count = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
            while (read < write && prefixSum[i] >= sums[read + 1]) {
                read++;
            }
            int j = que[read];
            long subarraySum = prefixSum[i] - prefixSum[j];
            count[i] = count[j] + 1;
            long sum = prefixSum[i] + subarraySum;
            while (sum <= sums[write]) {
                write--;
            }
            que[++write] = i;
            sums[write] = sum;
        }
        return count[n];
    }
}