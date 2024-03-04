package g0801_0900.s0823_binary_trees_with_factors;

// #Medium #Array #Hash_Table #Dynamic_Programming
// #2022_03_23_Time_28_ms_(80.54%)_Space_42.6_MB_(79.87%)

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    private Map<Integer, Long> dp = new HashMap<>();
    private Map<Integer, Integer> nums = new HashMap<>();
    private static final int MOD = (int) 1e9 + 7;
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `arr` is strictly greater than 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the number of binary trees that can be made using the elements in the input array `arr`.*);
//@ ensures(*The returned value is the answer modulo 10^9 + 7.*);

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            nums.put(arr[i], i);
        }
        long ans = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            ans = (ans % MOD + recursion(arr, arr[i], i) % MOD) % MOD;
        }
        return (int) ans;
    }

    private long recursion(int[] arr, int v, int idx) {
        if (dp.containsKey(v)) {
            return dp.get(v);
        }
        long ret = 1;
        for (int i = 0; i < idx; i++) {
            int child = arr[i];
            if (v % child == 0 && nums.containsKey(v / child)) {
                ret +=
                        (recursion(arr, child, nums.get(arr[i]))
                                        % MOD
                                        * recursion(arr, v / child, nums.get(v / child))
                                        % MOD)
                                % MOD;
            }
        }
        dp.put(v, ret);
        return ret;
    }
}