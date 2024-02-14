package g0601_0700.s0668_kth_smallest_number_in_multiplication_table;

// #Hard #Math #Binary_Search #2022_03_22_Time_12_ms_(97.84%)_Space_39.1_MB_(74.34%)

public class Solution {
	//@ requires(*1. The values of `m`, `n`, and `k` are positive integers.*);
	//@ requires(*2. The values of `m` and `n` are less than or equal to 30000.*);
	//@ requires(*3. The value of `k` is less than or equal to the product of `m` and `n`.*);
	//@ ensures(*1. The method returns an integer value.*);
	//@ ensures(*2. The returned value is the kth smallest element in the multiplication table of size `m x n`.*);
	//@ ensures(*3. The returned value is greater than or equal to 1.*);
	//@ ensures(*4. The returned value is less than or equal to the product of `m` and `n`.*);
    public int findKthNumber(int m, int n, int k) {
        int lo = 1;
        int hi = m * n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int col = n;
            int row = 1;
            int count = 0;
            while (row <= m && col >= 1) {
                int val = row * col;
                if (val > mid) {
                    col--;
                } else {
                    count += col;
                    row++;
                }
            }
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}