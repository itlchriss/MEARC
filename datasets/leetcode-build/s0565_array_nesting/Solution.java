package g0501_0600.s0565_array_nesting;

// #Medium #Array #Depth_First_Search #2022_08_10_Time_5_ms_(95.44%)_Space_75.8_MB_(65.97%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are unique.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range `[0, n - 1]`, where `n` is the length of the input array `nums`.*);
	//@ ensures(*The output is an integer representing the longest length of a set `s[k]`.*);
	//@ ensures(*The output is greater than or equal to 1.*);
	//@ ensures(*The output is less than or equal to the length of the input array `nums`.*);
    public int arrayNesting(int[] nums) {
        int index;
        int value;
        int maxLen = 0;
        int len;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                index = i;
                len = 0;
                while (nums[index] != -1) {
                    value = nums[index];
                    nums[index] = -1;
                    index = value;
                    len++;
                }
                maxLen = Math.max(len, maxLen);
            }
        }
        return maxLen;
    }
}