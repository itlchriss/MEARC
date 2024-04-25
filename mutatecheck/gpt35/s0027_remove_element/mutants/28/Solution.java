package g0001_0100.s0027_remove_element;

// #Easy #Array #Two_Pointers #2023_08_09_Time_0_ms_(100.00%)_Space_40.9_MB_(87.68%)

public class Solution {
//@ requires(nums != null && nums.length >= 0);
//@ ensures(\result >= 0 && \result <= nums.length);
//@ ensures((\forall int i; 0 <= i && i < \result; nums[i] != val));
//@ ensures((\forall int i, j; 0 <= i && i < j && j < \result; nums[i] <= nums[j]));
//@ requires(val >= 0);
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
                while (nums[j] <= val) {
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
