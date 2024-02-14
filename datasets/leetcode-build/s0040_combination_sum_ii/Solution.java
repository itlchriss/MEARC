package g0001_0100.s0040_combination_sum_ii;

// #Medium #Array #Backtracking #Algorithm_II_Day_10_Recursion_Backtracking
// #2023_08_09_Time_2_ms_(99.75%)_Space_43.9_MB_(10.59%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
	//@ requires(*The input array `candidates` must not be null.*);
	//@ requires(*The input array `candidates` must have at least one element.*);
	//@ requires(*The input array `candidates` must not contain any negative numbers.*);
	//@ requires(*The input array `candidates` must not contain any numbers greater than - The input `target` must be a positive integer.*);
	//@ requires(*The input `target` must not be greater than*);
	//@ ensures(*The method should return a list of lists, where each inner list represents a unique combination of numbers from the `candidates` array that sum to the `target`.*);
	//@ ensures(*Each inner list should not contain any duplicate numbers.*);
	//@ ensures(*The order of the numbers in each inner list does not matter.*);
	//@ ensures(*The order of the inner lists in the final result does not matter.*);
	//@ ensures(*The final result should not contain any duplicate combinations.*);
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