package g2101_2200.s2162_minimum_cost_to_set_cooking_time;

// #Medium #Math #Enumeration #2022_06_04_Time_1_ms_(95.82%)_Space_40.8_MB_(69.87%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The value of `startAt` must be between 0 and 9 (inclusive).*);
//@ ensures(*The value of `moveCost` must be between 1 and 10^5 (inclusive).*);
//@ ensures(*The value of `pushCost` must be between 1 and 10^5 (inclusive).*);
//@ ensures(*The value of `targetSeconds` must be between 1 and 6039 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum cost to set the cooking time.*);
//@ ensures(*The minimum cost is calculated based on the given parameters `startAt`, `moveCost`, `pushCost`, and `targetSeconds`.*);
//@ ensures(*The minimum cost is the sum of the costs of moving the finger and pushing the digits to set the cooking time.*);
//@ ensures(*The method considers all possible ways to set the cooking time and returns the minimum cost among them.*);
    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        int mins = targetSeconds / 60;
        int secs = targetSeconds % 60;
        return Math.min(
                cost(mins, secs, startAt, moveCost, pushCost),
                cost(mins - 1, secs + 60, startAt, moveCost, pushCost));
    }

    private int cost(int mins, int secs, int startAt, int moveCost, int pushCost) {
        if (mins > 99 || secs > 99 || mins < 0 || secs < 0) {
            return Integer.MAX_VALUE;
        }
        String s = Integer.toString(mins * 100 + secs);
        char curr = (char) (startAt + '0');
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == curr) {
                res += pushCost;
            } else {
                res += pushCost + moveCost;
                curr = s.charAt(i);
            }
        }
        return res;
    }
}