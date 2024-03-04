package g2201_2300.s2271_maximum_white_tiles_covered_by_a_carpet;

// #Medium #Array #Sorting #Greedy #Binary_Search #Prefix_Sum
// #2022_06_16_Time_74_ms_(71.51%)_Space_86_MB_(38.91%)

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `tiles` is a 2D integer array.*);
//@ ensures(*Each element `tiles[i]` in `tiles` is an array of length 2, representing the range of white tiles.*);
//@ ensures(*The values in `tiles[i]` are integers.*);
//@ ensures(*The values in `tiles[i]` are non-negative.*);
//@ ensures(*The values in `tiles[i]` are non-decreasing.*);
//@ ensures(*The input `carpetLen` is an integer.*);
//@ ensures(*The value of `carpetLen` is positive.*);
//@ ensures(*The value of `carpetLen` is less than or equal to 10^9.*);
//@ ensures(*The `tiles` array is non-empty.*);
//@ ensures(*The length of `tiles` is less than or equal to 5 * 10^4.*);
//@ ensures(*The `tiles` array does not contain overlapping ranges.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer.*);
//@ ensures(*The returned value represents the maximum number of white tiles that can be covered by the carpet.*);
//@ ensures(*The returned value is non-negative.*);
//@ ensures(*The returned value is less than or equal to the length of the longest range in `tiles`.*);
//@ ensures(*The returned value is less than or equal to the length of the carpet (`carpetLen`).*);
//@ ensures(*The returned value is the maximum possible number of white tiles that can be covered by the carpet.*);
    public int maximumWhiteTiles(int[][] tiles, int carpetLength) {
        Arrays.sort(tiles, Comparator.comparingInt(x -> x[0]));
        int currentCover = Math.min(tiles[0][1] - tiles[0][0] + 1, carpetLength);
        int maxCover = currentCover;
        int head = 1;
        int tail = 0;
        while (tail < tiles.length && head < tiles.length && maxCover < carpetLength) {
            if (tiles[head][1] - tiles[tail][0] + 1 <= carpetLength) {
                currentCover += tiles[head][1] - tiles[head][0] + 1;
                maxCover = Math.max(maxCover, currentCover);
                ++head;
            } else {
                int possiblePartialCoverOverCurrentHead =
                        carpetLength - (tiles[head][0] - tiles[tail][0]);
                maxCover = Math.max(maxCover, currentCover + possiblePartialCoverOverCurrentHead);
                currentCover = currentCover - (tiles[tail][1] - tiles[tail][0] + 1);
                ++tail;
            }
        }
        return maxCover;
    }
}