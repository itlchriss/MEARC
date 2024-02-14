package g0501_0600.s0581_shortest_unsorted_continuous_subarray;

// #Medium #Array #Sorting #Greedy #Two_Pointers #Stack #Monotonic_Stack
// #2022_08_10_Time_2_ms_(84.72%)_Space_55_MB_(6.90%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of -10^5 to 10^*);
	//@ ensures(*The method returns an integer representing the length of the shortest continuous subarray that needs to be sorted in ascending order.*);
	//@ ensures(*If there is no subarray that needs to be sorted, the method returns 0.*);
    public int findUnsortedSubarray(int[] nums) {
        int end = -2;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                end = i;
            }
        }
        int start = -1;
        int min = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                start = i;
            }
        }
        return end - start + 1;
    }
}