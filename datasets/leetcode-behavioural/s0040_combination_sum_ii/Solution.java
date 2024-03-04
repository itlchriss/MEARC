package g0001_0100.s0040_combination_sum_ii;

// #Medium #Array #Backtracking #Algorithm_II_Day_10_Recursion_Backtracking
// #2023_08_09_Time_2_ms_(99.75%)_Space_43.9_MB_(10.59%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
//@ ensures(*The integer array parameter `candidates` must not be null.*);
//@ ensures(*The integer parameter `target` must be greater than or equal to 1 and less than or equal to 30.*);
//@ ensures(*The integer collection result must not contain duplicate combinations.*);
//@ ensures(*Each number in the integer array parameter `candidates` can only be used once in the combination.*);
//@ ensures(*The integer collection result must contain all unique combinations in the integer array parameter `candidates` where the candidate numbers sum to the integer parameter `target`.*);
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> sums = new ArrayList<>();
        // optimize
        Arrays.sort(candidates);
        combinationSum(candidates, target, 0, sums, new LinkedList<>());
        return sums;
    }

    private void combinationSum(
            int[] candidates,
            int target,
            int start,
            List<List<Integer>> sums,
            LinkedList<Integer> sum) {
        if (target == 0) {
            // make a deep copy of the current combination
            sums.add(new ArrayList<>(sum));
            return;
        }
        for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
            // If candidate[i] equals candidate[i-1], then solutions for i is subset of
            // solution of i-1
            if (i == start || (i > start && candidates[i] != candidates[i - 1])) {
                sum.addLast(candidates[i]);
                // call on 'i+1' (not i) to avoid duplicate usage of same element
                combinationSum(candidates, target - candidates[i], i + 1, sums, sum);
                sum.removeLast();
            }
        }
    }
}