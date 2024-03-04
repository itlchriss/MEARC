package g2901_3000.s2983_palindrome_rearrangement_queries;

// #Hard #String #Hash_Table #Prefix_Sum #2024_01_18_Time_14_ms_(88.19%)_Space_96.6_MB_(78.74%)

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("java:S6541")
public class Solution {
    private int n;

    // get associated index in the other half
    private int opp(int i) {
        return n - 1 - i;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The length of the string `s` is even.*);
//@ ensures(*The length of the 2D integer array `queries` is at least 1.*);
//@ ensures(*Each query in `queries` is represented by a 4-element array.*);
//@ ensures(*The first element of each query represents the starting index of the first substring.*);
//@ ensures(*The second element of each query represents the ending index of the first substring.*);
//@ ensures(*The third element of each query represents the starting index of the second substring.*);
//@ ensures(*The fourth element of each query represents the ending index of the second substring.*);
//@ ensures(*The starting index of the first substring is between 0 and `n/2`, where `n` is the length of `s`.*);
//@ ensures(*The ending index of the first substring is between the starting index and `n/2`, where `n` is the length of `s`.*);
//@ ensures(*The starting index of the second substring is between `n/2` and `n`, where `n` is the length of `s`.*);
//@ ensures(*The ending index of the second substring is between the starting index and `n`, where `n` is the length of `s`.*);
//@ ensures(*Each query is independent of the others.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a 0-indexed array `answer`.*);
//@ ensures(*The length of `answer` is equal to the number of queries in `queries`.*);
//@ ensures(*Each element of `answer` represents whether it is possible to make `s` a palindrome by performing the operations specified by the corresponding query.*);
//@ ensures(*If it is possible to make `s` a palindrome by performing the operations specified by a query, the corresponding element in `answer` is `true`.*);
//@ ensures(*If it is not possible to make `s` a palindrome by performing the operations specified by a query, the corresponding element in `answer` is `false`.*);

    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
        int[] fq = new int[26];
        int m = queries.length;
        boolean[] ret = new boolean[m];
        n = s.length();
        // check that both halves contain the same letters
        for (int i = 0; i < n / 2; i++) {
            fq[s.charAt(i) - 'a']++;
        }
        for (int i = n / 2; i < n; i++) {
            fq[s.charAt(i) - 'a']--;
        }
        for (int em : fq) {
            if (em != 0) {
                return ret;
            }
        }
        // find the first and the last characters in the first half
        // that do not match with their associated character in
        // the second half
        int problemPoint = -1;
        int lastProblem = -1;
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(opp(i))) {
                if (problemPoint == -1) {
                    problemPoint = i;
                }
                lastProblem = i;
            }
        }
        // if already a palindrome
        if (problemPoint == -1) {
            Arrays.fill(ret, true);
            return ret;
        }
        // the idea is that at least one of the intervals in the
        // query has to cover the first pair of different characters.
        // But depending on how far the other end of that interval
        // goes, the requirements for the other interval are lessened
        int[] dpFirst = new int[n / 2 + 1];
        int[] dpSecond = new int[n + 1];
        Arrays.fill(dpFirst, -1);
        Arrays.fill(dpSecond, -1);
        // assuming the first interval covers the first problem,
        // and then extends to the right
        int rptr = opp(problemPoint);
        Map<Character, Integer> mp = new HashMap<>();
        for (int i = problemPoint; i < n / 2; i++) {
            mp.compute(s.charAt(i), (k, v) -> v == null ? 1 : v + 1);
            // the burden for the left end of the second interval does not change;
            // it needs to go at least until the last problematic match. But the
            // requirements for the right end do. If we can rearrange the characters
            // in the left half to match the right end of the right interval, this
            // means we do not need the right end of the right interval to go too far
            while (mp.containsKey(s.charAt(rptr))
                    || (rptr >= n / 2 && s.charAt(rptr) == s.charAt(opp(rptr)) && mp.size() == 0)) {
                mp.computeIfPresent(s.charAt(rptr), (k, v) -> v == 1 ? null : v - 1);
                rptr--;
            }
            dpFirst[i] = rptr;
        }
        // mirrored discussion assuming it is the right interval that takes
        // care of the first problematic pair
        int lptr = problemPoint;
        mp.clear();
        for (int i = opp(problemPoint); i >= n / 2; i--) {
            mp.compute(s.charAt(i), (k, v) -> v == null ? 1 : v + 1);
            while (mp.containsKey(s.charAt(lptr))
                    || (lptr < n / 2 && s.charAt(lptr) == s.charAt(opp(lptr)) && mp.size() == 0)) {
                mp.computeIfPresent(s.charAt(lptr), (k, v) -> v == 1 ? null : v - 1);
                lptr++;
            }
            dpSecond[i] = lptr;
        }
        for (int i = 0; i < m; i++) {
            int a = queries[i][0];
            int b = queries[i][1];
            int c = queries[i][2];
            int d = queries[i][3];
            // if either interval the problematic interval on its side, it does not matter
            // what happens with the other interval
            if (a <= problemPoint && b >= lastProblem
                    || c <= opp(lastProblem) && d >= opp(problemPoint)) {
                ret[i] = true;
                continue;
            }
            // if the left interval covers the first problem, we use
            // dp to figure out if the right one is large enough
            if (a <= problemPoint
                    && b >= problemPoint
                    && d >= dpFirst[b]
                    && c <= opp(lastProblem)) {
                ret[i] = true;
            }
            // similarly for the case where the right interval covers
            // the first problem
            if (d >= opp(problemPoint)
                    && c <= opp(problemPoint)
                    && a <= dpSecond[c]
                    && b >= lastProblem) {
                ret[i] = true;
            }
        }
        return ret;
    }
}