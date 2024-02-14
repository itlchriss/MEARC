package g0301_0400.s0330_patching_array;

// #Hard #Array #Greedy #2022_07_09_Time_1_ms_(60.00%)_Space_44.3_MB_(27.06%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is sorted in ascending order.*);
	//@ requires(*The length of the input array `nums` is between 1 and 1000 (inclusive).*);
	//@ requires(*Each element in the input array `nums` is between 1 and 10^4 (inclusive).*);
	//@ requires(*The input integer `n` is between 1 and 2^31 - 1 (inclusive).*);
	//@ ensures(*The output is an integer representing the minimum number of patches required.*);
	//@ ensures(*The output is greater than or equal to 0.*);
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