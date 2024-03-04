package g0601_0700.s0688_knight_probability_in_chessboard;

// #Medium #Dynamic_Programming #2022_03_22_Time_7_ms_(85.13%)_Space_42.5_MB_(58.12%)

public class Solution {
    private final int[][] directions =
            new int[][] {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, -1}, {2, 1}, {1, -2}, {-1, -2}};
    private double[][][] probabilityGiven;
//@ ensures(*Method behavioural requirements:*);
//@ ensures(*The integer parameter `n` must be greater than or equal to 1 and less than or equal to 25.*);
//@ ensures(*The integer parameter `k` must be greater than or equal to 0 and less than or equal to 100.*);
//@ ensures(*The integer parameter `row` must be greater than or equal to 0 and less than or equal to `n`.*);
//@ ensures(*The integer parameter `column` must be greater than or equal to 0 and less than or equal to `n`.*);
//@ ensures(*The double result is the probability that the knight remains on the board after making exactly `k` moves.*);

    public double knightProbability(int n, int k, int row, int column) {
        probabilityGiven = new double[n][n][k + 1];
        return probability(row, column, k, n);
    }

    private double probability(int row, int column, int k, int n) {
        if (k == 0) {
            return 1.0;
        } else if (probabilityGiven[row][column][k] != 0) {
            return probabilityGiven[row][column][k];
        } else {
            double p = 0d;
            for (int[] dir : directions) {
                if (isValid(row + dir[0], column + dir[1], n)) {
                    p += probability(row + dir[0], column + dir[1], k - 1, n);
                }
            }
            probabilityGiven[row][column][k] = p / 8.0;
            return probabilityGiven[row][column][k];
        }
    }

    private boolean isValid(int row, int column, int n) {
        return (row >= 0 && row < n && column >= 0 && column < n);
    }
}