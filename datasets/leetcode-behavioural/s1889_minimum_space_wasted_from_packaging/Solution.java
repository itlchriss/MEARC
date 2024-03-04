package g1801_1900.s1889_minimum_space_wasted_from_packaging;

// #Hard #Array #Sorting #Binary_Search #Prefix_Sum
// #2022_05_09_Time_98_ms_(45.92%)_Space_128.3_MB_(32.65%)

import java.util.Arrays;

@SuppressWarnings("java:S135")
public class Solution {
    private static final int MOD = 1_000_000_007;
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `packages` must not be null.*);
//@ ensures(*The input array `boxes` must not be null.*);
//@ ensures(*The length of the input array `packages` must be greater than 0.*);
//@ ensures(*The length of the input array `boxes` must be greater than 0.*);
//@ ensures(*The elements in the input array `packages` must be positive integers.*);
//@ ensures(*The elements in the input array `boxes` must be positive integers.*);
//@ ensures(*The elements in the input array `boxes` must be distinct.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum total wasted space.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
//@ ensures(*If it is impossible to fit all the packages inside boxes, the method returns -1.*);
//@ ensures(*The returned value is modulo 10^9 + 7.*);

    public int minWastedSpace(int[] packages, int[][] boxes) {
        int numPackages = packages.length;
        Arrays.sort(packages);
        long[] preSum = new long[numPackages];
        preSum[0] = packages[0];
        for (int i = 1; i < packages.length; i++) {
            preSum[i] = packages[i] + preSum[i - 1];
        }
        long ans = Long.MAX_VALUE;
        for (int[] box : boxes) {
            Arrays.sort(box);
            // Box of required size not present
            if (packages[numPackages - 1] > box[box.length - 1]) {
                continue;
            }
            // Find the total space wasted
            long totalWastedSpace = 0;
            int prev = -1;
            for (int j : box) {
                if (prev == packages.length - 1) {
                    break;
                }
                if (j < packages[0] || j < packages[prev + 1]) {
                    continue;
                }
                // Find up to which package the current box can fit
                int upper = findUpperBound(packages, j);
                if (upper == -1) {
                    continue;
                }
                // The current box will be able to handle the packages from
                // prev + 1 to the upper index
                long totalSpace = ((long) upper - (long) prev) * j;
                long packageSum = preSum[upper] - (prev >= 0 ? preSum[prev] : 0);
                long spaceWastedCurr = totalSpace - packageSum;
                totalWastedSpace += spaceWastedCurr;
                prev = upper;
            }
            ans = Math.min(ans, totalWastedSpace);
        }
        return ans == Long.MAX_VALUE ? -1 : (int) (ans % MOD);
    }

    private int findUpperBound(int[] packages, int key) {
        int l = 0;
        int h = packages.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (packages[m] <= key) {
                l = m + 1;
            } else {
                h = m;
            }
        }
        return h - 1;
    }
}