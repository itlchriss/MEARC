package g0001_0100.s0039_combination_sum;

// #Medium #Top_100_Liked_Questions #Array #Backtracking #Algorithm_II_Day_10_Recursion_Backtracking
// #Level_2_Day_20_Brute_Force/Backtracking #Udemy_Backtracking/Recursion
// #Big_O_Time_O(2^n)_Space_O(n+2^n) #2023_08_09_Time_1_ms_(100.00%)_Space_43.6_MB_(90.84%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ requires(*Method behavioural specifications:*);
//@ requires(*The integer array parameter `candidates` must not be null.*);
//@ requires(*The integer parameter `target` is greater than or equal to 1 and is less than or equal to 500.*);
//@ requires(*The same number from the integer array parameter `candidates` can be chosen multiple times in a combination.*);
//@ requires(*Two combinations are considered unique if the frequency of at least one chosen number is different.*);
//@ ensures(*The length of the integer array result is less than or equal to 150.*);
//@ ensures(*Each list in the list result is a unique combination of integers from the integer array parameter `candidates` that sum up to the integer parameter `target`.*);
    public List<List<Integer>> combinationSum(int[] coins, int amount) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> subList = new ArrayList<>();
        combinationSumRec(coins.length, coins, amount, subList, ans);
        return ans;
    }

    //@ requires 0 <= n < coins.length;
    private void combinationSumRec(
            int n, int[] coins, int amount, List<Integer> subList, List<List<Integer>> ans) {
        if (amount == 0 || n == 0) {
            if (amount == 0) {
                List<Integer> base = new ArrayList<>(subList);
                ans.add(base);
            }
            return;
        }
        if (amount - coins[n - 1] >= 0) {
            subList.add(coins[n - 1]);
            combinationSumRec(n, coins, amount - coins[n - 1], subList, ans);
            subList.remove(subList.size() - 1);
        }
        combinationSumRec(n - 1, coins, amount, subList, ans);
    }
}