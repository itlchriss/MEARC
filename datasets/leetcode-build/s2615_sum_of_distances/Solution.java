package g2601_2700.s2615_sum_of_distances;

// #Medium #Array #Hash_Table #Prefix_Sum #2023_08_30_Time_13_ms_(100.00%)_Space_70.4_MB_(43.45%)

import java.util.HashMap;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are non-negative integers.*);
	//@ ensures(*The output array `arr` is not null.*);
	//@ ensures(*The length of the output array `arr` is equal to the length of the input array `nums`.*);
	//@ ensures(*The elements in the output array `arr` are non-negative integers.*);
	//@ ensures(*For each index `i` in the output array `arr`, if there exists an index `j` such that `nums[j] == nums[i]` and `j != i`, then `arr[i]` is equal to the sum of the absolute differences between `i` and all indices `j` where `nums[j] == nums[i]`. Otherwise, `arr[i]` is equal to 0.*);
    public long[] distance(int[] nums) {
        HashMap<Integer, long[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long[] temp = map.computeIfAbsent(nums[i], k -> new long[4]);
            temp[0] += i;
            temp[2]++;
        }
        long[] ans = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            long[] temp = map.get(nums[i]);
            ans[i] += i * temp[3] - temp[1];
            temp[1] += i;
            temp[3]++;
            ans[i] += temp[0] - temp[1] - i * (temp[2] - temp[3]);
        }
        return ans;
    }
}