package g2101_2200.s2140_solving_questions_with_brainpower;

// #Medium #Array #Dynamic_Programming #2022_06_05_Time_5_ms_(98.77%)_Space_92.6_MB_(94.67%)

public class Solution {
	//@ requires(*The input `questions` is a 2D integer array.*);
	//@ requires(*The length of `questions` is greater than or equal to 1 and less than or equal to 10^5.*);
	//@ requires(*Each element in `questions` is an array of length 2.*);
	//@ requires(*The first element of each array in `questions` represents the number of points that can be earned by solving the question.*);
	//@ requires(*The second element of each array in `questions` represents the number of questions that cannot be solved after solving the current question.*);
	//@ ensures(*The method returns a long value representing the maximum points that can be earned for the exam.*);
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] memo = new long[n];
        memo[n - 1] = questions[n - 1][0];
        for (int i = n - 2; i >= 0; i--) {
            if (i + questions[i][1] + 1 < n) {
                memo[i] = Math.max(memo[i + 1], questions[i][0] + memo[i + questions[i][1] + 1]);
            } else {
                memo[i] = Math.max(memo[i + 1], questions[i][0]);
            }
        }
        return memo[0];
    }
}