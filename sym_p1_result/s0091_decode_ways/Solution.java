package g0001_0100.s0091_decode_ways;

// #Medium #Top_Interview_Questions #String #Dynamic_Programming
// #Algorithm_II_Day_15_Dynamic_Programming #Dynamic_Programming_I_Day_10
// #2022_06_21_Time_2_ms_(66.37%)_Space_41.8_MB_(78.45%)

public class Solution {
//@ requires(*A message containing letters from `A-Z` can be encoded into numbers using the following mapping:*);
//@ requires(*'A' -> "1" 'B' -> "2" ... 'Z' -> "26"*);
//@ requires(*To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways).*);
//@ requires(*For example, `"11106"` can be mapped into:*);
//@ requires(*`"AAJF"` with the grouping `(1 1 10 6)`*);
//@ requires(*`"KJF"` with the grouping `(11 10 6)`*);
//@ requires(*Note that the grouping `(1 11 06)` is invalid because `"06"` cannot be mapped into `'F'` since `"6"` is different from `"06"`.*);
//@ requires(*The answer is guaranteed to fit in a 32-bit integer.*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "12"*);
//@ requires(*Output: 2*);
//@ requires(*Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "226"*);
//@ requires(*Output: 3*);
//@ requires(*Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "0"*);
//@ requires(*Output: 0*);
//@ requires(*Explanation: There is no character that is mapped to a number starting with 0.*);
//@ requires(*The only valid mappings with 0 are 'J' -> "10" and 'T' -> "20", neither of which start with 0.*);
//@ requires(*Hence, there are no valid ways to decode this since all digits need to be mapped.*);
//@ requires(*Example 4:*);
//@ requires(*Input: s = "06"*);
//@ requires(*Output: 0*);
//@ requires(*Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= s.length <= 100`*);
//@ requires(*param_s contains only digits and may contain leading zero(s).*);
//@ ensures(*Given a string param_s containing only digits, the result is the number of ways to decode it.*);
    public int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int[] f = new int[n + 1];
        // Auxiliary
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            // Calculate the independent number
            if (s.charAt(i - 1) != '0') {
                // As long as the current character is not 0, it means that the previous decoding
                // number can be inherited
                f[i] = f[i - 1];
            }
            // Calculate the number of combinations
            int twodigits = (s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0');
            if (twodigits >= 10 && twodigits <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}