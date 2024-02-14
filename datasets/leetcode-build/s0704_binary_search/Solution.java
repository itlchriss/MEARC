package g0701_0800.s0704_binary_search;

// #Easy #Array #Binary_Search #Algorithm_I_Day_1_Binary_Search #Binary_Search_I_Day_1
// #Level_1_Day_7_Binary_Search #Udemy_Binary_Search
// #2022_03_23_Time_0_ms_(100.00%)_Space_54.8_MB_(20.10%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is sorted in ascending order.*);
	//@ requires(*The input array `nums` contains unique integers.*);
	//@ requires(*The input array `nums` has a length greater than or equal to 1.*);
	//@ requires(*The input integer `target` is within the range of integers in `nums`.*);
	//@ ensures(*If the `target` exists in `nums`, the method returns the index of the `target` in `nums`.*);
	//@ ensures(*If the `target` does not exist in `nums`, the method returns -1.*);
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        if (target < nums[left] || target > nums[right]) {
            return -1;
        }
        if (nums[left] == target) {
            return left;
        } else if (nums[right] == target) {
            return right;
        }
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}