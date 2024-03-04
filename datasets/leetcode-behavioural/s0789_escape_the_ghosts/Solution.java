package g0701_0800.s0789_escape_the_ghosts;

// #Medium #Array #Math #2022_03_26_Time_0_ms_(100.00%)_Space_42.7_MB_(52.86%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `ghosts` is a 2D array of length `n` where `1 <= n <= 100`.*);
//@ ensures(*Each element in `ghosts` is a 2-element array representing the starting position of a ghost.*);
//@ ensures(*The starting positions of the ghosts are given as integral coordinates.*);
//@ ensures(*The input `target` is a 2-element array representing the destination point.*);
//@ ensures(*The destination point is given as integral coordinates.*);
//@ ensures(*The ghosts and the target are located on an infinite 2D grid.*);
//@ ensures(*Each turn, the player and all the ghosts can independently choose to move 1 unit in any of the four cardinal directions or stay still.*);
//@ ensures(*All actions happen simultaneously.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether it is possible to escape regardless of how the ghosts move.*);
//@ ensures(*If it is possible to reach the target before any ghost reaches the player, the method returns `true`.*);
//@ ensures(*If the player reaches any square (including the target) at the same time as a ghost, it does not count as an escape.*);
//@ ensures(*If it is not possible to reach the target before any ghost reaches the player, the method returns `false`.*);
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int[] currPos = {0, 0};
        int selfDist = getDist(currPos, target);
        for (int[] ghost : ghosts) {
            int ghostDist = getDist(ghost, target);
            if (ghostDist <= selfDist) {
                return false;
            }
        }
        return true;
    }

    private int getDist(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
}