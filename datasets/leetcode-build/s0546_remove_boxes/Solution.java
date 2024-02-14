package g0501_0600.s0546_remove_boxes;

// #Hard #Array #Dynamic_Programming #Memoization
// #2022_08_02_Time_45_ms_(95.58%)_Space_52.3_MB_(87.17%)

public class Solution {
    private Integer[][][] dp;
	//@ requires(*The input array `boxes` is not null.*);
	//@ requires(*The length of the input array `boxes` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `boxes` is a positive number.*);
	//@ requires(*The maximum value of each element in the input array `boxes` is 100.*);
	//@ ensures(*The method returns an integer value representing the maximum points that can be obtained.*);
	//@ ensures(*The input array `boxes` is not modified.*);
	//@ ensures(*The input array `boxes` does not contain any boxes.*);

    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        dp = new Integer[n][n][n + 1];
        return helper(boxes, 0, n - 1, 0);
    }

    private int helper(int[] boxes, int left, int right, int count) {
        if (left > right) {
            return 0;
        }
        if (dp[left][right][count] != null) {
            return dp[left][right][count];
        }
        int leftDash = left;
        int countDash = count;
        while (leftDash < right && boxes[leftDash] == boxes[leftDash + 1]) {
            leftDash++;
            countDash++;
        }
        int ans;
        ans = helper(boxes, leftDash + 1, right, 0) + (countDash + 1) * (countDash + 1);
        for (int i = leftDash + 1; i <= right; i++) {
            if (boxes[i] == boxes[leftDash]) {
                int temp =
                        helper(boxes, leftDash + 1, i - 1, 0)
                                + helper(boxes, i, right, countDash + 1);
                ans = Math.max(temp, ans);
            }
        }
        dp[left][right][count] = ans;
        return ans;
    }
}