package g1901_2000.s1923_longest_common_subpath;

// #Hard #Array #Binary_Search #Hash_Function #Rolling_Hash #Suffix_Array
// #2022_05_15_Time_517_ms_(73.88%)_Space_209.3_MB_(50.00%)

import java.util.HashSet;

@SuppressWarnings("java:S1172")
public class Solution {
    private static final long BASE = 100001;
    private static final long MOD = (long) (Math.pow(10, 11) + 7);
    private long[] pow;
	//@ requires(*The input `n` is a positive integer representing the number of cities.*);
	//@ requires(*The input `paths` is a 2D integer array representing the paths of the friends.*);
	//@ requires(*The length of `paths` is equal to the number of friends `m`.*);
	//@ requires(*Each path in `paths` is represented by an integer array.*);
	//@ requires(*Each path in `paths` contains the visited cities in order.*);
	//@ requires(*Each path in `paths` may contain a city more than once, but the same city will not be listed consecutively.*);
	//@ requires(*The length of each path in `paths` is not greater than 10^- The same city is not listed multiple times consecutively in each path in `paths`.*);
	//@ ensures(*The method returns an integer representing the length of the longest common subpath shared by every friend's path.*);
	//@ ensures(*If there is no common subpath at all, the method returns 0.*);

    public int longestCommonSubpath(int n, int[][] paths) {
        int res = 0;
        int min = Integer.MAX_VALUE;
        for (int[] path : paths) {
            min = Math.min(min, path.length);
        }
        pow = new long[min + 1];
        pow[0]++;
        for (int i = 1; i <= min; i++) {
            pow[i] = (pow[i - 1] * BASE) % MOD;
        }
        int st = 1;
        int end = min;
        int mid = (st + end) / 2;
        while (st <= end) {
            if (commonSubstring(paths, mid)) {
                res = mid;
                st = mid + 1;
            } else {
                end = mid - 1;
            }
            mid = (st + end) / 2;
        }
        return res;
    }

    private boolean commonSubstring(int[][] paths, int l) {
        HashSet<Long> set = rollingHash(paths[0], l);
        for (int i = 1, n = paths.length; i < n; i++) {
            set.retainAll(rollingHash(paths[i], l));
            if (set.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private HashSet<Long> rollingHash(int[] a, int l) {
        HashSet<Long> set = new HashSet<>();
        long hash = 0;
        for (int i = 0; i < l; i++) {
            hash = (hash * BASE + a[i]) % MOD;
        }
        set.add(hash);
        for (int n = a.length, curr = l, prev = 0; curr < n; prev++, curr++) {
            hash = (((hash * BASE) % MOD - (a[prev] * pow[l]) % MOD + a[curr]) + MOD) % MOD;
            set.add(hash);
        }
        return set;
    }
}