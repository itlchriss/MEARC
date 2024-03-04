package g1601_1700.s1632_rank_transform_of_a_matrix;

// #Hard #Array #Greedy #Matrix #Graph #Union_Find #Topological_Sort
// #2022_04_20_Time_65_ms_(100.00%)_Space_109.9_MB_(70.50%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input matrix must not be null.*);
//@ ensures(*2. The input matrix must have at least one row and one column.*);
//@ ensures(*3. The elements in the input matrix must be integers.*);
//@ ensures(*4. The dimensions of the input matrix must satisfy the constraints: 1 <= m, n <= 500.*);
//@ ensures(*5. The values of the elements in the input matrix must satisfy the constraints: -10^9 <= matrix[row][col] <= 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The output matrix must have the same dimensions as the input matrix.*);
//@ ensures(*2. The elements in the output matrix must be integers.*);
//@ ensures(*3. The rank of each element in the output matrix must be calculated correctly according to the given rules.*);
//@ ensures(*4. The rank of each element in the output matrix must be as small as possible.*);
//@ ensures(*5. The output matrix must be unique under the given rules.*);
    public int[][] matrixRankTransform(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        long[] nums = new long[rowCount * colCount];
        int numsIdx = 0;
        int[] rows = new int[rowCount];
        int[] cols = new int[colCount];

        for (int r = rowCount - 1; r >= 0; r--) {
            for (int c = colCount - 1; c >= 0; c--) {
                nums[numsIdx++] = ((long) matrix[r][c] << 32) | ((long) r << 16) | c;
            }
        }
        Arrays.sort(nums);
        int nIdx = 0;
        while (nIdx < numsIdx) {
            long num = nums[nIdx] & 0xFFFFFFFF00000000L;
            int endIdx = nIdx + 1;
            while (endIdx < numsIdx && ((nums[endIdx] & 0xFFFFFFFF00000000L) == num)) {
                endIdx++;
            }
            doGroup(matrix, nums, nIdx, endIdx, rows, cols);
            nIdx = endIdx;
        }
        return matrix;
    }

    private void doGroup(
            int[][] matrix, long[] nums, int startIdx, int endIdx, int[] rows, int[] cols) {
        if (startIdx + 1 == endIdx) {
            int r = ((int) nums[startIdx] >> 16) & 0xFFFF;
            int c = (int) nums[startIdx] & 0xFFFF;
            matrix[r][c] = rows[r] = cols[c] = Math.max(rows[r], cols[c]) + 1;
        } else {
            int rowCount = matrix.length;
            int[] ufind = new int[rowCount + matrix[0].length];
            Arrays.fill(ufind, -1);
            for (int nIdx = startIdx; nIdx < endIdx; nIdx++) {
                int r = ((int) nums[nIdx] >> 16) & 0xFFFF;
                int c = (int) nums[nIdx] & 0xFFFF;
                int pr = getIdx(ufind, r);
                int pc = getIdx(ufind, rowCount + c);
                if (pr != pc) {
                    ufind[pr] =
                            Math.min(
                                    Math.min(ufind[pr], ufind[pc]),
                                    -Math.max(rows[r], cols[c]) - 1);
                    ufind[pc] = pr;
                }
            }
            for (int nIdx = startIdx; nIdx < endIdx; nIdx++) {
                int r = ((int) nums[nIdx] >> 16) & 0xFFFF;
                int c = (int) nums[nIdx] & 0xFFFF;
                matrix[r][c] = rows[r] = cols[c] = -ufind[getIdx(ufind, r)];
            }
        }
    }

    private int getIdx(int[] ufind, int idx) {
        if (ufind[idx] < 0) {
            return idx;
        } else {
            ufind[idx] = getIdx(ufind, ufind[idx]);
            return ufind[idx];
        }
    }
}