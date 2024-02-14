package g1501_1600.s1567_maximum_length_of_subarray_with_positive_product;

// #Medium #Array #Dynamic_Programming #Greedy #Dynamic_Programming_I_Day_6
// #2022_04_11_Time_4_ms_(80.86%)_Space_77.8_MB_(65.54%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ ensures(*The returned value is an integer.*);
	//@ ensures(*The returned value represents the maximum length of a subarray with a positive product.*);
    public int getMaxLen(int[] nums) {
        int posLen = 0;
        int negLen = 0;
        int res = 0;
        for (int num : nums) {
            if (num == 0) {
                posLen = 0;
                negLen = 0;
            } else if (num > 0) {
                posLen++;
                negLen = negLen == 0 ? 0 : negLen + 1;
            } else {
                int temp = posLen;
                posLen = negLen == 0 ? 0 : negLen + 1;
                negLen = temp + 1;
            }
            res = Math.max(res, posLen);
        }
        return res;
    }
}