package g1301_1400.s1301_number_of_paths_with_max_score;

// #Hard #Array #Dynamic_Programming #Matrix #2022_03_14_Time_14_ms_(72.31%)_Space_46.1_MB_(23.08%)

import java.util.List;

public class Solution {
    private static final int[][] DIRECTIONS = new int[][] {{1, 0}, {0, 1}, {1, 1}};
//@ ensures(*Preconditions:*);
//@ ensures(*The input `board` is a non-empty list of strings.*);
//@ ensures(*The length of each string in `board` is equal to the length of `board`.*);
//@ ensures(*The length of `board` is between 2 and 100 (inclusive).*);
//@ ensures(*Each string in `board` consists of characters 'E', 'X', 'S', or numeric characters '1' to '9'.*);
//@ ensures(*There is at least one 'E' character and one 'S' character in `board`.*);
//@ ensures(*There is no 'E' character or 'S' character in the same string as an 'X' character.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a list of two integers.*);
//@ ensures(*The first integer represents the maximum sum of numeric characters that can be collected.*);
//@ ensures(*The second integer represents the number of paths that result in the maximum sum.*);
//@ ensures(*The output is taken modulo 10^9 + 7.*);
//@ ensures(*If there is no path from the bottom right square to the top left square, the output is [0, 0].*);

    public int[] pathsWithMaxScore(List<String> board) {
        int rows = board.size();
        int columns = board.get(0).length();
        int[][][] dp = new int[rows][columns][2];
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = columns - 1; c >= 0; c--) {
                char current = board.get(r).charAt(c);
                if (current == 'S') {
                    dp[r][c][0] = 0;
                    dp[r][c][1] = 1;
                } else if (current != 'X') {
                    int maxScore = 0;
                    int paths = 0;
                    int currentScore = current == 'E' ? 0 : current - '0';
                    for (int[] dir : DIRECTIONS) {
                        int nextR = r + dir[0];
                        int nextC = c + dir[1];
                        if (nextR < rows && nextC < columns && dp[nextR][nextC][1] > 0) {
                            if (dp[nextR][nextC][0] + currentScore > maxScore) {
                                maxScore = dp[nextR][nextC][0] + currentScore;
                                paths = dp[nextR][nextC][1];
                            } else if (dp[nextR][nextC][0] + currentScore == maxScore) {
                                paths = (paths + dp[nextR][nextC][1]) % 1000000007;
                            }
                        }
                    }
                    dp[r][c][0] = maxScore;
                    dp[r][c][1] = paths;
                }
            }
        }
        return new int[] {dp[0][0][0], dp[0][0][1]};
    }
}