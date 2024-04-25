package g0001_0100.s0080_remove_duplicates_from_sorted_array_ii;

// #Medium #Array #Two_Pointers #Udemy_Arrays #2023_08_11_Time_0_ms_(100.00%)_Space_44_MB_(12.69%)

public class Solution {
//@ ensures(\result >= 0);
//@ ensures((\forall int i; \result <= i && i < nums.length; nums[i] == \old(nums[i])));
//@ requires(nums != null);
//@ ensures((\forall int i; 0 <= i && i < \result; (\forall int j; i+1 <= j && j < \result; nums[i] != nums[j] || (j - i) < 2)));
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int k = 0;
        int count = 0;
        while (i < nums.length - 1) {
            ++count;
            if (count <= 2) {
                nums[k++] = nums[i];
            }
            if (nums[i] != nums[i - 1]) {
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
