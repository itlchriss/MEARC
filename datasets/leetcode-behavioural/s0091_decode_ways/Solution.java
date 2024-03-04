package g0001_0100.s0091_decode_ways;

// #Medium #Top_Interview_Questions #String #Dynamic_Programming
// #Algorithm_II_Day_15_Dynamic_Programming #Dynamic_Programming_I_Day_10
// #2022_06_21_Time_2_ms_(66.37%)_Space_41.8_MB_(78.45%)

public class Solution {
//@ ensures(*If the string parameter `s` contains only digits and has a length between 1 and 100, the integer result is the number of ways to decode the string `s`.*);
//@ ensures(*If the string parameter `s` contains leading zeros, the integer result is 0.*);
//@ ensures(*If the string parameter `s` does not have a valid mapping to a character starting with 0, the integer result is 0.*);
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