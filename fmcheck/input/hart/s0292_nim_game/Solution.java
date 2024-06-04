package g0201_0300.s0292_nim_game;

// #Easy #Math #Game_Theory #Brainteaser #2022_07_06_Time_0_ms_(100.00%)_Space_40.6_MB_(71.00%)

import java.util.Arrays;

import java.util.Collections;

public class Solution {
//@ requires((n >= 1) && (n <= 2147483647));
//@ ensures((n == 1) ==> (\result == true));
//@ ensures((n == 4) ==> (\result == false));
//@ ensures((n == 2) ==> (\result == true));
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
