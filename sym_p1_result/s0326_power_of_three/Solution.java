package g0301_0400.s0326_power_of_three;

// #Easy #Top_Interview_Questions #Math #Recursion
// #2022_07_09_Time_18_ms_(85.35%)_Space_47.9_MB_(14.68%)

public class Solution {
    // regular method that has a loop
//@ requires(*An integer param_n is a power of three, if there exists an integer `x` such that <code>n == 3<sup>x</sup></code>.*);
//@ requires(*Example 1:*);
//@ requires(*Input: n = 27*);
//@ requires(*Output: true*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 0*);
//@ requires(*Output: false*);
//@ requires(*Example 3:*);
//@ requires(*Input: n = 9*);
//@ requires(*Output: true*);
//@ requires(*Constraints:*);
//@ requires(*<code>-2<sup>31</sup> <= n <= 2<sup>31</sup> - 1</code>*);
//@ requires(*Follow up: Could you solve it without loops/recursion?*);
//@ ensures(*Given an integer param_n, the result is `true` if it is a power of three.*);
//@ ensures(*Otherwise, the result is `false`.*);
    public boolean isPowerOfThree(int n) {
        if (n < 3 && n != 1) {
            return false;
        }
        while (n != 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}