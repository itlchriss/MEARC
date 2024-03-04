package g0001_0100.s0078_subsets;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Bit_Manipulation #Backtracking
// #Algorithm_II_Day_9_Recursion_Backtracking #Udemy_Backtracking/Recursion
// #Big_O_Time_O(2^n)_Space_O(n*2^n) #2023_08_11_Time_1_ms_(70.60%)_Space_41.8_MB_(71.73%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S5413")
public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The size of the list of lists result is equal to 2 raised to the power of the length of the integer array parameter `nums`.*);
//@ ensures(*Each list in the list of lists result is a subset of the integer array parameter `nums`.*);
//@ ensures(*The list of lists result must not contain duplicate subsets.*);
//@ ensures(*The order of subsets in the list of lists result is not specified.*);
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        solve(nums, new ArrayList<>(), res, 0);
        return res;
    }

    private void solve(int[] nums, List<Integer> temp, List<List<Integer>> res, int start) {
        res.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            solve(nums, temp, res, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}