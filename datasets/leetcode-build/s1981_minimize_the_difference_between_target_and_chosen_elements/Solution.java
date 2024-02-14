package g1901_2000.s1981_minimize_the_difference_between_target_and_chosen_elements;

// #Medium #Array #Dynamic_Programming #Matrix
// #2022_05_21_Time_480_ms_(90.78%)_Space_53.7_MB_(66.51%)

public class Solution {
	//@ requires(*1. The input matrix `mat` is not null.*);
	//@ requires(*2. The input matrix `mat` has at least one row.*);
	//@ requires(*3. The input matrix `mat` has at least one column.*);
	//@ requires(*4. The input matrix `mat` has the same number of columns for each row.*);
	//@ requires(*5. The target value `target` is a positive integer.*);
	//@ requires(*6. The target value `target` is less than or equal to 800.*);
	//@ ensures(*1. The output is an integer representing the minimum absolute difference.*);
	//@ ensures(*2. The output is non-negative.*);
	//@ ensures(*3. The output is the absolute difference between the target value and the sum of the chosen elements.*);
	//@ ensures(*4. The chosen elements are one integer from each row of the matrix.*);
	//@ ensures(*5. The sum of the chosen elements is equal to or closest to the target value.*);
    public int minimizeTheDifference(int[][] mat, int target) {
        int m = mat.length;
        boolean[][] seen = new boolean[m][m * 70 + 1];
        dfs(0, mat, 0, seen);
        for (int i = 0; true; i++) {
            for (int j = 0, sign = 1; j < 2; j++, sign *= -1) {
                int k = target - i * sign;
                if (k >= 0 && k <= m * 70 && seen[m - 1][k]) {
                    return i;
                }
            }
        }
    }

    private void dfs(int i, int[][] mat, int sum, boolean[][] seen) {
        if (i == mat.length) {
            return;
        }

        for (int j = 0; j < mat[i].length; j++) {
            if (!seen[i][sum + mat[i][j]]) {
                seen[i][sum + mat[i][j]] = true;
                dfs(i + 1, mat, sum + mat[i][j], seen);
            }
        }
    }
}