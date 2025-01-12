package g0401_0500.s0455_assign_cookies;

// #Easy #Array #Sorting #Greedy #2022_07_18_Time_12_ms_(41.00%)_Space_52.6_MB_(78.45%)

import java.util.Arrays;

public class Solution {
//@ requires(*The length of the integer array parameter `g` is less than or equal to 30000 and is greater than or equal to 1.*);
//@ requires(*The length of the integer array parameter `s` is less than or equal to 30000 and is greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `g` are less than or equal to 231 - 1 and are greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `s` are less than or equal to 231 - 1 and are greater than or equal to 1.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to the length of the integer array parameter `g`.*);
//@ ensures(*If the integer array parameter `g` is equal to [1,2,3] and the integer array parameter `s` is equal to [1,1], the integer result is equal to 1.*);
//@ ensures(*If the integer array parameter `g` is equal to [1,2] and the integer array parameter `s` is equal to [1,2,3], the integer result is equal to 2.*);
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