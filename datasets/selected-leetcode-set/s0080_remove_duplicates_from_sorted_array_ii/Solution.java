package g0001_0100.s0080_remove_duplicates_from_sorted_array_ii;

// #Medium #Array #Two_Pointers #Udemy_Arrays #2023_08_11_Time_0_ms_(100.00%)_Space_44_MB_(12.69%)

public class Solution {
//@ ensures(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ ensures(*The integer result is the number of unique elements in the array `nums` after removing duplicates such that each unique element appears at most twice.*);
//@ ensures(*The relative order of the elements in the array `nums` should be kept the same.*);
//@ ensures(*The first `k` elements of the array `nums` should hold the final result after removing duplicates.*);
//@ ensures(*The length of the integer array `nums` does not change, and the final result is placed in the first `k` slots of the array `nums`.*);
//@ ensures(*The integer result `k` is equal to the number of elements in the array `nums` after removing duplicates.*);
//@ ensures(*The integer result `k` is greater than or equal to 0 and is less than or equal to 30000.*);
//@ ensures(*The integer result `k` is equal to the length of the expected answer with correct length.*);
//@ ensures(*The values of the first `k` elements in the array `nums` are equal to the values of the expected answer with correct length.*);
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