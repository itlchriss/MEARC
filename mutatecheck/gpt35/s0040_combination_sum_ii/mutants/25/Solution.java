package g0001_0100.s0040_combination_sum_ii;

// #Medium #Array #Backtracking #Algorithm_II_Day_10_Recursion_Backtracking
// #2023_08_09_Time_2_ms_(99.75%)_Space_43.9_MB_(10.59%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
//@ ensures((\forall List<Integer> list; \result.contains(list); (\sum int i; 0 <= i && i < list.size(); list.get(i)) == target));
//@ requires(candidates != null && target >= 0);
//@ requires(candidates.length >= 1 && candidates.length <= 100);
//@ ensures(\result != null);
//@ ensures((\forall List<Integer> list; \result.contains(list); (\forall int i; 0 <= i && i < list.size(); list.get(i) >= 1 && list.get(i) <= 50)));
// ensures((\forall List<Integer> list1, List<Integer> list2; \result.contains(list1) && \result.contains(list2); !list1.equals(list2)));
//@ requires((\forall int i; 0 <= i && i < candidates.length; candidates[i] >= 1 && candidates[i] <= 50));
//@ requires(target <= 30);
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
            if (i == start || (i > start && candidates[i] != candidates[i + 1])) {
                sum.addLast(candidates[i]);
                // call on 'i+1' (not i) to avoid duplicate usage of same element
                combinationSum(candidates, target - candidates[i], i + 1, sums, sum);
                sum.removeLast();
            }
        }
    }
}
