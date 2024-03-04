package g2001_2100.s2001_number_of_pairs_of_interchangeable_rectangles;

// #Medium #Array #Hash_Table #Math #Counting #Number_Theory
// #2022_05_22_Time_34_ms_(99.02%)_Space_121.4_MB_(69.27%)

import java.util.Arrays;

public class Solution {
    private long factorial(long n) {
        long m = 0;
        while (n > 0) {
            m += n;
            n = n - 1;
        }
        return m;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input `rec` is a 2D integer array representing `n` rectangles.*);
//@ ensures(*Each rectangle `rec[i]` has a width `width_i` and a height `height_i`.*);
//@ ensures(*The width and height of each rectangle are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns the number of pairs of interchangeable rectangles in `rec`.*);
//@ ensures(*Two rectangles `i` and `j` are considered interchangeable if they have the same width-to-height ratio.*);
//@ ensures(*The width-to-height ratio is calculated using decimal division, not integer division.*);
//@ ensures(*The method returns 0 if there are no interchangeable pairs of rectangles.*);

    public long interchangeableRectangles(int[][] rec) {
        double[] ratio = new double[rec.length];
        for (int i = 0; i < rec.length; i++) {
            ratio[i] = (double) rec[i][0] / rec[i][1];
        }
        Arrays.sort(ratio);
        long res = 0;
        int k = 0;
        for (int j = 0; j < ratio.length - 1; j++) {
            if (ratio[j] == ratio[j + 1]) {
                k++;
            }
            if (ratio[j] != ratio[j + 1] || j + 2 == ratio.length) {
                res += factorial(k);
                k = 0;
            }
        }
        return res;
    }
}