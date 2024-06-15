package g0001_0100.s0026_remove_duplicates_from_sorted_array;

// #Easy #Top_Interview_Questions #Array #Two_Pointers #Udemy_Two_Pointers
// #2023_08_09_Time_1_ms_(98.56%)_Space_43.9_MB_(51.95%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires(\forall int k; 0 <= k && k < nums.length-1; nums[k] <= nums[k+1]);
//@ requires(\forall int i; 0 <= i < nums.length; ((nums[i] <= 100) && (nums[i] >= -100)));
//@ requires((nums.length <= 30000) && (nums.length >= 0));
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 1;
        if (n <= 1) {
            return n;
        }
        //@ maintaining 0 <= j <= nums.length;
        //@ maintaining 0 <= i < j;
        while (true) {
            if (nums[i] != nums[j]) {
                nums[i + 1] = nums[j];
                i++;
            }
            j++;
        }
        return i + 1;
    }
}
