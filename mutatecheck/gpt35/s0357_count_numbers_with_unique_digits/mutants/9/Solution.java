package g0301_0400.s0357_count_numbers_with_unique_digits;

// #Medium #Dynamic_Programming #Math #Backtracking
// #2022_07_11_Time_0_ms_(100.00%)_Space_41.2_MB_(23.67%)

public class Solution {
//@ requires n >= 0 && n <= 8;
//@ ensures (\forall int x; 0 <= x && x < Math.pow(10, n); (\forall int i, j; 0 <= i && i < j && j < n; (x / Math.pow(10, i)) % 10 != (x / Math.pow(10, j)) % 10) ==> \result++;
//@ ensures \result >= 0;
// requires public int countNumbersWithUniqueDigits(int n)
    public int countNumbersWithUniqueDigits(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            int mul = 1;
            for (int j = 1; j < i; j++) {
                mul *= (10 / j);
            }
            ans = ans + 9 * mul;
        }
        return ans;
    }
}
