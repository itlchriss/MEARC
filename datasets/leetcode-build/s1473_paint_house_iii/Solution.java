package g1401_1500.s1473_paint_house_iii;

// #Hard #Array #Dynamic_Programming #2022_03_29_Time_26_ms_(89.13%)_Space_42.9_MB_(91.75%)

public class Solution {
    private int[][][] memo;
    private int[] houses;
    private int nColors;
    private int[][] cost;
	//@ requires(*1. The length of the `houses` array must be equal to the length of the `cost` array.*);
	//@ requires(*2. The length of each row in the `cost` matrix must be equal to `n`.*);
	//@ requires(*3. The values in the `houses` array must be between 0 and `n`, inclusive.*);
	//@ requires(*4. The value of `m` must be between 1 and 100, inclusive.*);
	//@ requires(*5. The value of `n` must be between 1 and 20, inclusive.*);
	//@ requires(*6. The value of `target` must be between 1 and `m`, inclusive.*);
	//@ requires(*7. The values in the `cost` matrix must be between 1 and 10^4, inclusive.*);
	//@ ensures(*1. The method should return an integer representing the minimum cost of painting all the remaining houses.*);
	//@ ensures(*2. If it is not possible to paint the houses in such a way that there are exactly `target` neighborhoods, the method should return -1.*);

    public int minCost(int[] houses, int[][] cost, int nColors, int tGroups) {
        this.cost = cost;
        this.houses = houses;
        this.memo = new int[houses.length][nColors + 1][tGroups + 1];
        this.nColors = nColors;

        int dp = dp(0, 0, tGroups);
        return dp == Integer.MAX_VALUE ? -1 : dp;
    }

    private int dp(int ithEl, int prevClr, int tGroups) {
        if (ithEl == houses.length) {
            return tGroups == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (ithEl < houses.length && tGroups < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[ithEl][prevClr][tGroups] == 0) {
            int currC = houses[ithEl];
            int res = Integer.MAX_VALUE;
            if (currC != 0) {
                int grpLeft = currC == prevClr ? tGroups : tGroups - 1;
                res = dp(ithEl + 1, currC, grpLeft);
            } else {
                for (int clr = 1; clr <= nColors; clr++) {
                    int grpLeft = clr == prevClr ? tGroups : tGroups - 1;
                    int dp = dp(ithEl + 1, clr, grpLeft);
                    res =
                            Math.min(
                                    res,
                                    dp != Integer.MAX_VALUE
                                            ? cost[ithEl][clr - 1] + dp
                                            : Integer.MAX_VALUE);
                }
            }
            memo[ithEl][prevClr][tGroups] = res;
        }
        return memo[ithEl][prevClr][tGroups];
    }
}