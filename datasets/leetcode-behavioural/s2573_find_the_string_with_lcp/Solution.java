package g2501_2600.s2573_find_the_string_with_lcp;

// #Hard #String #Dynamic_Programming #Greedy #Union_Find
// #2023_08_21_Time_5_ms_(100.00%)_Space_142.3_MB_(10.34%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input matrix `lcp` is not null.*);
//@ ensures(*The length of `lcp` is equal to the length of `lcp[i]`.*);
//@ ensures(*The length of `lcp` is greater than 0 and less than or equal to 1000.*);
//@ ensures(*The values in `lcp` are non-negative integers.*);
//@ ensures(*The values in `lcp` are less than or equal to the length of `lcp`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string `word` is the alphabetically smallest string that corresponds to `lcp`.*);
//@ ensures(*If there is no such string, the returned string is empty.*);
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] arr = new char[n];
        arr[0] = 'a';
        char test;
        boolean found;
        for (int i = 1; i < n; ++i) {
            test = 'a';
            found = false;
            for (int j = 0; j < i; ++j) {
                test = (char) Math.max(test, arr[j]);
                if (lcp[i][j] != 0) {
                    found = true;
                    arr[i] = arr[j];
                    break;
                }
            }
            if (found) {
                continue;
            }
            ++test;
            arr[i] = test;
            if (test > 'z') {
                return "";
            }
        }
        int[][] dp = new int[n + 1][n + 1];
        int val;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (arr[i] != arr[j]) {
                    val = 0;
                } else {
                    val = 1 + dp[i + 1][j + 1];
                }
                dp[i][j] = val;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (dp[i][j] != lcp[i][j]) {
                    return "";
                }
            }
        }
        return String.valueOf(arr);
    }
}