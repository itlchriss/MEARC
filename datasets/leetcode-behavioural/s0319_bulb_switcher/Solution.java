package g0301_0400.s0319_bulb_switcher;

// #Medium #Math #Brainteaser #2022_07_08_Time_0_ms_(100.00%)_Space_41.1_MB_(27.19%)

public class Solution {
//@ ensures(*The integer parameter `n` must be non-negative.*);
//@ ensures(*The integer result is the number of bulbs that are on after `n` rounds.*);
    public int bulbSwitch(int n) {
        if (n < 2) {
            return n;
        }
        return (int) Math.sqrt(n);
    }
}