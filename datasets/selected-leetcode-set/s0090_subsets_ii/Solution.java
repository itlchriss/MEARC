package g0001_0100.s0090_subsets_ii;

// #Medium #Array #Bit_Manipulation #Backtracking #Algorithm_II_Day_9_Recursion_Backtracking
// #2022_06_20_Time_2_ms_(82.94%)_Space_43.5_MB_(77.86%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S5413")
public class Solution {
    List<List<Integer>> allComb = new ArrayList<>();
    List<Integer> comb = new ArrayList<>();
    int[] nums;
//@ ensures(*The integer array parameter `nums` may contain duplicates.*);
//@ ensures(*The result is a list of lists representing all possible subsets of the integer array `nums`.*);
//@ ensures(*The solution set must not contain duplicate subsets.*);
//@ ensures(*The order of subsets in the result list can be in any order.*);
//@ ensures(*Each subset in the result list must be unique.*);
//@ ensures(*The length of the integer array `nums` is greater than or equal to 1 and is less than or equal to 10.*);
//@ ensures(*Each element in the integer array `nums` is greater than or equal to -10 and is less than or equal to 10.*);

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        this.nums = nums;
        dfs(0);
        allComb.add(new ArrayList<>());
        return allComb;
    }

    private void dfs(int start) {
        if (start > nums.length) {
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            comb.add(nums[i]);
            allComb.add(new ArrayList<>(comb));
            dfs(i + 1);
            comb.remove(comb.size() - 1);
        }
    }
}