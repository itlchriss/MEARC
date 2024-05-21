package g0901_1000.s0942_di_string_match;

// #Easy #Array #String #Math #Greedy #Two_Pointers
// #2022_03_30_Time_4_ms_(33.74%)_Space_48.7_MB_(20.18%)

public class Solution {
//@ requires(*A permutation `perm` of `n + 1` integers of all the integers in the range `[0, n]` can be represented as a string param_s of length `n` where:*);
//@ requires(*`s[i] == 'I'` if `perm[i] < perm[i + 1]`, and*);
//@ requires(*`s[i] == 'D'` if `perm[i] > perm[i + 1]`.*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "IDID"*);
//@ requires(*Output: [0,4,1,3,2]*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = "III"*);
//@ requires(*Output: [0,1,2,3]*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "DDI"*);
//@ requires(*Output: [3,2,0,1]*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= s.length <= 10<sup>5</sup></code>*);
//@ requires(*`s[i]` is either `'I'` or `'D'`.*);
//@ ensures(*Given a string param_s, reconstruct the permutation `perm` and the result is it.*);
//@ ensures(*If there are multiple valid permutations perm, the result is any of them.*);
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