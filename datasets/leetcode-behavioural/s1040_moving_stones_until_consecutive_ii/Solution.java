package g1001_1100.s1040_moving_stones_until_consecutive_ii;

// #Medium #Array #Math #Sorting #Two_Pointers #2022_02_27_Time_8_ms_(55.00%)_Space_49_MB_(31.67%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `stones` is not null.*);
//@ ensures(*The length of the input array `stones` is at least 3.*);
//@ ensures(*All the values in the input array `stones` are unique.*);
//@ ensures(*The values in the input array `stones` are within the range of 1 to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `answer` is not null.*);
//@ ensures(*The length of the output array `answer` is 2.*);
//@ ensures(*The first element of the output array `answer[0]` represents the minimum number of moves to finish the game.*);
//@ ensures(*The second element of the output array `answer[1]` represents the maximum number of moves to finish the game.*);
//@ ensures(*The values in the output array `answer` are within the range of 0 to the length of the input array `stones` minus 3.*);
    public int[] numMovesStonesII(int[] stones) {
        int n = stones.length;
        int[] ans = new int[2];
        int i = 0;
        int j = 0;
        int wsize;
        int scount;
        int minMoves = Integer.MAX_VALUE;
        Arrays.sort(stones);
        while (j < n) {
            wsize = stones[j] - stones[i] + 1;
            scount = j - i + 1;
            if (wsize > n) {
                i++;
                continue;
            }
            if (wsize == n - 1 && scount == n - 1) {
                minMoves = Math.min(minMoves, 2);
            } else {
                minMoves = Math.min(minMoves, n - scount);
            }
            j++;
        }
        ans[0] = minMoves;
        int maxMoves;
        if (stones[1] == stones[0] + 1 || stones[n - 1] == stones[n - 2] + 1) {
            maxMoves = stones[n - 1] - stones[0] + 1 - n;
        } else {
            maxMoves =
                    Math.max(
                            ((stones[n - 1] - stones[1]) - (n - 1) + 1),
                            ((stones[n - 2] - stones[0]) - (n - 1) + 1));
        }
        ans[1] = maxMoves;
        return ans;
    }
}