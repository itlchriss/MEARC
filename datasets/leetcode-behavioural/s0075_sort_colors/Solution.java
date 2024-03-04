package g0001_0100.s0075_sort_colors;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Sorting #Two_Pointers
// #Data_Structure_II_Day_2_Array #Udemy_Arrays #Big_O_Time_O(n)_Space_O(1)
// #2023_08_11_Time_0_ms_(100.00%)_Space_41_MB_(50.59%)

public class Solution {
//@ ensures(*The integer parameter array `nums` is sorted in-place such that objects of the same color are adjacent, with the colors in the order red, white, and blue.*);
//@ ensures(*The integers 0, 1, and 2 represent the colors red, white, and blue respectively in the sorted array `nums`.*);
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
        for (int k = zeroes + ones; k < nums.length; k++) {
            nums[k] = 2;
        }
    }
}