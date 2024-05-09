package g0301_0400.s0319_bulb_switcher;

// #Medium #Math #Brainteaser #2022_07_08_Time_0_ms_(100.00%)_Space_41.1_MB_(27.19%)

public class Solution {
//@ requires(*The integer parameter `n` is greater than or equal to 0 and is less than or equal to 10^9.*);
//@ requires(*The number of bulbs that are initially off is equal to `n`.*);
//@ requires(*After each round, the bulbs are toggled based on the round number. On the `i`th round, every `i` bulb is toggled.*);
//@ requires(*The bulbs are toggled from off to on and vice versa.*);
//@ ensures(*The integer result is the number of bulbs that are on after `n` rounds.*);
//@ ensures(*The integer result is the count of bulbs that are on after `n` rounds.*);
    public int bulbSwitch(int n) {
        if (n < 2) {
            return n;
        }
        return (int) Math.sqrt(n);
    }
}