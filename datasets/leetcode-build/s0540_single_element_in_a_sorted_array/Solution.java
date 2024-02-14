package g0501_0600.s0540_single_element_in_a_sorted_array;

// #Medium #Array #Binary_Search #Binary_Search_II_Day_9
// #2022_08_02_Time_0_ms_(100.00%)_Space_60.5_MB_(44.30%)

public class Solution {
	//@ requires(*1. The input array `nums` is sorted in non-decreasing order.*);
	//@ requires(*2. The length of the input array `nums` is at least 1 and at most 10^5.*);
	//@ requires(*3. The elements in the input array `nums` are integers.*);
	//@ requires(*4. Each element in the input array `nums` appears exactly twice, except for one element which appears exactly once.*);
	//@ ensures(*1. The method returns an integer, which is the single element that appears only once in the input array `nums`.*);
	//@ ensures(*2. The method runs in O(log n) time complexity, where n is the length of the input array `nums`.*);
	//@ ensures(*3. The method uses O(1) space complexity.*);
    public int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (mid + 1 < nums.length
                    && nums[mid] != nums[mid + 1]
                    && mid - 1 >= 0
                    && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            } else if (mid + 1 < nums.length && nums[mid] == nums[mid + 1] && mid % 2 == 0) {
                start = mid + 1;
            } else if (mid - 1 >= 0 && nums[mid] == nums[mid - 1] && mid % 2 == 1) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return nums[start];
    }
}