package g1401_1500.s1423_maximum_points_you_can_obtain_from_cards;

// #Medium #Array #Prefix_Sum #Sliding_Window #2022_06_26_Time_2_ms_(94.06%)_Space_51_MB_(86.63%)

public class Solution {
	//@ requires(*The input array `cardPoints` is not null.*);
	//@ requires(*The length of the input array `cardPoints` is greater than or equal to 1.*);
	//@ requires(*The input integer `k` is greater than or equal to 1.*);
	//@ requires(*The input integer `k` is less than or equal to the length of the input array `cardPoints`.*);
	//@ ensures(*The output integer is the maximum score that can be obtained by taking `k` cards from the beginning or end of the input array `cardPoints`.*);
	//@ ensures(*The output integer is greater than or equal to 0.*);
    public int maxScore(int[] cardPoints, int k) {
        int currSum = 0;
        int maxSum;
        for (int i = 0; i < k; i++) {
            currSum += cardPoints[i];
        }
        if (k == cardPoints.length) {
            return currSum;
        }
        maxSum = currSum;
        int r = cardPoints.length - 1;
        while (r >= cardPoints.length - k) {
            currSum += cardPoints[r] - cardPoints[k + r - cardPoints.length];
            maxSum = Math.max(currSum, maxSum);
            r--;
        }
        return maxSum;
    }
}