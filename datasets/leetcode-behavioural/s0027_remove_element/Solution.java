package g0001_0100.s0027_remove_element;

// #Easy #Array #Two_Pointers #2023_08_09_Time_0_ms_(100.00%)_Space_40.9_MB_(87.68%)

public class Solution {
//@ ensures(*Every occurrence of the integer parameter `val` in the integer array parameter `nums` is removed in-place.*);
//@ ensures(*The relative order of the elements in the integer array parameter `nums` may be changed.*);
//@ ensures(*The first `k` elements of the integer array parameter `nums` hold the final result after removing the occurrences of the integer parameter `val`.*);
//@ ensures(*The integer result `k` is returned after placing the final result in the first `k` slots of the integer array parameter `nums`.*);
//@ ensures(*No extra space is allocated for another array, and the operation is done by modifying the input array in-place with O(1) extra memory.*);
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