package g1701_1800.s1743_restore_the_array_from_adjacent_pairs;

// #Medium #Array #Hash_Table #2022_04_29_Time_95_ms_(99.08%)_Space_101.6_MB_(84.84%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*The input `adjacentPairs` is a 2D integer array.*);
	//@ requires(*The length of `adjacentPairs` is equal to `n - 1`, where `n` is the length of the unknown array `nums`.*);
	//@ requires(*Each element in `adjacentPairs` is an array of length 2.*);
	//@ requires(*The elements in `adjacentPairs` are integers.*);
	//@ requires(*The elements in `adjacentPairs` can be positive, negative, or zero.*);
	//@ requires(*The elements in `adjacentPairs` can be within the range of -10^5 to 10^5.*);
	//@ requires(*There exists an array `nums` that has `adjacentPairs` as its pairs.*);
	//@ ensures(*The output is an integer array `nums`.*);
	//@ ensures(*The length of `nums` is equal to `n`.*);
	//@ ensures(*The elements in `nums` are unique.*);
	//@ ensures(*Every pair of adjacent elements in `nums` is present in `adjacentPairs`.*);
	//@ ensures(*The order of the adjacent pairs in `adjacentPairs` does not necessarily correspond to the order of the adjacent elements in `nums`.*);
	//@ ensures(*If there are multiple solutions, any of them can be returned as the output.*);
    public int[] restoreArray(int[][] adjacentPairs) {
        if (adjacentPairs == null || adjacentPairs.length == 0) {
            return new int[0];
        }
        if (adjacentPairs.length == 1) {
            return adjacentPairs[0];
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
        }
        int[] res = new int[graph.size()];
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            if (entry.getValue().size() == 1) {
                res[0] = entry.getKey();
                break;
            }
        }
        res[1] = graph.get(res[0]).get(0);
        for (int i = 2; i < res.length; i++) {
            for (int cur : graph.get(res[i - 1])) {
                if (cur != res[i - 2]) {
                    res[i] = cur;
                    break;
                }
            }
        }
        return res;
    }
}