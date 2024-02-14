package g0001_0100.s0026_remove_duplicates_from_sorted_array;

// #Easy #Top_Interview_Questions #Array #Two_Pointers #Udemy_Two_Pointers
// #2023_08_09_Time_1_ms_(98.56%)_Space_43.9_MB_(51.95%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is sorted in non-decreasing order.*);
	//@ ensures(*The length of the modified array `nums` is equal to the value of `k`.*);
	//@ ensures(*The first `k` elements of the array `nums` contain the final result, with each unique element appearing only once.*);
	//@ ensures(*The relative order of the elements in the array `nums` is the same as before the removal of duplicates.*);
	//@ ensures(*The remaining elements beyond the first `k` elements in the array `nums` can have any values.*);
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 1;
        if (n <= 1) {
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