package g0301_0400.s0357_count_numbers_with_unique_digits;

// #Medium #Dynamic_Programming #Math #Backtracking
// #2022_07_11_Time_0_ms_(100.00%)_Space_41.2_MB_(23.67%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 8 and is greater than or equal to 0.*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to 1000000000.*);
//@ ensures(*If the integer parameter `n` is equal to 2, the integer result is equal to 91.*);
//@ ensures(*If the integer parameter `n` is equal to 0, the integer result is equal to 1.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the integer result is equal to 10.*);
//@ ensures(*If the integer parameter `n` is equal to 3, the integer result is equal to 739.*);
//@ ensures(*If the integer parameter `n` is equal to 4, the integer result is equal to 5275.*);
//@ ensures(*If the integer parameter `n` is equal to 5, the integer result is equal to 32491.*);
//@ ensures(*If the integer parameter `n` is equal to 6, the integer result is equal to 1688228.*);
//@ ensures(*If the integer parameter `n` is equal to 7, the integer result is equal to 83621143.*);
//@ ensures(*If the integer parameter `n` is equal to 8, the integer result is equal to 385875490.*);
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