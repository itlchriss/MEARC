package g1901_2000.s1920_build_array_from_permutation;

// #Easy #Array #Simulation #2022_05_14_Time_1_ms_(94.23%)_Space_54.1_MB_(13.18%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are distinct.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of 0 to `nums.length - 1`.*);
	//@ ensures(*The output array `ans` is not null.*);
	//@ ensures(*The length of the output array `ans` is the same as the length of the input array `nums`.*);
	//@ ensures(*The elements in the output array `ans` are integers.*);
	//@ ensures(*For each index `i` from 0 to `nums.length - 1`, `ans[i]` is equal to `nums[nums[i]]`.*);
    public int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }
}