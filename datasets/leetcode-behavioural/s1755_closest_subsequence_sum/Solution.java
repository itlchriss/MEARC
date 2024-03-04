package g1701_1800.s1755_closest_subsequence_sum;

// #Hard #Array #Dynamic_Programming #Two_Pointers #Bit_Manipulation #Bitmask
// #2022_04_30_Time_383_ms_(87.60%)_Space_65.5_MB_(78.51%)

import java.util.Arrays;

@SuppressWarnings("java:S2184")
public class Solution {
    private int idx;
    private int sum;
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` must not be null.*);
//@ ensures(*The length of the input array `nums` must be greater than or equal to 1.*);
//@ ensures(*The length of the input array `nums` must be less than or equal to 40.*);
//@ ensures(*Each element in the input array `nums` must be an integer.*);
//@ ensures(*Each element in the input array `nums` must be greater than or equal to -10^7.*);
//@ ensures(*Each element in the input array `nums` must be less than or equal to 10^7.*);
//@ ensures(*The input integer `goal` must be greater than or equal to -10^9.*);
//@ ensures(*The input integer `goal` must be less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output integer must be the minimum possible value of `abs(sum - goal)`, where `sum` is the sum of the elements in the chosen subsequence of `nums`.*);
//@ ensures(*The output integer must be greater than or equal to 0.*);

    public int minAbsDifference(int[] nums, int goal) {
        int n = nums.length;
        int nFirst = (int) Math.pow(2, (double) n / 2);
        int nSecond = (int) Math.pow(2, n - n / 2);
        int[] first = new int[nFirst];
        int[] second = new int[nSecond];
        helper(nums, first, 0, n / 2 - 1);
        idx = sum = 0;
        helper(nums, second, n / 2, n - 1);
        Arrays.sort(first);
        Arrays.sort(second);
        int low = 0;
        int high = nSecond - 1;
        int ans = Integer.MAX_VALUE;
        while (low < nFirst && high >= 0) {
            int localSum = first[low] + second[high];
            ans = Math.min(ans, Math.abs(localSum - goal));
            if (ans == 0) {
                break;
            }
            if (localSum < goal) {
                low++;
            } else {
                high--;
            }
        }
        return ans;
    }

    private void helper(int[] nums, int[] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            sum += nums[i];
            arr[idx++] = sum;
            helper(nums, arr, i + 1, end);
            sum -= nums[i];
        }
    }
}