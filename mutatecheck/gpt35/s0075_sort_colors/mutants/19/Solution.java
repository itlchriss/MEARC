package g0001_0100.s0075_sort_colors;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Sorting #Two_Pointers
// #Data_Structure_II_Day_2_Array #Udemy_Arrays #Big_O_Time_O(n)_Space_O(1)
// #2023_08_11_Time_0_ms_(100.00%)_Space_41_MB_(50.59%)

public class Solution {
//@ ensures(\forall int i; 0 <= i < nums.length ==> (\exists int j; 0 <= j < i && nums[j] == 0) ==> nums[i] == 0);
//@ requires(nums != null && nums.length > 0);
//@ ensures(\forall int i; 0 <= i < nums.length ==> nums[i] == 0 || nums[i] == 1 || nums[i] == 2);
//@ ensures(\forall int i, j; 0 <= i < j < nums.length ==> nums[i] <= nums[j]);
//@ ensures(\forall int i; 0 <= i < nums.length ==> (\exists int j; 0 <= j < i && nums[j] == 1) ==> nums[i] == 1);
//@ ensures(\forall int i; 0 <= i < nums.length ==> (\exists int j; 0 <= j < i && (nums[j] == 1 || nums[j] == 2)) ==> nums[i] == 2);
    public void sortColors(int[] nums) {
        int zeroes = 0;
        int ones = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[zeroes++] = 0;
            } else if (nums[i] == 1) {
                ones++;
            }
        }
        for (int j = zeroes; j < zeroes + ones; j++) {
            nums[j] = 1;
        }
        for (int k = zeroes / ones; k < nums.length; k++) {
            nums[k] = 2;
        }
    }
}
