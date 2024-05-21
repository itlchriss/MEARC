package g0001_0100.s0026_remove_duplicates_from_sorted_array;

// #Easy #Top_Interview_Questions #Array #Two_Pointers #Udemy_Two_Pointers
// #2023_08_09_Time_1_ms_(98.56%)_Space_43.9_MB_(51.95%)

public class Solution {
//@ requires(*Given an integer array param_nums sorted in non-decreasing order, remove the duplicates [in-place](https://en.wikipedia.org/wiki/In-placealgorithm) such that each unique element appears only once.*);
//@ requires(*The relative order of the elements should be kept the same.*);
//@ requires(*It does not matter what you leave beyond the first `k` elements.*);
//@ requires(*Do not allocate extra space for another array.*);
//@ requires(*You must do this by modifying the input array [in-place](https://en.wikipedia.org/wiki/In-placealgorithm) with O(1) extra memory.*);
//@ requires(*Custom Judge:*);
//@ requires(*The judge will test your solution with the following code:*);
//@ requires(*int[] nums = [...]; // Input array*);
//@ requires(*int[] expectedNums = [...]; // The expected answer with correct length*);
//@ requires(*int k = removeDuplicates(nums); // Calls your implementation*);
//@ requires(*assert k == expectedNums.length;*);
//@ requires(*for (int i = 0; i < k; i++) {*);
//@ requires(*assert nums[i] == expectedNums[i];*);
//@ requires(*}*);
//@ requires(*If all assertions pass, then your solution will be accepted.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,1,2]*);
//@ requires(*Output: 2, nums = [1,2,\]*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [0,0,1,1,1,2,2,3,3,4]*);
//@ requires(*Output: 5, nums = [0,1,2,3,4,\,\,\,\,\]*);
//@ requires(*Constraints:*);
//@ requires(*<code>0 <= nums.length <= 3  10<sup>4</sup></code>*);
//@ requires(*`-100 <= nums[i] <= 100`*);
//@ requires(*param_nums is sorted in non-decreasing order.*);
//@ ensures(*Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array param_nums.*);
//@ ensures(*More formally, if there are `k` elements after removing the duplicates, then the first `k` elements of param_nums should hold the final result.*);
//@ ensures(*Return `k` after placing the final result in the first `k` slots of param_nums.*);
//@ ensures(*Explanation: Your function should the result is k = 2, with the first two elements of nums being 1 and 2 respectively.*);
//@ ensures(*It does not matter what you leave beyond the the result ised k (hence they are underscores).*);
//@ ensures(*Explanation: Your function should the result is k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.*);
//@ ensures(*It does not matter what you leave beyond the the result ised k (hence they are underscores).*);
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