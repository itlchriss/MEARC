package g2701_2800.s2734_lexicographically_smallest_string_after_substring_operation;

// #Medium #String #Greedy #2023_09_22_Time_12_ms_(86.26%)_Space_48.4_MB_(70.88%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of `s` is greater than or equal to 1.*);
	//@ ensures(*The output string is not null.*);
	//@ ensures(*The length of the output string is the same as the length of the input string `s`.*);
	//@ ensures(*The output string is lexicographically smaller than or equal to the input string `s`.*);
	//@ ensures(*The output string is obtained by performing the substring operation exactly once on the input string `s`.*);
    public String smallestString(String s) {
        int i = 0;
        int n = s.length();
        char[] a = s.toCharArray();
        while (i < n && a[i] == 'a') {
            i++;
            if (i == n) {
                a[n - 1] = 'z';
            }
        }
        while (i < n && a[i] != 'a') {
            --a[i++];
        }
        return String.valueOf(a);
    }
}