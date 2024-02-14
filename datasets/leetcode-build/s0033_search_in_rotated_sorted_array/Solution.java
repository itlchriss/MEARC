package g0001_0100.s0033_search_in_rotated_sorted_array;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search
// #Algorithm_II_Day_1_Binary_Search #Binary_Search_I_Day_11 #Level_2_Day_8_Binary_Search
// #Udemy_Binary_Search #Big_O_Time_O(log_n)_Space_O(1)
// #2023_08_09_Time_0_ms_(100.00%)_Space_40.6_MB_(92.43%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is not empty.*);
	//@ requires(*The input array `nums` is sorted in ascending order.*);
	//@ requires(*The input array `nums` contains distinct values.*);
	//@ requires(*The input integer `target` is within the range of -10^4 to 10^4.*);
	//@ ensures(*The method returns an integer representing the index of the `target` in the `nums` array if it is present.*);
	//@ ensures(*The method returns -1 if the `target` is not present in the `nums` array.*);
	//@ ensures(*The method has a runtime complexity of O(log n).*);
    public int search(int[] nums, int target) {
        int mid;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            mid = ((hi - lo) >> 1) + lo;
            if (target == nums[mid]) {
                return mid;
            }
            // if this is true, then the possible rotation can only be in the second half
            if (nums[lo] <= nums[mid]) {
                // the target is in the first half only if it's
                if (nums[lo] <= target && target <= nums[mid]) {
                    // included
                    hi = mid - 1;
                } else {
                    // between nums[lo] and nums[mid]
                    lo = mid + 1;
                }
                // otherwise, the possible rotation can only be in the first half
            } else if (nums[mid] <= target && target <= nums[hi]) {
                // the target is in the second half only if it's included
                lo = mid + 1;
            } else {
                // between nums[hi] and nums[mid]
                hi = mid - 1;
            }
        }
        return -1;
    }
}