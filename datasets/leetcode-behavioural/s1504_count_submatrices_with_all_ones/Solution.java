package g1501_1600.s1504_count_submatrices_with_all_ones;

// #Medium #Array #Dynamic_Programming #Matrix #Stack #Monotonic_Stack
// #2022_04_07_Time_9_ms_(85.86%)_Space_43.1_MB_(87.32%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input matrix `mat` is not null.*);
//@ ensures(*2. The input matrix `mat` has at least one row and one column.*);
//@ ensures(*3. The input matrix `mat` contains only binary values (0 or 1).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method returns an integer representing the number of submatrices that have all ones.*);
//@ ensures(*2. The method does not modify the input matrix `mat`.*);
//@ ensures(*3. The method terminates and returns a result in a reasonable amount of time for inputs within the given constraints.*);
    public int numSubmat(int[][] mat) {
        int[][] dp = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            int c = 0;
            for (int j = mat[0].length - 1; j >= 0; j--) {
                if (mat[i][j] == 1) {
                    c++;
                } else {
                    c = 0;
                }
                dp[i][j] = c;
            }
        }
        int ans = 0;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int x = Integer.MAX_VALUE;
                for (int k = i; k < mat.length; k++) {
                    x = Math.min(x, dp[k][j]);
                    ans += x;
                }
            }
        }
        return ans;
    }
}