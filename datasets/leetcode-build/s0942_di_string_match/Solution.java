package g0901_1000.s0942_di_string_match;

// #Easy #Array #String #Math #Greedy #Two_Pointers
// #2022_03_30_Time_4_ms_(33.74%)_Space_48.7_MB_(20.18%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is greater than or equal to 1 and less than or equal to 10^- Each character in the input string `s` is either 'I' or 'D'.*);
	//@ ensures(*The output is an array of integers representing a permutation `perm` of length `n + 1`, where `n` is the length of the input string `s`.*);
	//@ ensures(*The elements of the output array `perm` are integers in the range [0, n].*);
	//@ ensures(*The output array `perm` satisfies the conditions specified by the input string `s`:*);
	//@ ensures(*- If `s[i] == 'I'`, then `perm[i] < perm[i + 1]`.*);
	//@ ensures(*- If `s[i] == 'D'`, then `perm[i] > perm[i + 1]`.*);
	//@ ensures(*The output array `perm` is a valid permutation, meaning it contains all integers in the range [0, n] exactly once.*);
    public int[] diStringMatch(String s) {
        int[] arr = new int[s.length() + 1];
        int max = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'D') {
                arr[i] = max;
                max--;
            }
        }
        for (int i = s.length() - 1; i >= 0 && max > 0; i--) {
            if (s.charAt(i) == 'I' && arr[i + 1] == 0) {
                arr[i + 1] = max;
                max--;
            }
        }
        for (int i = 0; i < arr.length && max > 0; i++) {
            if (arr[i] == 0) {
                arr[i] = max;
                max--;
            }
        }

        return arr;
    }
}