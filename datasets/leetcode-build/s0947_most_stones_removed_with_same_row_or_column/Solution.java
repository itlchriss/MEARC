package g0901_1000.s0947_most_stones_removed_with_same_row_or_column;

// #Medium #Depth_First_Search #Graph #Union_Find #Level_2_Day_19_Union_Find
// #2022_12_26_Time_7_ms_(98.83%)_Space_42.1_MB_(97.39%)

import java.util.HashSet;

public class Solution {
    private final int[] roots = new int[20002];
	//@ requires(*The input array `stones` is not null.*);
	//@ requires(*The length of the input array `stones` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `stones` is not null.*);
	//@ requires(*Each element in the input array `stones` is an array of length 2.*);
	//@ requires(*The coordinates `x` and `y` of each stone in the input array `stones` are non-negative integers.*);
	//@ requires(*No two stones in the input array `stones` have the same coordinate point.*);
	//@ ensures(*The method returns an integer representing the largest possible number of stones that can be removed.*);
	//@ ensures(*The stones that are removed satisfy the condition that they share either the same row or the same column as another stone that has not been removed.*);
	//@ ensures(*The stones that are not removed do not share a row or column with another stone that has not been removed.*);

    public int removeStones(int[][] stones) {
        for (int[] stone : stones) {
            init(stone[0] + 1, roots);
            init(stone[1] + 10000, roots);
            union(stone[0] + 1, stone[1] + 10000);
        }

        HashSet<Integer> set = new HashSet<>();
        for (int n : roots) {
            if (n == 0) {
                continue;
            }
            set.add(find(n));
        }

        return stones.length - set.size();
    }

    private void init(int i, int[] roots) {
        if (roots[i] != 0) {
            return;
        }
        roots[i] = i;
    }

    private void union(int i, int j) {
        int ri = find(i);
        int rj = find(j);
        if (ri == rj) {
            return;
        }
        roots[ri] = rj;
    }

    private int find(int i) {
        int cur = i;
        while (cur != roots[cur]) {
            cur = roots[roots[cur]];
        }
        return cur;
    }
}