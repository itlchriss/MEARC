package g2201_2300.s2239_find_closest_number_to_zero;

// #Easy #Array #2022_06_10_Time_2_ms_(84.21%)_Space_50.9_MB_(41.29%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than 0.*);
	//@ ensures(*The returned number is an element from the input array `nums`.*);
	//@ ensures(*The returned number has the value closest to 0.*);
	//@ ensures(*If there are multiple numbers with the same closest distance to 0, the returned number is the largest among them.*);
    public int findClosestNumber(int[] nums) {
        int mini = Integer.MAX_VALUE;
        int closestNum = 0;
        for (int n : nums) {
            if (mini > Math.abs(n)) {
                mini = Math.abs(n);
                closestNum = n;
            } else if (mini == Math.abs(n) && closestNum < n) {
                closestNum = n;
            }
        }
        return closestNum;
    }
}