package g0001_0100.s0046_permutations;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Backtracking
// #Algorithm_I_Day_11_Recursion_Backtracking #Level_2_Day_20_Brute_Force/Backtracking
// #Udemy_Backtracking/Recursion #Big_O_Time_O(n*n!)_Space_O(n+n!)
// #2023_08_11_Time_1_ms_(95.07%)_Space_43.7_MB_(87.98%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The length of the integer array parameter `nums` is greater than or equal to 1 and is less than or equal to 6.*);
//@ ensures(*All values in the integer array parameter `nums` are greater than or equal to -10 and are less than or equal to 10.*);
//@ ensures(*All integers in the integer array parameter `nums` are unique.*);
//@ ensures(*The result is a list of lists where each inner list represents a permutation of the integers in the input array `nums`.*);
//@ ensures(*The order of permutations in the result list can be in any order.*);
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> finalResult = new ArrayList<>();
        permuteRecur(nums, finalResult, new ArrayList<Integer>(), new boolean[nums.length]);
        return finalResult;
    }

    //@ requires used.length == nums.length;
    private void permuteRecur(
            int[] nums, List<List<Integer>> finalResult, List<Integer> currResult, boolean[] used) {
        if (currResult.size() == nums.length) {
            finalResult.add(new ArrayList<Integer>(currResult));
            return;
        }
        //@ loop_invariant 0 <= i <= nums.length;
        //@ loop_invariant 0 <= i <= used.length;
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            currResult.add(nums[i]);
            used[i] = true;
            permuteRecur(nums, finalResult, currResult, used);
            used[i] = false;
            currResult.remove(currResult.size() - 1);
        }
    }
}