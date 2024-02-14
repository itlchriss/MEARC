package g0701_0800.s0793_preimage_size_of_factorial_zeroes_function;

// #Hard #Math #Binary_Search #2022_03_26_Time_0_ms_(100.00%)_Space_41.3_MB_(22.94%)

public class Solution {
	//@ requires(*The input `k` is a non-negative integer.*);
	//@ ensures(*The method returns an integer representing the number of non-negative integers `x` such that `f(x) = k`.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
	//@ ensures(*The returned value is less than or equal to 10^9.*);
    public int preimageSizeFZF(int k) {
        long left = 0;
        long right = 5L * (k + 1);
        while (left < right - 1) {
            long mid = left + (right - left) / 2;
            int cnt = countZeros(mid);
            if (cnt == k) {
                return 5;
            } else if (cnt < k) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return countZeros(left) == k || countZeros(right) == k ? 5 : 0;
    }

    private int countZeros(long n) {
        long rst = 0;
        while (n > 0) {
            rst += n / 5;
            n /= 5;
        }
        return (int) rst;
    }
}