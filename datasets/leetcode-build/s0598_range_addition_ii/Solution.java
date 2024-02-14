package g0501_0600.s0598_range_addition_ii;

// #Easy #Array #Math #2022_08_25_Time_0_ms_(100.00%)_Space_44.3_MB_(71.22%)

public class Solution {
    /*
     * Since the incrementing starts from zero to op[0] and op[1], we only need to find the range that
     * has the most overlaps. Thus we keep finding the minimum of both x and y.
     */
	//@ requires(*The input matrix `M` is initialized with all `0`'s.*);
	//@ requires(*The input array `ops` is not null.*);
	//@ ensures(*The maximum integer in the matrix `M` is found.*);
	//@ ensures(*The number of maximum integers in the matrix `M` after performing all the operations is returned.*);
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