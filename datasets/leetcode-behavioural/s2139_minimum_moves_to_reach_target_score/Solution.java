package g2101_2200.s2139_minimum_moves_to_reach_target_score;

// #Medium #Math #Greedy #2022_06_05_Time_1_ms_(37.95%)_Space_40.8_MB_(55.49%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `target` is a positive integer greater than or equal to 1.*);
//@ ensures(*The input `maxDoubles` is a non-negative integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum number of moves needed to reach the target starting with 1.*);
//@ ensures(*The output is greater than or equal to 0.*);
    public int minMoves(int target, int maxDoubles) {
        int count = 0;
        while (target > 1) {
            if (maxDoubles > 0 && target % 2 == 0) {
                maxDoubles--;
                target = target / 2;
            } else {
                if (maxDoubles == 0) {
                    count = count + target - 1;
                    return count;
                } else {
                    target = target - 1;
                }
            }
            count++;
        }
        return count;
    }
}