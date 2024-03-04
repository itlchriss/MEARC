package g1601_1700.s1659_maximize_grid_happiness;

// #Hard #Dynamic_Programming #Bit_Manipulation #Bitmask #Memoization
// #2022_04_23_Time_95_ms_(75.00%)_Space_53.1_MB_(58.33%)

public class Solution {
    private int m;
    private int n;
    private int[][][][][] dp;
    private int notPlace = 0;
    private int intro = 1;
    private int extro = 2;
    private int mod;
//@ ensures(*Preconditions:*);
//@ ensures(*The values of m and n must be positive integers.*);
//@ ensures(*The values of introvertsCount and extrovertsCount must be non-negative integers.*);
//@ ensures(*The sum of introvertsCount and extrovertsCount must be less than or equal to the total number of cells in the grid (m * n).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer representing the maximum possible grid happiness.*);
//@ ensures(*The grid happiness is calculated by summing the happiness of each person in the grid.*);
//@ ensures(*The happiness of an introvert is calculated as 120 (starting happiness) minus 30 multiplied by the number of neighbors (introvert or extrovert).*);
//@ ensures(*The happiness of an extrovert is calculated as 40 (starting happiness) plus 20 multiplied by the number of neighbors (introvert or extrovert).*);
//@ ensures(*The neighbors of a person are the cells directly adjacent to their cell (north, east, south, and west).*);
//@ ensures(*The happiness of each person should be calculated correctly based on their type (introvert or extrovert) and the number of neighbors they have.*);
//@ ensures(*The maximum possible grid happiness should be determined correctly based on the given constraints.*);

    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        this.m = m;
        this.n = n;
        int numOfState = (int) Math.pow(3, n);
        this.dp = new int[m][n][introvertsCount + 1][extrovertsCount + 1][numOfState];
        this.mod = numOfState / 3;
        return dfs(0, 0, introvertsCount, extrovertsCount, 0);
    }

    private int dfs(int x, int y, int ic, int ec, int state) {
        if (x == m) {
            return 0;
        } else if (y == n) {
            return dfs(x + 1, 0, ic, ec, state);
        }
        if (dp[x][y][ic][ec][state] != 0) {
            return dp[x][y][ic][ec][state];
        }
        // 1 - not place
        int max = dfs(x, y + 1, ic, ec, (state % mod) * 3);
        int up = state / mod;
        int left = state % 3;
        // 2 - place intro
        if (ic > 0) {
            int temp = 120;
            if (x > 0 && up != notPlace) {
                temp -= 30;
                temp += up == intro ? -30 : 20;
            }
            if (y > 0 && left != notPlace) {
                temp -= 30;
                temp += left == intro ? -30 : 20;
            }
            int nextState = state;
            nextState %= mod;
            nextState *= 3;
            nextState += intro;
            max = Math.max(max, temp + dfs(x, y + 1, ic - 1, ec, nextState));
        }
        // 3 - place extro
        if (ec > 0) {
            int temp = 40;
            if (x > 0 && up != notPlace) {
                temp += 20;
                temp += up == intro ? -30 : 20;
            }
            if (y > 0 && left != notPlace) {
                temp += 20;
                temp += left == intro ? -30 : 20;
            }
            int nextState = state;
            nextState %= mod;
            nextState *= 3;
            nextState += extro;
            max = Math.max(max, temp + dfs(x, y + 1, ic, ec - 1, nextState));
        }
        dp[x][y][ic][ec][state] = max;
        return max;
    }
}