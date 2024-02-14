package g1801_1900.s1888_minimum_number_of_flips_to_make_the_binary_string_alternating;

// #Medium #String #Greedy #2022_05_06_Time_30_ms_(75.70%)_Space_55.2_MB_(18.22%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*The characters in the input string `s` are either '0' or '1'.*);
	//@ ensures(*The output is an integer representing the minimum number of type-2 operations needed to make the string `s` alternating.*);
    public int minFlips(String s) {
        int n = s.length();
        String localStr = s + s;
        char[] t = localStr.toCharArray();
        char[] a = new char[n + n];
        char[] b = new char[n + n];
        for (int i = 0; i < n + n; i++) {
            if (i % 2 == 0) {
                a[i] = '1';
                b[i] = '0';
            } else {
                a[i] = '0';
                b[i] = '1';
            }
        }
        int f = 0;
        int sec = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n + n; i++) {
            if (a[i] != t[i]) {
                f++;
            }
            if (b[i] != t[i]) {
                sec++;
            }
            if (i >= n) {
                if (a[i - n] != t[i - n]) {
                    f--;
                }
                if (b[i - n] != t[i - n]) {
                    sec--;
                }
            }
            if (i >= n - 1) {
                ans = Math.min(ans, Math.min(f, sec));
            }
        }
        return ans;
    }
}