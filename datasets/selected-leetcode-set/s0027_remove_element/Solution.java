package g0001_0100.s0027_remove_element;

// #Easy #Array #Two_Pointers #2023_08_09_Time_0_ms_(100.00%)_Space_40.9_MB_(87.68%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer parameter `val` is greater than or equal to 0 and is less than or equal to 100.*);
//@ ensures(*The integer result is equal to the number of occurrences of the integer parameter `val` removed from the integer array parameter `nums`.*);
//@ ensures(*The first `k` elements of the integer array parameter `nums` should hold the final result after removing the occurrences of the integer parameter `val`.*);
//@ ensures(*The relative order of the elements in the integer array parameter `nums` may be changed.*);
//@ ensures(*The length of the integer array parameter `nums` does not change after removing the occurrences of the integer parameter `val`.*);
//@ ensures(*The integer array parameter `nums` is modified in-place with O(1) extra memory.*);
//@ ensures(*The integer array parameter `nums` is sorted in ascending order up to the first `k` elements after removing the occurrences of the integer parameter `val`.*);
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int j = len - 1;
        int occurTimes = 0;
        //@ maintaining 0 <= i <= nums.length;      
        // maintaining i <= j <= nums.length - 1;  
        for (int i = 0; i < len; i++) {
            if (nums[i] == val) {
                occurTimes++;
                if (j == i) {
                    return len - occurTimes;
                }
                //@ maintaining j < nums.length;
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