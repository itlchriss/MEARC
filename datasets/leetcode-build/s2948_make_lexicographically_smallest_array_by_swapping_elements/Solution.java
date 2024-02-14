package g2901_3000.s2948_make_lexicographically_smallest_array_by_swapping_elements;

// #Medium #Array #Sorting #Union_Find #2023_12_26_Time_41_ms_(99.61%)_Space_63.4_MB_(71.68%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` has at least one element.*);
	//@ requires(*The input array `nums` contains only positive integers.*);
	//@ requires(*The input integer `limit` is positive.*);
	//@ ensures(*The returned array is not null.*);
	//@ ensures(*The returned array has the same length as the input array `nums`.*);
	//@ ensures(*The returned array is lexicographically smaller than or equal to the input array `nums`.*);
	//@ ensures(*The returned array can be obtained by performing the operation any number of times.*);
	//@ ensures(*The returned array is obtained by swapping elements in the input array `nums` such that the absolute difference between the swapped elements is less than or equal to the input integer `limit`.*);
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, nums[i]);
        }
        Arrays.sort(nodes, (a, b) -> Integer.signum(a.value - b.value));
        int group = 1;
        nodes[0].group = group;
        for (int i = 1; i < n; i++) {
            if (Math.abs(nodes[i].value - nodes[i - 1].value) <= limit) {
                nodes[i].group = group;
            } else {
                nodes[i].group = ++group;
            }
        }
        int[] groupBase = new int[group + 1];
        for (int i = n - 1; i >= 0; i--) {
            groupBase[nodes[i].group] = i;
        }
        int[] groupIndex = new int[n];
        for (Node node : nodes) {
            groupIndex[node.id] = node.group;
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int index = groupBase[groupIndex[i]];
            ans[i] = nodes[index].value;
            groupBase[groupIndex[i]]++;
        }
        return ans;
    }

    private static class Node {
        int id;
        int value;
        int group;

        Node(int id, int value) {
            this.id = id;
            this.value = value;
        }
    }
}