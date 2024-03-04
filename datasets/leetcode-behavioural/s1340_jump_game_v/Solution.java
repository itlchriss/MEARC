package g1301_1400.s1340_jump_game_v;

// #Hard #Array #Dynamic_Programming #Sorting #2022_03_19_Time_13_ms_(71.33%)_Space_47.7_MB_(46.67%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `arr` is not null.*);
//@ ensures(*The length of the input array `arr` is greater than or equal to 1.*);
//@ ensures(*The input integer `d` is greater than or equal to 1.*);
//@ ensures(*The input integer `d` is less than or equal to the length of the input array `arr`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output integer is the maximum number of indices that can be visited.*);
//@ ensures(*The output integer is greater than or equal to 1.*);
//@ ensures(*The output integer is less than or equal to the length of the input array `arr`.*);
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        int jumps = 0;
        for (int i = 0; i < n; i++) {
            jumps = Math.max(jumps, helper(arr, d, i, dp));
        }
        return jumps;
    }

    private int helper(int[] arr, int d, int i, int[] dp) {
        int temp = 0;
        if (dp[i] != 0) {
            return dp[i];
        }
        int l = Math.max(0, i - d);
        int r = Math.min(i + d, arr.length - 1);
        for (int j = i - 1; j >= l && arr[i] > arr[j]; j--) {
            temp = Math.max(temp, helper(arr, d, j, dp));
        }
        for (int j = i + 1; j <= r && arr[i] > arr[j]; j++) {
            temp = Math.max(temp, helper(arr, d, j, dp));
        }
        return 1 + temp;
    }
}