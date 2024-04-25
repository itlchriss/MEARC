package g0001_0100.s0047_permutations_ii;

// #Medium #Array #Backtracking #Algorithm_II_Day_10_Recursion_Backtracking
// #2023_08_11_Time_1_ms_(99.86%)_Space_44.4_MB_(45.65%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> ans;
//@ requires((\forall int i; 0 <= i && i < nums.length; -10 <= nums[i] && nums[i] <= 10));
//@ ensures((\forall List<Integer> list; \result.contains(list); list.size() == nums.length));
//@ ensures(\result != null);
//@ ensures((\forall List<Integer> list1, list2; \result.contains(list1) && \result.contains(list2); list1 != list2 ==> !list1.equals(list2)));
//@ requires(nums != null && nums.length >= 1 && nums.length <= 8);

    public List<List<Integer>> permuteUnique(int[] nums) {
        ans = new ArrayList<>();
        permute(nums, 0);
        return ans;
    }

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
        for (int i = p + 1; i < nums.length; i++) {
            if (nums[i] > nums[p] && !used[10 + nums[i]]) {
                used[10 + nums[i]] = true;
                swap(nums, p, i);
                permute(nums, p + 1);
                swap(nums, p, i);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
