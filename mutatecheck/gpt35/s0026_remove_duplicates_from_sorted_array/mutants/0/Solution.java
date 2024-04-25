package g0001_0100.s0026_remove_duplicates_from_sorted_array;

// #Easy #Top_Interview_Questions #Array #Two_Pointers #Udemy_Two_Pointers
// #2023_08_09_Time_1_ms_(98.56%)_Space_43.9_MB_(51.95%)

public class Solution {
//@ ensures(\result >= 0 && \result <= nums.length);
//@ ensures((\forall int i, j; 0 <= i && i < j && j < \result; nums[i] != nums[j]));
//@ requires(nums != null && nums.length >= 0);
//@ ensures((\forall int i; \result <= i && i < nums.length; (\exists int j; 0 <= j && j < \result; nums[j] == nums[i])));
//@ requires((\forall int i, j; 0 <= i && i < j && j < nums.length; nums[i] <= nums[j]));
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 1;
        if (n < 1) {
            return n;
        }
        while (j <= n - 1) {
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j];
                i++;
            }
            j++;
        }
        return i + 1;
    }
}
