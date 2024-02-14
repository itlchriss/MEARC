package g2901_3000.s2906_construct_product_matrix;

// #Medium #Array #Matrix #Prefix_Sum #2023_12_26_Time_11_ms_(84.62%)_Space_73.2_MB_(28.57%)

public class Solution {
	//@ requires(*The input `grid` is a 2D integer matrix of size `n * m`.*);
	//@ requires(*The size of `grid` is within the constraints: `1 <= n == grid.length <= 10^5` and `1 <= m == grid[i].length <= 10^5`.*);
	//@ requires(*The product of `n` and `m` is within the constraint: `2 <= n * m <= 10^5`.*);
	//@ requires(*Each element in `grid` is within the constraint: `1 <= grid[i][j] <= 10^9`.*);
	//@ ensures(*The method returns a 2D integer matrix `p` of size `n * m`.*);
	//@ ensures(*Each element `p[i][j]` is calculated as the product of all elements in `grid` except for the element `grid[i][j]`.*);
	//@ ensures(*The product `p[i][j]` is taken modulo `12345`.*);
	//@ ensures(*The method returns the product matrix `p`.*);
    public int[][] constructProductMatrix(int[][] grid) {
        long prod = 1;
        int[][] ans = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                ans[i][j] = (int) prod;
                prod = (prod * grid[i][j]) % 12345;
            }
        }
        prod = 1;
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                ans[i][j] = (ans[i][j] * (int) prod) % 12345;
                prod *= grid[i][j];
                prod = prod % 12345;
            }
        }
        return ans;
    }
}