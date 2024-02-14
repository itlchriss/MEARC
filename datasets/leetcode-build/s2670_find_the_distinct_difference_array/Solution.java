package g2601_2700.s2670_find_the_distinct_difference_array;

// #Easy #Array #Hash_Table #2023_09_10_Time_5_ms_(92.12%)_Space_44.5_MB_(66.07%)

import java.util.HashSet;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of 1 to 50.*);
	//@ ensures(*The output array `diff` is not null.*);
	//@ ensures(*The length of the output array `diff` is equal to the length of the input array `nums`.*);
	//@ ensures(*The elements in the output array `diff` are integers.*);
	//@ ensures(*The elements in the output array `diff` are calculated correctly according to the given formula.*);
    public int[] distinctDifferenceArray(int[] nums) {
        int n = nums.length;
        HashSet<Integer> prefixSet = new HashSet<>();
        HashSet<Integer> suffixSet = new HashSet<>();
        int[] preList = new int[n];
        int[] sufList = new int[n];
        int[] ans = new int[n];
        for (int i = 0; i < nums.length; i++) {
            prefixSet.add(nums[i]);
            suffixSet.add(nums[n - 1 - i]);
            preList[i] = prefixSet.size();
            sufList[n - 1 - i] = suffixSet.size();
        }
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                ans[i] = preList[i];
            } else {
                ans[i] = preList[i] - sufList[i + 1];
            }
        }
        return ans;
    }
}