package g2901_3000.s2946_matrix_similarity_after_cyclic_shifts;

// #Easy #Array #Math #Matrix #Simulation #2023_12_26_Time_1_ms_(100.00%)_Space_44.5_MB_(14.88%)

public class Solution {
	//@ requires(*The input matrix `mat` is not null.*);
	//@ requires(*The length of `mat` is greater than or equal to 1 and less than or equal to 25.*);
	//@ requires(*The length of each row in `mat` is greater than or equal to 1 and less than or equal to 25.*);
	//@ requires(*Each element in `mat` is an integer greater than or equal to 1 and less than or equal to 25.*);
	//@ requires(*The value of `k` is greater than or equal to 1 and less than or equal to 50.*);
	//@ ensures(*The method returns a boolean value indicating whether the initial and final matrix are exactly the same.*);
	//@ ensures(*If the initial and final matrix are exactly the same, the method returns true.*);
	//@ ensures(*If the initial and final matrix are not exactly the same, the method returns false.*);
    public boolean areSimilar(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        k %= n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if ((i & 1) != 0) {
                    if (mat[i][j] != mat[i][(j - k + n) % n]) {
                        return false;
                    }
                } else {
                    if (mat[i][j] != mat[i][(j + k) % n]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}