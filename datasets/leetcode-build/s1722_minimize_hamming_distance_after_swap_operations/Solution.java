package g1701_1800.s1722_minimize_hamming_distance_after_swap_operations;

// #Medium #Array #Depth_First_Search #Union_Find
// #2022_04_25_Time_51_ms_(94.82%)_Space_77.5_MB_(96.89%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*The length of the `source` array is equal to the length of the `target` array.*);
	//@ requires(*The length of the `source` array is equal to the length of the `allowedSwaps` array.*);
	//@ requires(*The length of each inner array in `allowedSwaps` is equal to - The values in the `allowedSwaps` array are valid indices for the `source` array.*);
	//@ requires(*The values in the `allowedSwaps` array are not equal to each other.*);
	//@ ensures(*The returned value is an integer.*);
	//@ ensures(*The returned value represents the minimum Hamming distance between the `source` and `target` arrays after performing any amount of swap operations on the `source` array.*);
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int i;
        int n = source.length;
        int weight = 0;
        int[] parent = new int[n];
        for (i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int[] swap : allowedSwaps) {
            union(swap[0], swap[1], parent);
        }
        HashMap<Integer, List<Integer>> components = new HashMap<>();
        for (i = 0; i < n; i++) {
            find(i, parent);
            List<Integer> list = components.getOrDefault(parent[i], new ArrayList<>());
            list.add(i);
            components.put(parent[i], list);
        }
        for (Map.Entry<Integer, List<Integer>> entry : components.entrySet()) {
            weight += getHammingDistance(source, target, entry.getValue());
        }
        return weight;
    }

    private int getHammingDistance(int[] source, int[] target, List<Integer> indices) {
        HashMap<Integer, Integer> list1 = new HashMap<>();
        HashMap<Integer, Integer> list2 = new HashMap<>();
        for (int i : indices) {
            list1.put(target[i], 1 + list1.getOrDefault(target[i], 0));
            list2.put(source[i], 1 + list2.getOrDefault(source[i], 0));
        }
        int size = indices.size();
        for (Map.Entry<Integer, Integer> entry : list1.entrySet()) {
            size -= Math.min(entry.getValue(), list2.getOrDefault(entry.getKey(), 0));
        }
        return size;
    }

    private void union(int x, int y, int[] parent) {
        if (x != y) {
            int a = find(x, parent);
            int b = find(y, parent);
            if (a != b) {
                parent[a] = b;
            }
        }
    }

    private int find(int x, int[] parent) {
        int y = x;
        while (y != parent[y]) {
            y = parent[y];
        }
        parent[x] = y;
        return y;
    }
}