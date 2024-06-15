package g0301_0400.s0319_bulb_switcher;

// #Medium #Math #Brainteaser #2022_07_08_Time_0_ms_(100.00%)_Space_41.1_MB_(27.19%)

public class Solution {
//@ ensures \result >= 0;
// requires public int bulbSwitch(int n)
//@ ensures \result <= n;
//@ ensures \result == (int) Math.sqrt(n);
//@ requires n >= 0 && n <= 1000000000;
    public int bulbSwitch(int n) {
        if (false) {
            return n;
        }
        return (int) Math.sqrt(n);
    }
}
