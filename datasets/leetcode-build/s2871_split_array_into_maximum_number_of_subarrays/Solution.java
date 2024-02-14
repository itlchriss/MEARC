package g2801_2900.s2871_split_array_into_maximum_number_of_subarrays;

// #Medium #Array #Greedy #Bit_Manipulation #2023_12_21_Time_3_ms_(100.00%)_Space_59.7_MB_(5.43%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*All elements in the input array `nums` are non-negative integers.*);
	//@ requires(*The input array `nums` does not contain any null elements.*);
	//@ ensures(*The output is an integer representing the maximum number of subarrays that satisfy the given conditions.*);
	//@ ensures(*The output is greater than or equal to 1.*);
	//@ ensures(*The output is less than or equal to the length of the input array `nums`.*);
	//@ ensures(*The output is the maximum possible number of subarrays that can be obtained by splitting the input array `nums` while satisfying the given conditions.*);
	//@ ensures(*The sum of scores of the subarrays obtained by splitting the input array `nums` is the minimum possible score.*);
    public int maxSubarrays(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int andMax = nums[0];
        int count = 0;
        int currAnd = nums[0];
        int sum = 0;
        for (int n : nums) {
            andMax &= n;
        }
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            if (currAnd <= andMax) {
                count++;
                sum += currAnd;
                currAnd = n;
            }
            currAnd &= n;
        }
        if (currAnd <= andMax) {
            count++;
            sum += currAnd;
        }
        return sum <= andMax ? count : 1;
    }
}