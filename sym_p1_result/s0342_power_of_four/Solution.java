package g0301_0400.s0342_power_of_four;

// #Easy #Math #Bit_Manipulation #Recursion #2022_07_10_Time_1_ms_(100.00%)_Space_41.2_MB_(55.90%)

public class Solution {
//@ requires(*An integer param_n is a power of four, if there exists an integer `x` such that <code>n == 4<sup>x</sup></code>.*);
//@ requires(*Example 1:*);
//@ requires(*Input: n = 16*);
//@ requires(*Output: true*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 5*);
//@ requires(*Output: false*);
//@ requires(*Example 3:*);
//@ requires(*Input: n = 1*);
//@ requires(*Output: true*);
//@ requires(*Constraints:*);
//@ requires(*<code>-2<sup>31</sup> <= n <= 2<sup>31</sup> - 1</code>*);
//@ requires(*Follow up: Could you solve it without loops/recursion?*);
//@ ensures(*Given an integer param_n, the result is `true` if it is a power of four.*);
//@ ensures(*Otherwise, the result is `false`.*);
    public boolean isPowerOfFour(int n) {
        while (n >= 4) {
            if (n % 4 != 0) {
                return false;
            }
            n = n / 4;
        }
        return n == 1;
    }
}