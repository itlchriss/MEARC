package g0001_0100.s0085_maximal_rectangle;

// #Hard #Array #Dynamic_Programming #Matrix #Stack #Monotonic_Stack
// #2022_06_20_Time_3_ms_(99.68%)_Space_46.4_MB_(89.77%)

public class Solution {
	//@ requires(*1. The input matrix must not be null.*);
	//@ requires(*2. The input matrix must have at least one row.*);
	//@ requires(*3. The input matrix must have at least one column.*);
	//@ requires(*4. The input matrix must contain only '0' and '1' characters.*);
	//@ ensures(*1. The method returns an integer representing the area of the largest rectangle containing only '1's in the input matrix.*);
	//@ ensures(*2. If the input matrix is empty, the method returns 0.*);
	//@ ensures(*3. If the input matrix contains only '0's, the method returns 0.*);
	//@ ensures(*4. If the input matrix contains only '1's, the method returns the total number of '1's in the matrix.*);
	//@ ensures(*5. The input matrix is not modified by the method.*);
    public int maximalRectangle(char[][] matrix) {
        /*
         * idea: using [LC84 Largest Rectangle in Histogram]. For each row of the matrix, construct
         * the histogram based on the current row and the previous histogram (up to the previous
         * row), then compute the largest rectangle area using LC84.
         */
        int m = matrix.length;
        int n;
        if (m == 0 || (n = matrix[0].length) == 0) {
            return 0;
        }
        int i;
        int j;
        int res = 0;
        int[] heights = new int[n];
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j] += 1;
                }
            }
            res = Math.max(res, largestRectangleArea(heights));
        }

        return res;
    }

    private int largestRectangleArea(int[] heights) {
        /*
         * idea: scan and store if a[i-1]<=a[i] (increasing), then as long as a[i]<a[i-1], then we
         * can compute the largest rectangle area with base a[j], for j<=i-1, and a[j]>a[i], which
         * is a[j]*(i-j). And meanwhile, all these bars (a[j]'s) are already done, and thus are
         * throwable (using pop() with a stack).
         *
         * <p>We can use an array nLeftGeq[] of size n to simulate a stack. nLeftGeq[i] = the number
         * of elements to the left of [i] having value greater than or equal to a[i] (including a[i]
         * itself). It is also the index difference between [i] and the next index on the top of the
         * stack.
         */
        int n = heights.length;
        if (n == 0) {
            return 0;
        }
        int[] nLeftGeq = new int[n];
        // the number of elements to the left
        // of [i] with value >= heights[i]
        nLeftGeq[0] = 1;
        // preIdx=the index of stack.peek(), res=max area so far
        int preIdx = 0;
        int res = 0;
        for (int i = 1; i < n; i++) {
            nLeftGeq[i] = 1;
            // notice that preIdx = i - 1 = peek()
            while (preIdx >= 0 && heights[i] < heights[preIdx]) {
                res = Math.max(res, heights[preIdx] * (nLeftGeq[preIdx] + i - preIdx - 1));
                // pop()
                nLeftGeq[i] += nLeftGeq[preIdx];
                // peek() current top
                preIdx = preIdx - nLeftGeq[preIdx];
            }
            if (preIdx >= 0 && heights[i] == heights[preIdx]) {
                // pop()
                nLeftGeq[i] += nLeftGeq[preIdx];
            }
            // otherwise nothing to do
            preIdx = i;
        }
        // compute the rest largest rectangle areas with (indices of) bases
        // on stack
        while (preIdx >= 0 && 0 < heights[preIdx]) {
            res = Math.max(res, heights[preIdx] * (nLeftGeq[preIdx] + n - preIdx - 1));
            // peek() current top
            preIdx = preIdx - nLeftGeq[preIdx];
        }
        return res;
    }
}