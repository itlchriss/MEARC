package g0001_0100.s0027_remove_element;

// #Easy #Array #Two_Pointers #2023_08_09_Time_0_ms_(100.00%)_Space_40.9_MB_(87.68%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` has a length `n`.*);
	//@ requires(*The input integer `val` is not null.*);
	//@ ensures(*The relative order of the elements in `nums` may be changed.*);
	//@ ensures(*The first `k` elements of `nums` hold the final result, where `k` is the number of elements after removing the occurrences of `val`.*);
	//@ ensures(*The remaining elements in `nums` beyond the first `k` elements do not matter.*);
	//@ ensures(*The method returns `k` after placing the final result in the first `k` slots of `nums`.*);
	//@ ensures(*The input array `nums` is modified in-place with O(1) extra memory.*);
	//@ ensures(*The modified `nums` array is sorted with no values equaling `val`.*);
	//@ ensures(*The length of the modified `nums` array is equal to `k`.*);
	//@ ensures(*The modified `nums` array can be in any order as long as it satisfies the above conditions.*);
	//@ ensures(*The method does not allocate extra space for another array.*);
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int j = len - 1;
        int occurTimes = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == val) {
                occurTimes++;
                if (j == i) {
                    return len - occurTimes;
                }
                while (nums[j] == val) {
                    j--;
                    occurTimes++;
                    if (j == i) {
                        return len - occurTimes;
                    }
                }
                nums[i] = nums[j];
                j--;
            }
            if (i == j) {
                return len - occurTimes;
            }
        }
        return len - occurTimes;
    }
}