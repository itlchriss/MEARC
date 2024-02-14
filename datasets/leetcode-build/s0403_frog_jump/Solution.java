package g0401_0500.s0403_frog_jump;

// #Hard #Array #Dynamic_Programming #2022_07_15_Time_13_ms_(99.06%)_Space_54.5_MB_(74.78%)

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    // global hashmap to store visited index -> set of jump lengths from that index
    private HashMap<Integer, HashSet<Integer>> visited = new HashMap<>();
	//@ requires(*The input array `stones` is not null.*);
	//@ requires(*The length of the input array `stones` is at least 2.*);
	//@ requires(*The input array `stones` is sorted in ascending order.*);
	//@ requires(*The first element of the input array `stones` is 0.*);
	//@ ensures(*The method returns a boolean value indicating whether the frog can cross the river by landing on the last stone.*);
	//@ ensures(*If the method returns true, it means the frog can cross the river.*);
	//@ ensures(*If the method returns false, it means the frog cannot cross the river.*);
	//@ ensures(*The frog can only jump on a stone, it cannot jump into the water.*);
	//@ ensures(*The frog's last jump was `k` units, and its next jump must be either `k - 1`, `k`, or `k + 1` units.*);
	//@ ensures(*The frog can only jump in the forward direction.*);
	//@ ensures(*The frog starts on the first stone and assumes the first jump must be 1 unit.*);
	//@ ensures(*The frog can jump to the last stone by making a series of valid jumps from one stone to another.*);

    public boolean canCross(int[] stones) {
        // a mathematical check before going in the recursion
        for (int i = 3; i < stones.length; i++) {
            if (stones[i] > stones[i - 1] * 2) {
                return false;
            }
        }
        // map of values -> index to make sure we get the next index quickly
        HashMap<Integer, Integer> rocks = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            rocks.put(stones[i], i);
        }
        return jump(stones, 0, 1, 0, rocks);
    }

    private boolean jump(
            int[] stones, int index, int jumpLength, int expectedVal, Map<Integer, Integer> rocks) {
        // overshoot and going backwards not allowed
        if (index >= stones.length || jumpLength <= 0) {
            return false;
        }
        // reached the last index
        if (index == stones.length - 1) {
            return expectedVal == stones[index];
        }
        // check if this index -> jumpLength pair was seen before, otherwise record it
        HashSet<Integer> rememberJumps = visited.getOrDefault(index, new HashSet<>());
        if (stones[index] > expectedVal || rememberJumps.contains(jumpLength)) {
            return false;
        }
        rememberJumps.add(jumpLength);
        visited.put(index, rememberJumps);
        // check for jumpLength-1, jumpLength, jumpLength+1 for a new expected value
        return jump(
                        stones,
                        rocks.getOrDefault(stones[index] + jumpLength, stones.length),
                        jumpLength + 1,
                        stones[index] + jumpLength,
                        rocks)
                || jump(
                        stones,
                        rocks.getOrDefault(stones[index] + jumpLength, stones.length),
                        jumpLength,
                        stones[index] + jumpLength,
                        rocks)
                || jump(
                        stones,
                        rocks.getOrDefault(stones[index] + jumpLength, stones.length),
                        jumpLength - 1,
                        stones[index] + jumpLength,
                        rocks);
    }
}