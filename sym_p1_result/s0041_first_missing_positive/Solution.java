package g0001_0100.s0041_first_missing_positive;

// #Hard #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table #Udemy_Arrays
// #Big_O_Time_O(n)_Space_O(n) #2023_08_11_Time_2_ms_(57.59%)_Space_59.2_MB_(51.48%)

public class Solution {
//@ requires(*You must implement an algorithm that runs in `O(n)` time and uses constant extra space.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,2,0]*);
//@ requires(*Output: 3*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [3,4,-1,1]*);
//@ requires(*Output: 2*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [7,8,9,11,12]*);
//@ requires(*Output: 1*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= nums.length <= 5  10<sup>5</sup></code>*);
//@ requires(*<code>-2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1</code>*);
//@ ensures(*Given an unsorted integer array param_nums, the result is the smallest missing positive integer.*);
    public int firstMissingPositive(int[] nums) {
        //@ loop_invariant 0 <= i <= nums.length;
        //@ decreases nums.length - i;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length || nums[i] == i + 1) {
                continue;
            }
            dfs(nums, nums[i]);
        }
        //@ loop_invariant 0 <= i <= nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void dfs(int[] nums, int val) {
        if (val <= 0 || val > nums.length || val == nums[val - 1]) {
            return;
        }
        int temp = nums[val - 1];
        nums[val - 1] = val;
        dfs(nums, temp);
    }
}