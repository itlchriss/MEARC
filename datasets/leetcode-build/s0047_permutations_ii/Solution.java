package g0001_0100.s0047_permutations_ii;

// #Medium #Array #Backtracking #Algorithm_II_Day_10_Recursion_Backtracking
// #2023_08_11_Time_1_ms_(99.86%)_Space_44.4_MB_(45.65%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<List<Integer>> ans;
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is greater than or equal to 1 and less than or equal to 8.*);
	//@ requires(*Each element in `nums` is an integer.*);
	//@ requires(*Each element in `nums` is within the range of -10 to 10.*);
	//@ ensures(*The method returns a list of lists of integers.*);
	//@ ensures(*Each list in the returned list represents a unique permutation of the input array `nums`.*);
	//@ ensures(*The order of the permutations in the returned list can be in any order.*);
	//@ ensures(*The length of each permutation in the returned list is equal to the length of the input array `nums`.*);
	//@ ensures(*The elements in each permutation in the returned list are in the same order as the input array `nums`.*);
	//@ ensures(*The returned list does not contain any duplicate permutations.*);

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
            if (nums[i] != nums[p] && !used[10 + nums[i]]) {
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