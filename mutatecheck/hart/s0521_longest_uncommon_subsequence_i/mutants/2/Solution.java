package g0501_0600.s0521_longest_uncommon_subsequence_i;

// #Easy #String #2022_07_28_Time_0_ms_(100.00%)_Space_40.2_MB_(87.89%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
    /*
     * The gotcha point of this question is:
     * 1. if a and b are identical, then there will be no common subsequence, return -1
     * 2. else if a and b are of equal length, then any one of them will be a subsequence of the other string
     * 3. else if a and b are of different length, then the longer one is a required subsequence because
     * the longer string cannot be a subsequence of the shorter one
     * Or in other words, when a.length() != b.length(), no subsequence of b will be equal to a,
     * so return Math.max(a.length(), b.length())
     */
//@ requires((a.length() <= 100) && (a.length() >= 1));
//@ requires((b.length() <= 100) && (b.length() >= 1));
//@ ensures((\result <= 2147483647) && (\result >= -1));
//@ ensures(((a.equals("aba")) && (b.equals("cdc"))) ==> (\result == 3));
//@ ensures(((a.equals("aaa")) && (b.equals("aaa"))) ==> (\result == -1));
//@ ensures(((a.equals("aaa")) && (b.equals("bbb"))) ==> (\result == 3));
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {

        }
        return Math.max(a.length(), b.length());
    }
}
