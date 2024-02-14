package g0301_0400.s0397_integer_replacement;

// #Medium #Dynamic_Programming #Greedy #Bit_Manipulation #Memoization
// #2022_07_15_Time_1_ms_(66.03%)_Space_41.7_MB_(9.89%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ ensures(*The method returns an integer representing the minimum number of operations needed for `n` to become 1.*);
	//@ ensures(*The value of `n` is modified according to the given operations until it becomes 1.*);
    public int integerReplacement(int n) {
        Map<Integer, Integer> dp = new HashMap<>();
        return solve(n, dp);
    }

    private int solve(int n, Map<Integer, Integer> dp) {
        if (n == 1) {
            return 0;
        }
        if (dp.containsKey(n)) {
            return dp.get(n);
        }
        int ans;
        if (n % 2 == 0) {
            ans = 1 + solve(n >>> 1, dp);
        } else {
            ans = 1 + Math.min(solve(n + 1, dp), solve(n - 1, dp));
        }
        dp.put(n, ans);
        return ans;
    }
}