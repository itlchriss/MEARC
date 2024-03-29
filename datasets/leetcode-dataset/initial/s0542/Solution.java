package g0501_0600.s0542_01_matrix;

// #Medium #Array #Dynamic_Programming #Breadth_First_Search #Matrix
// #Algorithm_I_Day_9_Breadth_First_Search_Depth_First_Search
// #Graph_Theory_I_Day_5_Matrix_Related_Problems
// #2022_08_02_Time_7_ms_(95.83%)_Space_46.4_MB_(86.74%)

import java.util.Arrays;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given an `m x n` binary matrix `mat`, return _the distance of the nearest_ `0` _for each cell_.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int[][] updateMatrix(int[][] mat) {
        int[][] dist = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE - 100000);
        }
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                } else {
                    if (i > 0) {
                        dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                    }
                    if (j > 0) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                    }
                }
            }
        }
        for (int i = mat.length - 1; i >= 0; i--) {
            for (int j = mat[0].length - 1; j >= 0; j--) {
                if (i < mat.length - 1) {
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                }
                if (j < mat[0].length - 1) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                }
            }
        }
        return dist;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
