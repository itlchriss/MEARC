package g0001_0100.s0075_sort_colors;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Sorting #Two_Pointers
// #Data_Structure_II_Day_2_Array #Udemy_Arrays #Big_O_Time_O(n)_Space_O(1)
// #2023_08_11_Time_0_ms_(100.00%)_Space_41_MB_(50.59%)

public class Solution {
//@ ensures(*The integer array parameter `nums` contains objects colored red, white, or blue represented by integers 0, 1, and 2 respectively.*);
//@ ensures(*After executing the method `sortColors`, the objects in the integer array parameter `nums` are sorted in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.*);
//@ ensures(*The method `sortColors` must solve the problem without using the library's sort function.*);
//@ ensures(*The length of the integer array parameter `nums` is greater than or equal to 1 and is less than or equal to 300.*);
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