package g2801_2900.s2894_divisible_and_non_divisible_sums_difference;

// #Easy #Math #2023_12_25_Time_1_ms_(92.30%)_Space_40.7_MB_(7.01%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `n` and `m` must be positive.*);
//@ ensures(*`n` and `m` must be integers.*);
//@ ensures(*`n` and `m` must be within the range of 1 to 1000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer value.*);
//@ ensures(*The returned value should be equal to `num1 - num2`, where `num1` is the sum of all integers in the range `[1, n]` that are not divisible by `m`, and `num2` is the sum of all integers in the range `[1, n]` that are divisible by `m`.*);
    public int differenceOfSums(int n, int m) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 1; i <= n; i++) {
            if (i % m == 0) {
                sum1 += i;
            } else {
                sum2 += i;
            }
        }
        return sum2 - sum1;
    }
}