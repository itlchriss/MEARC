package g0001_0100.s0080_remove_duplicates_from_sorted_array_ii;

// #Medium #Array #Two_Pointers #Udemy_Arrays #2023_08_11_Time_0_ms_(100.00%)_Space_44_MB_(12.69%)

public class Solution {
//@ ensures(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ ensures(*The integer array parameter `nums` must be modified in-place to remove duplicates such that each unique element appears at most twice.*);
//@ ensures(*The relative order of the elements in the integer array parameter `nums` should be kept the same.*);
//@ ensures(*The first `k` elements of the integer array parameter `nums` should hold the final result after removing duplicates.*);
//@ ensures(*The integer result is `k` after placing the final result in the first `k` slots of the integer array parameter `nums`.*);
//@ ensures(*If there are `k` elements after removing duplicates, the first `k` elements of the integer array parameter `nums` should hold the final result.*);
//@ ensures(*It does not matter what is left beyond the first `k` elements in the integer array parameter `nums`.*);
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int k = 0;
        int count = 0;
        while (i < nums.length - 1) {
            ++count;
            if (count <= 2) {
                nums[k++] = nums[i];
            }
            if (nums[i] != nums[i + 1]) {
                count = 0;
                i++;
                continue;
            }
            i++;
        }
        ++count;
        if (count <= 2) {
            nums[k++] = nums[i];
        }
        return k;
    }
}