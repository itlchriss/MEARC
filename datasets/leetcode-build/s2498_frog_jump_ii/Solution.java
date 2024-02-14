package g2401_2500.s2498_frog_jump_ii;

// #Medium #Array #Greedy #Binary_Search #2023_02_12_Time_1_ms_(100.00%)_Space_55.8_MB_(66.50%)

public class Solution {
	//@ requires(*The input array `stones` is not null.*);
	//@ requires(*The length of the input array `stones` is at least 2.*);
	//@ requires(*The elements in the input array `stones` are sorted in strictly increasing order.*);
	//@ requires(*The first element in the input array `stones` is 0.*);
	//@ ensures(*The method returns an integer representing the minimum cost of a path for the frog.*);
	//@ ensures(*The frog can jump to any stone at most once.*);
	//@ ensures(*The frog starts on the first stone and wants to travel to the last stone and then return to the first stone.*);
	//@ ensures(*The length of a jump is the absolute difference between the position of the stone the frog is currently on and the position of the stone to which the frog jumps.*);
	//@ ensures(*The cost of a path is the maximum length of a jump among all jumps in the path.*);
	//@ ensures(*The method returns the minimum cost of a path for the frog.*);
    public int maxJump(int[] stones) {
        int n = stones.length;
        int max = 0;
        for (int i = 2; i < n; i++) {
            int gap = stones[i] - stones[i - 2];
            if (gap > max) {
                max = gap;
            }
        }
        if (n > 2) {
            return max;
        } else {
            return stones[1] - stones[0];
        }
    }
}