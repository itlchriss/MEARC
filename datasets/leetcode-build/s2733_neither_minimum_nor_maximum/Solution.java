package g2701_2800.s2733_neither_minimum_nor_maximum;

// #Easy #Array #Sorting #2023_09_22_Time_4_ms_(99.76%)_Space_43.9_MB_(27.58%)

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The input array `nums` must contain at least one element.*);
	//@ requires(*All elements in the input array `nums` must be distinct positive integers.*);
	//@ ensures(*The method should return an integer that is neither the minimum nor the maximum value in the input array `nums`.*);
	//@ ensures(*If there is no such number, the method should return -1.*);
    public int findNonMinOrMax(int[] nums) {
        int mn = 999;
        int mx = -1;
        for (int num : nums) {
            mn = Math.min(num, mn);
            mx = Math.max(num, mx);
        }
        for (int num : nums) {
            if (num != mn && num != mx) {
                return num;
            }
        }
        return -1;
    }
}