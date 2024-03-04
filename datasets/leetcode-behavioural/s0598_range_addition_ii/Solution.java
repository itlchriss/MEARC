package g0501_0600.s0598_range_addition_ii;

// #Easy #Array #Math #2022_08_25_Time_0_ms_(100.00%)_Space_44.3_MB_(71.22%)

public class Solution {
    /*
     * Since the incrementing starts from zero to op[0] and op[1], we only need to find the range that
     * has the most overlaps. Thus we keep finding the minimum of both x and y.
     */
//@ ensures(*The integer parameter `m` must be greater than or equal to 1.*);
//@ ensures(*The integer parameter `n` must be greater than or equal to 1.*);
//@ ensures(*The integer array parameter `ops` must not be null.*);
//@ ensures(*The integer array parameter `ops` must have a length greater than or equal to 0.*);
//@ ensures(*The integer array parameter `ops` must have subarrays of length 2.*);
//@ ensures(*The integer subarray elements in `ops` must be within the range of 1 to the respective `m` and `n` values.*);
//@ ensures(*The integer result is the number of maximum integers in the matrix `M` after performing all the operations specified in the `ops` array.*);
    public int maxCount(int m, int n, int[][] ops) {
        int x = m;
        int y = n;
        for (int[] op : ops) {
            x = Math.min(x, op[0]);
            y = Math.min(y, op[1]);
        }
        return x * y;
    }
}