package g2801_2900.s2830_maximize_the_profit_as_the_salesman;

// #Medium #Array #Dynamic_Programming #Sorting #Binary_Search
// #2023_12_11_Time_36_ms_(80.00%)_Space_76.3_MB_(73.13%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` must be greater than or equal to 1.*);
//@ ensures(*The length of the `offers` list must be greater than or equal to 1.*);
//@ ensures(*Each sublist in the `offers` list must have a length of 3.*);
//@ ensures(*The start and end indices in each sublist must be within the range of 0 to `n - 1`.*);
//@ ensures(*The gold amount in each sublist must be greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer representing the maximum amount of gold that can be earned.*);
//@ ensures(*The maximum amount of gold earned should be achievable by strategically selecting and selling houses to buyers.*);
//@ ensures(*Different buyers cannot buy the same house.*);
//@ ensures(*Some houses may remain unsold.*);
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        int[] dp = new int[n];
        HashMap<Integer, List<List<Integer>>> range = new HashMap<>();
        for (List<Integer> l : offers) {
            if (range.containsKey(l.get(0))) {
                range.get(l.get(0)).add(l);
            } else {
                List<List<Integer>> r = new ArrayList<>();
                r.add(l);
                range.put(l.get(0), r);
            }
        }
        int i = 0;
        while (i < n) {
            List<List<Integer>> temp = new ArrayList<>();
            if (range.containsKey(i)) {
                temp = range.get(i);
            }
            dp[i] = (i != 0) ? Math.max(dp[i], dp[i - 1]) : dp[i];
            for (List<Integer> l : temp) {
                dp[l.get(1)] =
                        (i != 0)
                                ? Math.max(dp[l.get(1)], dp[i - 1] + l.get(2))
                                : Math.max(dp[l.get(1)], l.get(2));
            }
            i++;
        }
        return dp[n - 1];
    }
}