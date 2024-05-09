package g0401_0500.s0455_assign_cookies;

// #Easy #Array #Sorting #Greedy #2022_07_18_Time_12_ms_(41.00%)_Space_52.6_MB_(78.45%)

import java.util.Arrays;

public class Solution {
//@ requires(*The integer array parameter `g` must not be null.*);
//@ requires(*The integer array parameter `s` must not be null.*);
//@ requires(*The length of the integer array parameter `g` is greater than or equal to 1 and is less than or equal to 30000.*);
//@ requires(*The length of the integer array parameter `s` is greater than or equal to 0 and is less than or equal to 30000.*);
//@ requires(*All values in the integer array parameter `g` are greater than or equal to 1 and are less than or equal to 2147483647.*);
//@ requires(*All values in the integer array parameter `s` are greater than or equal to 1 and are less than or equal to 2147483647.*);
//@ ensures(*The integer result is the maximum number of content children that can be satisfied with the given greed factors and cookie sizes.*);
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int i = 0;
        int j = 0;
        //@ maintaining 0 <= i <= g.length;
        //@ maintaining 0 <= j <= s.length;
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