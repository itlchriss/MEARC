package g0701_0800.s0757_set_intersection_size_at_least_two;

// #Hard #Array #Sorting #Greedy #2022_03_25_Time_4_ms_(100.00%)_Space_52.4_MB_(43.65%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `intervals` is a non-empty array of arrays, where each inner array represents an integer interval `[a, b]`.*);
//@ ensures(*Each inner array in `intervals` contains exactly two integers `a` and `b`, where `a` is less than `b`.*);
//@ ensures(*The values of `a` and `b` in each inner array are non-negative integers and do not exceed `10^8`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum size of a set `S` such that for every integer interval `A` in `intervals`, the intersection of `S` with `A` has a size of at least two.*);
    public int intersectionSizeTwo(int[][] intervals) {
        int n = 0;
        long[] endStartPairs = new long[intervals.length];
        for (int[] interval : intervals) {
            endStartPairs[n] = -interval[0] & 0xFFFFFFFFL;
            endStartPairs[n++] |= (long) (interval[1]) << 32;
        }
        Arrays.sort(endStartPairs);
        int min = -2;
        int max = -1;
        int curStart;
        int curEnd;
        int res = 0;
        for (long endStartPair : endStartPairs) {
            curStart = -(int) endStartPair;
            curEnd = (int) (endStartPair >> 32);
            if (curStart <= min) {
                continue;
            }
            if (curStart <= max) {
                res += 1;
                min = max;
            } else {
                res += 2;
                min = curEnd - 1;
            }
            max = curEnd;
        }
        return res;
    }
}