package g2701_2800.s2768_number_of_black_blocks;

// #Medium #Array #Hash_Table #Enumeration #2023_09_21_Time_94_ms_(98.91%)_Space_55.3_MB_(48.37%)

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `m` and `n` must be greater than or equal to - The input matrix `coordinates` must have a length less than or equal to 10,- Each coordinate in `coordinates` must be a valid index within the grid, i.e., `0 <= coordinates[i][0] < m` and `0 <= coordinates[i][1] < n`.*);
//@ ensures(*The coordinates in `coordinates` must be pairwise distinct.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `arr` must have a length of - Each element in `arr` represents the number of blocks that contain a specific number of black cells.*);
//@ ensures(*The sum of all elements in `arr` must be equal to the total number of blocks in the grid.*);
//@ ensures(*The order of elements in `arr` corresponds to the number of black cells in a block, starting from 0 to 4.*);
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        long[] ans = new long[5];
        Map<Integer, Integer> count = new HashMap<>();
        for (int[] coordinate : coordinates) {
            int x = coordinate[0];
            int y = coordinate[1];
            for (int i = x; i < x + 2; i++) {
                for (int j = y; j < y + 2; j++) {
                    if (i - 1 >= 0 && i < m && j - 1 >= 0 && j < n) {
                        count.merge(i * n + j, 1, (a, b) -> a + b);
                    }
                }
            }
        }
        for (int freq : count.values()) {
            ans[freq]++;
        }
        ans[0] = (m - 1L) * (n - 1) - Arrays.stream(ans).sum();
        return ans;
    }
}