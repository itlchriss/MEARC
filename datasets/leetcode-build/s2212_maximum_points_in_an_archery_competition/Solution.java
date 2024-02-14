package g2201_2300.s2212_maximum_points_in_an_archery_competition;

// #Medium #Array #Bit_Manipulation #Recursion #Enumeration
// #2022_06_12_Time_7_ms_(78.16%)_Space_43.3_MB_(36.78%)

public class Solution {
    private final int[] ans = new int[12];
    private final int[] ans1 = new int[12];
    private int max = 0;
	//@ requires(*The input `numArrows` must be an integer greater than or equal to 1.*);
	//@ requires(*The input `aliceArrows` must be an integer array of size 12.*);
	//@ requires(*The elements of `aliceArrows` must be non-negative integers.*);
	//@ requires(*The sum of the elements in `aliceArrows` must be equal to `numArrows`.*);
	//@ ensures(*The output `bobArrows` must be an integer array of size 12.*);
	//@ ensures(*The elements of `bobArrows` must be non-negative integers.*);
	//@ ensures(*The sum of the elements in `bobArrows` must be equal to `numArrows`.*);

    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        solve(numArrows, aliceArrows, 11, 0);
        return ans1;
    }

    private void solve(int numArrows, int[] aliceArrows, int index, int sum) {
        if (numArrows <= 0 || index < 0) {
            if (max < sum) {
                max = sum;
                ans1[0] = Math.max(ans[0], ans[0] + numArrows);
                System.arraycopy(ans, 1, ans1, 1, 11);
            }
            return;
        }
        if (aliceArrows[index] + 1 <= numArrows) {
            ans[index] = aliceArrows[index] + 1;
            solve(numArrows - (aliceArrows[index] + 1), aliceArrows, index - 1, sum + index);
            ans[index] = 0;
        }
        solve(numArrows, aliceArrows, index - 1, sum);
    }
}