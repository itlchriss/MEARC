package g2401_2500.s2440_create_components_with_same_value;

// #Hard #Array #Math #Depth_First_Search #Tree #Enumeration
// #2022_12_13_Time_114_ms_(73.23%)_Space_115.7_MB_(68.01%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Solution {
    private int[] nums;
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is equal to `n`.*);
	//@ requires(*The input array `edges` is not null.*);
	//@ requires(*The length of `edges` is equal to `n - 1`.*);
	//@ requires(*Each element in `edges` is an array of length 2.*);
	//@ requires(*The values in `edges` are valid node indices (between 0 and `n - 1`).*);
	//@ requires(*The input tree is connected and does not contain any cycles.*);
	//@ ensures(*The method returns an integer value representing the maximum number of edges that can be deleted.*);
	//@ ensures(*The input tree is split into multiple connected components.*);
	//@ ensures(*Each connected component has the same value, which is the sum of the values in `nums` for the nodes in that component.*);

    public int componentValue(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        List[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        for (int k = n; k > 0; k--) {
            if (sum % k != 0) {
                continue;
            }
            int ans = helper(graph, 0, -1, sum / k);
            if (ans == 0) {
                return k - 1;
            }
        }
        return 0;
    }

    private int helper(List<Integer>[] graph, int i, int prev, int target) {
        if (graph[i].size() == 1 && graph[i].get(0) == prev) {
            if (nums[i] > target) {
                return -1;
            }
            if (nums[i] == target) {
                return 0;
            }
            return nums[i];
        }
        int sum = nums[i];
        for (int k : graph[i]) {
            if (k == prev) {
                continue;
            }
            int ans = helper(graph, k, i, target);
            if (ans == -1) {
                return -1;
            }
            sum += ans;
        }
        if (sum > target) {
            return -1;
        }
        if (sum == target) {
            return 0;
        }
        return sum;
    }
}