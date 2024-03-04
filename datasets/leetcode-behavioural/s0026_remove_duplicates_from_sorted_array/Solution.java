package g0001_0100.s0026_remove_duplicates_from_sorted_array;

// #Easy #Top_Interview_Questions #Array #Two_Pointers #Udemy_Two_Pointers
// #2023_08_09_Time_1_ms_(98.56%)_Space_43.9_MB_(51.95%)

public class Solution {
//@ ensures(*The integer array parameter `nums` is sorted in non-decreasing order.*);
//@ ensures(*The integer array parameter `nums` is modified in-place to remove duplicates such that each unique element appears only once.*);
//@ ensures(*The relative order of the elements in the integer array parameter `nums` is kept the same.*);
//@ ensures(*The first `k` elements of the integer array parameter `nums` hold the final result after removing duplicates.*);
//@ ensures(*The integer result is `k` after placing the final result in the first `k` slots of the integer array parameter `nums`.*);
//@ ensures(*No extra space is allocated for another array during the removal of duplicates.*);
//@ ensures(*The modification of the input array `nums` is done in-place with O(1) extra memory.*);
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