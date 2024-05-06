package g0101_0200.s0115_distinct_subsequences;

// #Hard #String #Dynamic_Programming #2022_06_23_Time_2_ms_(100.00%)_Space_40.2_MB_(99.14%)

public class Solution {
//@ ensures(*The integer result is the number of distinct subsequences of string `s` which equals string `t`.*);
//@ ensures(*The length of string `s` is greater than or equal to 1 and is less than or equal to 1000.*);
//@ ensures(*The length of string `t` is greater than or equal to 1 and is less than or equal to 1000.*);
//@ ensures(*All characters in string `s` and string `t` are English letters.*);
    public int numDistinct(String text, String text2) {
        if (text.length() < text2.length()) {
            return 0;
        }
        if (text.length() == text2.length()) {
            return (text.equals(text2) ? 1 : 0);
        }
        int move = text.length() - text2.length() + 2;
        // Only finite number of character in s can occupy first position in T. Same applies for
        // every character in T.
        int[] dp = new int[move];
        int j = 1;
        int k = 1;
        for (int i = 0; i < text2.length(); i++) {
            boolean firstMatch = true;
            for (; j < move; j++) {
                if (text2.charAt(i) == text.charAt(i + j - 1)) {
                    if (firstMatch) {
                        // Keep track of first match. To avoid useless comparisons on next
                        // iteration.
                        k = j;
                        firstMatch = false;
                    }
                    if (i == 0) {
                        dp[j] = 1;
                    }
                    dp[j] += dp[j - 1];
                } else {
                    dp[j] = dp[j - 1];
                }
            }
            // No match found for current character of t in s. No point in checking others.
            if (dp[move - 1] == 0) {
                return 0;
            }
            j = k;
        }
        return dp[move - 1];
    }
}