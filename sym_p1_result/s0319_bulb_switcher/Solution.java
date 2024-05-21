package g0301_0400.s0319_bulb_switcher;

// #Medium #Math #Brainteaser #2022_07_08_Time_0_ms_(100.00%)_Space_41.1_MB_(27.19%)

public class Solution {
//@ requires(*There are param_n bulbs that are initially off.*);
//@ requires(*You first turn on all the bulbs, then you turn off every second bulb.*);
//@ requires(*On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on).*);
//@ requires(*For the <code>i<sup>th</sup></code> round, you toggle every `i` bulb.*);
//@ requires(*For the <code>n<sup>th</sup></code> round, you only toggle the last bulb.*);
//@ requires(*Return the number of bulbs that are on after param_n rounds.*);
//@ requires(*Example 1:*);
//@ requires(*![](*);
//@ requires(*https://assets.leetcode.com/uploads/2020/11/05/bulb.jpg)*);
//@ requires(*Input: n = 3*);
//@ requires(*Output: 1*);
//@ requires(*Explanation: At first, the three bulbs are [off, off, off].*);
//@ requires(*After the first round, the three bulbs are [on, on, on].*);
//@ requires(*After the second round, the three bulbs are [on, off, on].*);
//@ requires(*After the third round, the three bulbs are [on, off, off].*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 0*);
//@ requires(*Output: 0*);
//@ requires(*Example 3:*);
//@ requires(*Input: n = 1*);
//@ requires(*Output: 1*);
//@ requires(*Constraints:*);
//@ requires(*<code>0 <= n <= 10<sup>9</sup></code>*);
//@ ensures(*So you should the result is 1 because there is only one bulb is on.*);
    public int bulbSwitch(int n) {
        if (n < 2) {
            return n;
        }
        return (int) Math.sqrt(n);
    }
}