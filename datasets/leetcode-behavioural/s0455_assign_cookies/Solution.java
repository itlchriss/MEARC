package g0401_0500.s0455_assign_cookies;

// #Easy #Array #Sorting #Greedy #2022_07_18_Time_12_ms_(41.00%)_Space_52.6_MB_(78.45%)

import java.util.Arrays;

public class Solution {
//@ ensures(*For each child `i` with greed factor `g[i]` and each cookie `j` with size `s[j]`, if `s[j] >= g[i]`, assign the cookie `j` to the child `i` to make the child content.*);
//@ ensures(*The integer array parameter `g` represents the greed factors of the children.*);
//@ ensures(*The integer array parameter `s` represents the sizes of the cookies.*);
//@ ensures(*The integer result is the maximum number of content children that can be achieved by assigning cookies based on the given conditions.*);
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int i = 0;
        int j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                result++;
                i++;
            }
            j++;
        }
        return result;
    }
}