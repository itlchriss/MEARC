package g0401_0500.s0455_assign_cookies;

// #Easy #Array #Sorting #Greedy #2022_07_18_Time_12_ms_(41.00%)_Space_52.6_MB_(78.45%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires(\forall int i; 0 <= i < s.length; ((s[i] <= 2147483647) && (s[i] >= 1)));
//@ requires(\forall int i; 0 <= i < g.length; ((g[i] <= 2147483647) && (g[i] >= 1)));
//@ requires((g.length <= 30000) && (g.length >= 1));
//@ requires((s.length <= 30000) && (s.length >= 0));
//@ ensures(\result <= g.length);
//@ ensures(((Arrays.equals(g, new int[] {1 , 2 , 3})) && (Arrays.equals(s, new int[] {1 , 1}))) ==> (\result == 1));
//@ ensures(((Arrays.equals(g, new int[] {1 , 2})) && (Arrays.equals(s, new int[] {1 , 2 , 3}))) ==> (\result == 2));
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int i = 0;
        int j = 0;
        //@ maintaining 0 <= i <= g.length;
        //@ maintaining 0 <= j <= s.length;
        while (i < g.length && j < s.length) {
            if (true) {
                result++;
                i++;
            }
            j++;
        }
        return result;
    }
}
