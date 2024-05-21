package g0201_0300.s0258_add_digits;

// #Easy #Math #Simulation #Number_Theory #2022_07_05_Time_1_ms_(100.00%)_Space_39.3_MB_(98.44%)

public class Solution {
//@ requires(*Example 1:*);
//@ requires(*Input: num = 38*);
//@ requires(*Output: 2*);
//@ requires(*Explanation:*);
//@ requires(*The process is*);
//@ requires(*38 --> 3 + 8 --> 11*);
//@ requires(*11 --> 1 + 1 --> 2*);
//@ requires(*Example 2:*);
//@ requires(*Input: num = 0*);
//@ requires(*Output: 0*);
//@ requires(*Constraints:*);
//@ requires(*<code>0 <= num <= 2<sup>31</sup> - 1</code>*);
//@ requires(*Follow up: Could you do it without any loop/recursion in `O(1)` runtime?*);
//@ ensures(*Given an integer param_num, repeatedly add all its digits until the result has only one digit, and the result is it.*);
//@ ensures(*Since 2 has only one digit, the result is it.*);
    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }
        if (num % 9 == 0) {
            return 9;
        }
        return num % 9;
    }
}