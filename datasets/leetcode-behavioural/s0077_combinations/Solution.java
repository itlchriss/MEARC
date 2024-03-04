package g0001_0100.s0077_combinations;

// #Medium #Backtracking #Algorithm_I_Day_11_Recursion_Backtracking
// #2023_08_11_Time_11_ms_(77.40%)_Space_93_MB_(5.21%)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S1149")
public class Solution {
//@ ensures(*The integer parameter `n` must be greater than or equal to 1.*);
//@ ensures(*The integer parameter `k` must be greater than or equal to 1.*);
//@ ensures(*The integer collection result must contain all possible combinations of `k` numbers out of the range `[1, n]`.*);
//@ ensures(*The order of the combinations in the integer collection result can be arbitrary.*);
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        // Boundary case
        if (n > 20 || k < 1 || k > n) {
            return ans;
        }
        backtrack(ans, n, k, 1, new ArrayDeque<>());
        return ans;
    }

    private void backtrack(
            List<List<Integer>> ans, int n, int k, int s, ArrayDeque<Integer> stack) {
        // Base case
        // If k becomes 0
        if (k == 0) {
            ans.add(new ArrayList<>(stack));
            return;
        }
        // Start with s till n-k+1
        for (int i = s; i <= (n - k) + 1; i++) {
            stack.push(i);
            // Update start for recursion and decrease k by 1
            backtrack(ans, n, k - 1, i + 1, stack);
            stack.pop();
        }
    }
}