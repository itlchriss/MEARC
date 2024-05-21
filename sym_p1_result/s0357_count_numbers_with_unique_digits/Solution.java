package g0301_0400.s0357_count_numbers_with_unique_digits;

// #Medium #Dynamic_Programming #Math #Backtracking
// #2022_07_11_Time_0_ms_(100.00%)_Space_41.2_MB_(23.67%)

public class Solution {
//@ requires(*Example 1:*);
//@ requires(*Input: n = 2*);
//@ requires(*Output: 91*);
//@ requires(*Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100, excluding 11,22,33,44,55,66,77,88,99*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 0*);
//@ requires(*Output: 1*);
//@ requires(*Constraints:*);
//@ requires(*`0 <= n <= 8`*);
//@ ensures(*Given an integer param_n, the result is the count of all numbers with unique digits, `x`, where <code>0 <= x < 10<sup>n</sup></code>.*);
    public int countNumbersWithUniqueDigits(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            int mul = 1;
            for (int j = 1; j < i; j++) {
                mul *= (10 - j);
            }
            ans = ans + 9 * mul;
        }
        return ans;
    }
}