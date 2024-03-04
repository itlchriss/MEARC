package g1401_1500.s1494_parallel_courses_ii;

// #Hard #Dynamic_Programming #Bit_Manipulation #Graph #Bitmask
// #2022_03_23_Time_325_ms_(42.24%)_Space_43.3_MB_(52.43%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` represents the number of courses, and it must be greater than or equal to 1.*);
//@ ensures(*The input array `relations` represents the prerequisite relationships between courses. Each element `relations[i]` must be an array of length 2, where `relations[i][0]` represents the prerequisite course and `relations[i][1]` represents the course that depends on the prerequisite. The prerequisite course and the dependent course must be integers between 1 and `n`, inclusive.*);
//@ ensures(*The input integer `k` represents the maximum number of courses that can be taken in one semester, and it must be greater than or equal to 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of semesters needed to take all courses.*);
//@ ensures(*The returned value must be greater than or equal to 1.*);
//@ ensures(*The test cases will be generated such that it is possible to take every course.*);
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        int[] pres = new int[n];
        for (int[] r : relations) {
            int prev = r[0] - 1;
            int next = r[1] - 1;
            pres[next] |= (1 << prev);
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int mask = 0; mask < dp.length; mask++) {
            int canTake = 0;
            for (int i = 0; i < n; i++) {
                // already taken
                if ((mask & (1 << i)) != 0) {
                    continue;
                }
                // satisfy all pres
                if ((mask & pres[i]) == pres[i]) {
                    canTake |= (1 << i);
                }
            }
            // loop each sub-masks
            for (int take = canTake; take > 0; take = (take - 1) & canTake) {
                if (Integer.bitCount(take) > k) {
                    continue;
                }
                dp[take | mask] = Math.min(dp[take | mask], dp[mask] + 1);
            }
        }
        return dp[(1 << n) - 1];
    }
}