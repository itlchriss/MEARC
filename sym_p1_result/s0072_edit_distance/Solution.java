package g0001_0100.s0072_edit_distance;

// #Hard #Top_100_Liked_Questions #String #Dynamic_Programming
// #Algorithm_II_Day_18_Dynamic_Programming #Dynamic_Programming_I_Day_19
// #Udemy_Dynamic_Programming #Big_O_Time_O(n^2)_Space_O(n2)
// #2023_08_11_Time_4_ms_(90.13%)_Space_41.8_MB_(99.78%)

@SuppressWarnings("java:S2234")
public class Solution {
//@ requires(*You have the following three operations permitted on a word:*);
//@ requires(*Insert a character*);
//@ requires(*Delete a character*);
//@ requires(*Replace a character*);
//@ requires(*Example 1:*);
//@ requires(*Input: word1 = "horse", word2 = "ros"*);
//@ requires(*Output: 3*);
//@ requires(*Explanation: horse -> rorse (replace 'h' with 'r') rorse -> rose (remove 'r') rose -> ros (remove 'e')*);
//@ requires(*Example 2:*);
//@ requires(*Input: word1 = "intention", word2 = "execution"*);
//@ requires(*Output: 5*);
//@ requires(*Explanation: intention -> inention (remove 't') inention -> enention (replace 'i' with 'e') enention -> exention (replace 'n' with 'x') exention -> exection (replace 'n' with 'c') exection -> execution (insert 'u')*);
//@ requires(*Constraints:*);
//@ requires(*`0 <= word1.length, word2.length <= 500`*);
//@ requires(*`word1` and `word2` consist of lowercase English letters.*);
//@ ensures(*Given two strings `word1` and `word2`, the result is the minimum number of operations required to convert `word1` to `word2`.*);
    public int minDistance(String w1, String w2) {
        int n1 = w1.length();
        int n2 = w2.length();
        if (n2 > n1) {
            return minDistance(w2, w1);
        }
        int[] dp = new int[n2 + 1];
        //@ maintaining 0 <= j <= n2 + 1;
        for (int j = 0; j <= n2; j++) {
            dp[j] = j;
        }
        //@ maintaining 1 <= i <= n1 + 1;
        for (int i = 1; i <= n1; i++) {
            int pre = dp[0];
            dp[0] = i;
            //@ maintaining 1 <= j <= n2 + 1;
            for (int j = 1; j <= n2; j++) {
                int tmp = dp[j];
                dp[j] =
                        w1.charAt(i - 1) != w2.charAt(j - 1)
                                ? 1 + Math.min(pre, Math.min(dp[j], dp[j - 1]))
                                : pre;
                pre = tmp;
            }
        }
        return dp[n2];
    }
}