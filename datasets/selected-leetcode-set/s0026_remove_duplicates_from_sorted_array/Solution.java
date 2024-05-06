package g0001_0100.s0026_remove_duplicates_from_sorted_array;

// #Easy #Top_Interview_Questions #Array #Two_Pointers #Udemy_Two_Pointers
// #2023_08_09_Time_1_ms_(98.56%)_Space_43.9_MB_(51.95%)

public class Solution {
//@ ensures(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ ensures(*The integer result is equal to `k`, which represents the number of unique elements in the array after removing duplicates.*);
//@ ensures(*The first `k` elements of the integer array `nums` hold the final result with each unique element appearing only once.*);
//@ ensures(*The relative order of the elements in the array is kept the same.*);
//@ ensures(*The length of the integer array `nums` does not change, and any elements beyond the first `k` elements are not specified.*);
//@ ensures(*The method must modify the input array in-place with O(1) extra memory.*);
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