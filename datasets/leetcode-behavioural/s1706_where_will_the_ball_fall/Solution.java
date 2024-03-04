package g1701_1800.s1706_where_will_the_ball_fall;

// #Medium #Array #Dynamic_Programming #Depth_First_Search #Matrix #Simulation
// #Level_2_Day_1_Implementation/Simulation #2022_04_24_Time_2_ms_(64.55%)_Space_54.5_MB_(25.38%)

@SuppressWarnings("java:S135")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input grid must be a 2-D array of size m x n.*);
//@ ensures(*Each cell in the grid must contain either a 1 or a -1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array must be of size n.*);
//@ ensures(*Each element in the output array must be the column that the ball falls out of at the bottom after dropping the ball from the corresponding column at the top.*);
//@ ensures(*If a ball gets stuck in the box, the corresponding element in the output array must be -1.*);
    public int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] res = new int[n];
        for (int j = 0; j < n; j++) {
            int currentJ = j;
            int currentI = 0;
            while (currentJ < n && currentI < m) {
                if (grid[currentI][currentJ] == 1) {
                    currentJ++;
                    if (currentJ < n && grid[currentI][currentJ] == 1) {
                        currentI++;
                    } else {
                        break;
                    }
                } else {
                    currentJ--;
                    if (currentJ >= 0 && grid[currentI][currentJ] == -1) {
                        currentI++;
                    } else {
                        break;
                    }
                }
            }
            if (currentI == m) {
                res[j] = currentJ;
            } else {
                res[j] = -1;
            }
        }
        return res;
    }
}