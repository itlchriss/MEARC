package g0001_0100.s0047_permutations_ii;

// #Medium #Array #Backtracking #Algorithm_II_Day_10_Recursion_Backtracking
// #2023_08_11_Time_1_ms_(99.86%)_Space_44.4_MB_(45.65%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> ans;
//@ ensures(*The integer array parameter `nums` may contain duplicates.*);
//@ ensures(*The result is a list of lists containing all possible unique permutations of the elements in the integer array `nums`.*);
//@ ensures(*The order of permutations in the result list is not specified.*);
//@ ensures(*The length of the integer array `nums` is greater than or equal to 1 and is less than or equal to 8.*);
//@ ensures(*All values in the integer array `nums` are greater than or equal to -10 and are less than or equal to 10.*);

    public List<List<Integer>> permuteUnique(int[] nums) {
        ans = new ArrayList<>();
        permute(nums, 0);
        return ans;
    }

    //@ requires 0 <= p;
    //@ requires ans != null;
    private void permute(int[] nums, int p) {
        if (p >= nums.length - 1) {
            List<Integer> t = new ArrayList<>(nums.length);
            for (int n : nums) {
                t.add(n);
            }
            ans.add(t);
            return;
        }
        permute(nums, p + 1);
        boolean[] used = new boolean[30];
        //@ loop_invariant p + 1 <= i <= nums.length;
        //@ loop_invariant \forall int k; 0 <= k < nums.length; 0 <= 10 + nums[k] < used.length;
        for (int i = p + 1; i < nums.length; i++) {
            if (nums[i] != nums[p] && !used[10 + nums[i]]) {
                used[10 + nums[i]] = true;
                swap(nums, p, i);
                permute(nums, p + 1);
                swap(nums, p, i);
            }
        }
    }

    //@ requires 0 <= i < nums.length;
    //@ requires 0 <= j < nums.length;
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}