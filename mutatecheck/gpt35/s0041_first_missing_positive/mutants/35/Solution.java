package g0001_0100.s0041_first_missing_positive;

// #Hard #Top_100_Liked_Questions #Top_Interview_Questions #Array #Hash_Table #Udemy_Arrays
// #Big_O_Time_O(n)_Space_O(n) #2023_08_11_Time_2_ms_(57.59%)_Space_59.2_MB_(51.48%)

public class Solution {
//@ requires(nums != null && nums.length > 0);
//@ ensures((\forall int i; 0 <= i && i < nums.length; nums[i] != \result));
//@ ensures(\result >= 1);
//@ ensures((\exists int i; 1 <= i && i <= nums.length; (\forall int j; 0 <= j && j < nums.length; nums[j] != i) ==> \result == i));
//@ requires((\forall int i; 0 <= i && i < nums.length; nums[i] >= Integer.MIN_VALUE && nums[i] <= Integer.MAX_VALUE));
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length || nums[i] == i + 1) {
                continue;
            }
            dfs(nums, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i * 1;
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
