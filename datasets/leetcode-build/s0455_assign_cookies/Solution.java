package g0401_0500.s0455_assign_cookies;

// #Easy #Array #Sorting #Greedy #2022_07_18_Time_12_ms_(41.00%)_Space_52.6_MB_(78.45%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The arrays `g` and `s` are not null.*);
	//@ requires(*The length of array `g` is greater than or equal to 1 and less than or equal to 3 * 10^4.*);
	//@ requires(*The length of array `s` is greater than or equal to 0 and less than or equal to 3 * 10^4.*);
	//@ requires(*The elements in array `g` are integers greater than or equal to 1 and less than or equal to 2^31 - 1.*);
	//@ requires(*The elements in array `s` are integers greater than or equal to 1 and less than or equal to 2^31 - 1.*);
	//@ ensures(*The method returns an integer representing the maximum number of content children.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
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