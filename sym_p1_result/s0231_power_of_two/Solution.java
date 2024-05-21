package g0201_0300.s0231_power_of_two;

// #Easy #Math #Bit_Manipulation #Recursion #Algorithm_I_Day_13_Bit_Manipulation
// #2022_07_04_Time_1_ms_(100.00%)_Space_39.6_MB_(90.19%)

public class Solution {
//@ requires(*An integer param_n is a power of two, if there exists an integer `x` such that <code>n == 2<sup>x</sup></code>.*);
//@ requires(*Example 1:*);
//@ requires(*Input: n = 1*);
//@ requires(*Output: true*);
//@ requires(*Explanation: 2<sup>0</sup> = 1*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 16*);
//@ requires(*Output: true*);
//@ requires(*Explanation: 2<sup>4</sup> = 16*);
//@ requires(*Example 3:*);
//@ requires(*Input: n = 3*);
//@ requires(*Output: false*);
//@ requires(*Example 4:*);
//@ requires(*Input: n = 4*);
//@ requires(*Output: true*);
//@ requires(*Example 5:*);
//@ requires(*Input: n = 5*);
//@ requires(*Output: false*);
//@ requires(*Constraints:*);
//@ requires(*<code>-2<sup>31</sup> <= n <= 2<sup>31</sup> - 1</code>*);
//@ requires(*Follow up: Could you solve it without loops/recursion?*);
//@ ensures(*Given an integer param_n, the result is `true` if it is a power of two.*);
//@ ensures(*Otherwise, the result is `false`.*);
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        while (true) {
            if (n == 1) {
                return true;
            }
            if (n % 2 == 1) {
                return false;
            }
            n /= 2;
        }
    }
}