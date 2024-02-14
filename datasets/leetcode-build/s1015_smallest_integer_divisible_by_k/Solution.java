package g1001_1100.s1015_smallest_integer_divisible_by_k;

// #Medium #Hash_Table #Math #2022_02_25_Time_2_ms_(90.67%)_Space_40.5_MB_(14.47%)

public class Solution {
	//@ requires(*The input `k` is a positive integer.*);
	//@ requires(*`k` is less than or equal to 10^5.*);
	//@ ensures(*The method returns an integer representing the length of the smallest positive integer `n` that is divisible by `k` and only contains the digit `1`.*);
	//@ ensures(*If there is no such `n`, the method returns -1.*);
    public int smallestRepunitDivByK(int k) {
        int n = 0;
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        int i = 1;
        while (i <= k) {
            n = (n * 10 + 1) % k;
            if (n == 0) {
                return i;
            }
            i++;
        }
        return -1;
    }
}