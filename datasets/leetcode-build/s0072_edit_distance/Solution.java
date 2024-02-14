package g0001_0100.s0072_edit_distance;

// #Hard #Top_100_Liked_Questions #String #Dynamic_Programming
// #Algorithm_II_Day_18_Dynamic_Programming #Dynamic_Programming_I_Day_19
// #Udemy_Dynamic_Programming #Big_O_Time_O(n^2)_Space_O(n2)
// #2023_08_11_Time_4_ms_(90.13%)_Space_41.8_MB_(99.78%)

@SuppressWarnings("java:S2234")
public class Solution {
	//@ requires(*The input strings `word1` and `word2` are not null.*);
	//@ requires(*The lengths of `word1` and `word2` are within the range of 0 to 500.*);
	//@ requires(*`word1` and `word2` consist only of lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the minimum number of operations required to convert `word1` to `word2`.*);
	//@ ensures(*The method does not modify the input strings `word1` and `word2`.*);
    public int minDistance(String w1, String w2) {
        int n1 = w1.length();
        int n2 = w2.length();
        if (n2 > n1) {
            return minDistance(w2, w1);
        }
        int[] dp = new int[n2 + 1];
        for (int j = 0; j <= n2; j++) {
            dp[j] = j;
        }
        for (int i = 1; i <= n1; i++) {
            int pre = dp[0];
            dp[0] = i;
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