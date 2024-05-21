package g0501_0600.s0504_base_7;

// #Easy #Math #2022_07_24_Time_0_ms_(100.00%)_Space_39.4_MB_(98.67%)

public class Solution {
//@ requires(*Example 1:*);
//@ requires(*Input: num = 100*);
//@ requires(*Output: "202"*);
//@ requires(*Example 2:*);
//@ requires(*Input: num = -7*);
//@ requires(*Output: "-10"*);
//@ requires(*Constraints:*);
//@ requires(*<code>-10<sup>7</sup> <= num <= 10<sup>7</sup></code>*);
//@ ensures(*Given an integer param_num, the result is a string of its base 7 representation.*);
    public String convertToBase7(int num) {
        return Integer.toString(num, 7);
    }
}