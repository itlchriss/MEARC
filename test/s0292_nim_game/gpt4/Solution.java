package g0201_0300.s0292_nim_game;

// #Easy #Math #Game_Theory #Brainteaser #2022_07_06_Time_0_ms_(100.00%)_Space_40.6_MB_(71.00%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 2147483647 and is greater than or equal to 1.*);
//@ ensures(*If the integer parameter `n` is equal to 4, the boolean result is equal to the false literal.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the boolean result is equal to the true literal.*);
//@ ensures(*If the integer parameter `n` is equal to 2, the boolean result is equal to the true literal.*);
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}