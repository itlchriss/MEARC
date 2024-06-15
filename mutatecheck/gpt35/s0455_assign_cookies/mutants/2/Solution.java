package g0401_0500.s0455_assign_cookies;

// #Easy #Array #Sorting #Greedy #2022_07_18_Time_12_ms_(41.00%)_Space_52.6_MB_(78.45%)

import java.util.Arrays;

public class Solution {
//@ requires (\forall int i; 0 <= i && i < g.length; g[i] >= 1 && g[i] <= Integer.MAX_VALUE);
//@ requires (\forall int j; 0 <= j && j < s.length; s[j] >= 1 && s[j] <= Integer.MAX_VALUE);
//@ ensures (\forall int i; 0 <= i && i < g.length; g[i] >= 1 && g[i] <= Integer.MAX_VALUE ==> (\exists int j; 0 <= j && j < s.length; s[j] >= g[i]));
//@ requires g.length > 0 && s.length > 0;
//@ ensures (\forall int i; 0 <= i && i < g.length; g[i] >= 1 && g[i] <= Integer.MAX_VALUE ==> (\forall int j; 0 <= j && j < s.length; s[j] < g[i]) ==> \result < i);
//@ ensures \result >= 0;
// requires pure
//@ requires g != null && s != null;
// requires public int findContentChildren(int[] g, int[] s)
//@ ensures \result <= g.length;
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int i = 0;
        int j = 0;
        //@ maintaining 0 <= i <= g.length;
        //@ maintaining 0 <= j <= s.length;
        while (i != g.length && j < s.length) {
            if (s[j] >= g[i]) {
                result++;
                i++;
            }
            j++;
        }
        return result;
    }
}
