package g1601_1700.s1695_maximum_erasure_value;

// #Medium #Array #Hash_Table #Sliding_Window #2022_04_13_Time_5_ms_(99.82%)_Space_53.3_MB_(92.02%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are positive integers.*);
	//@ requires(*The maximum value of the elements in the input array `nums` is less than or equal to 10^4.*);
	//@ ensures(*The output is an integer representing the maximum score.*);
	//@ ensures(*The maximum score is the sum of the elements in the subarray.*);
	//@ ensures(*The subarray contains unique elements.*);
	//@ ensures(*The subarray is a contiguous subsequence of the input array `nums`.*);
	//@ ensures(*The subarray is erased from the input array `nums`.*);
	//@ ensures(*The maximum score is obtained by erasing exactly one subarray.*);
    public int maximumUniqueSubarray(int[] nums) {
        int ans = 0;
        int sum = 0;
        boolean[] seen = new boolean[10001];
        int j = 0;
        for (int num : nums) {
            while (seen[num]) {
                seen[nums[j]] = false;
                sum -= nums[j++];
            }
            seen[num] = true;
            sum += num;
            ans = Math.max(sum, ans);
        }
        return ans;
    }
}