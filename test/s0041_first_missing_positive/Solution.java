package g0001_0100.s0041_first_missing_positive;

// #Hard #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table #Udemy_Arrays
// #Big_O_Time_O(n)_Space_O(n) #2023_08_11_Time_2_ms_(57.59%)_Space_59.2_MB_(51.48%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 500000 and is greater than or equal to 1.*);
//@ requires(*All the values in the integer array parameter `nums` are less than or equal to 2^31 - 1 and are greater than or equal to -2^31.*);
//@ requires(*The algorithm used to find the smallest missing positive integer runs in O(n) time.*);
//@ requires(*The algorithm used to find the smallest missing positive integer uses constant extra space.*);
//@ ensures(*The integer result is greater than or equal to 1.*);
//@ ensures(*The integer result is the smallest positive integer that is missing from the integer array parameter `nums`.*);
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