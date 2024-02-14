package g0201_0300.s0215_kth_largest_element_in_an_array;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Sorting #Heap_Priority_Queue
// #Divide_and_Conquer #Quickselect #Data_Structure_II_Day_20_Heap_Priority_Queue
// #Big_O_Time_O(n*log(n))_Space_O(log(n)) #2022_07_02_Time_5_ms_(70.82%)_Space_45.1_MB_(24.69%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be greater than or equal to `k`.*);
	//@ requires(*The value of `k` must be between 1 and the length of the input array `nums`, inclusive.*);
	//@ requires(*The input array `nums` must contain integers.*);
	//@ ensures(*The method should return an integer, which is the `k`th largest element in the input array `nums`.*);
	//@ ensures(*The returned integer should be one of the elements in the input array `nums`.*);
	//@ ensures(*The returned integer should be the `k`th largest element in the sorted order of the input array `nums`.*);
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n - k];
    }
}