package g0001_0100.s0026_remove_duplicates_from_sorted_array;

// #Easy #Top_Interview_Questions #Array #Two_Pointers #Udemy_Two_Pointers
// #2023_08_09_Time_1_ms_(98.56%)_Space_43.9_MB_(51.95%)

public class Solution {
//@ requires(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ requires(*After executing the method, each unique element in the integer array `nums` appears only once.*);
//@ requires(*The relative order of the elements in the integer array `nums` is kept the same.*);
//@ requires(*The length of the integer array `nums` after removing duplicates is equal to `k`.*);
//@ requires(*The method modifies the input array `nums` in-place with O(1) extra memory.*);
//@ ensures(*The result is placed in the first part of the array `nums`.*);
//@ ensures(*The first `k` elements of the array `nums` hold the final result after removing duplicates.*);
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 1;
        if (n <= 1) {
            return n;
        }
        //@ maintaining 0 <= j <= nums.length;
        //@ maintaining 0 <= i < j;
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