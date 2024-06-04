package g0201_0300.s0292_nim_game;

// #Easy #Math #Game_Theory #Brainteaser #2022_07_06_Time_0_ms_(100.00%)_Space_40.6_MB_(71.00%)

public class Solution {
//@ requires n >= 1 && n <= Math.pow(2, 31) - 1;
// requires public boolean canWinNim(int n)
//@ ensures \result == true || \result == false;
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
