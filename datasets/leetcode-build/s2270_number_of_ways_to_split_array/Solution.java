package g2201_2300.s2270_number_of_ways_to_split_array;

// #Medium #Array #Prefix_Sum #2022_06_15_Time_4_ms_(77.55%)_Space_91.9_MB_(18.27%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is at least 2.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of -10^5 to 10^5.*);
	//@ ensures(*The return value is an integer representing the number of valid splits in the input array `nums`.*);
	//@ ensures(*The return value is greater than or equal to 0.*);
    public int waysToSplitArray(int[] nums) {
        long leftSum = 0;
        long rightSum = 0;
        for (int i : nums) {
            rightSum += i;
        }
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            rightSum -= nums[i];
            leftSum += nums[i];
            if (leftSum >= rightSum) {
                count++;
            }
        }
        return count;
    }
}