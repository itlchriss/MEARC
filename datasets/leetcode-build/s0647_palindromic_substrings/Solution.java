package g0601_0700.s0647_palindromic_substrings;

// #Medium #Top_100_Liked_Questions #String #Dynamic_Programming #Big_O_Time_O(n^2)_Space_O(n)
// #2022_03_21_Time_2_ms_(98.77%)_Space_41.7_MB_(75.10%)

public class Solution {
    private void expand(char[] a, int l, int r, int[] res) {
        while (l >= 0 && r < a.length) {
            if (a[l] != a[r]) {
                return;
            } else {
                res[0]++;
                l--;
                r++;
            }
        }
    }
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is between 1 and 1000 (inclusive).*);
	//@ requires(*The input string `s` consists only of lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the number of palindromic substrings in the input string `s`.*);

    public int countSubstrings(String s) {
        char[] a = s.toCharArray();
        int[] res = {0};
        for (int i = 0; i < a.length; i++) {
            expand(a, i, i, res);
            expand(a, i, i + 1, res);
        }
        return res[0];
    }
}