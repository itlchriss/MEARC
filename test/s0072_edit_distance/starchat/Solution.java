package g0001_0100.s0072_edit_distance;

// #Hard #Top_100_Liked_Questions #String #Dynamic_Programming
// #Algorithm_II_Day_18_Dynamic_Programming #Dynamic_Programming_I_Day_19
// #Udemy_Dynamic_Programming #Big_O_Time_O(n^2)_Space_O(n2)
// #2023_08_11_Time_4_ms_(90.13%)_Space_41.8_MB_(99.78%)

@SuppressWarnings("java:S2234")
public class Solution {
//@ requires(*The length of the string parameter `w1` is less than or equal to 500 and is greater than or equal to 0.*);
//@ requires(*The length of the string parameter `w2` is less than or equal to 500 and is greater than or equal to 0.*);
//@ requires(*The string parameter `w1` consists of only lowercase English letters.*);
//@ requires(*The string parameter `w2` consists of only lowercase English letters.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the sum of the lengths of the string parameters `w1` and `w2`.*);
//@ ensures(*If the string parameters `w1` and `w2` are equal to "horse" and "ros", the integer result is equal to 3.*);
//@ ensures(*If the string parameters `w1` and `w2` are equal to "intention" and "execution", the integer result is equal to 5.*);
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