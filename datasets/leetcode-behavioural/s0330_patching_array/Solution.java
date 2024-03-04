package g0301_0400.s0330_patching_array;

// #Hard #Array #Greedy #2022_07_09_Time_1_ms_(60.00%)_Space_44.3_MB_(27.06%)

public class Solution {
//@ ensures(*If the integer array parameter `nums` is empty, the integer result is the minimum number of patches required to cover the range `[1, n]`.*);
//@ ensures(*If the integer array parameter `nums` is not empty, the integer result is the minimum number of patches required to cover the range `[1, n]` after adding elements to the array.*);
    public int minPatches(int[] nums, int n) {
        int res = 0;
        long sum = 0;
        int i = 0;
        while (sum < n) {
            // required number
            long req = sum + 1;
            if (i < nums.length && nums[i] <= req) {
                sum += nums[i++];
            } else {
                sum += req;
                res++;
            }
        }
        return res;
    }
}