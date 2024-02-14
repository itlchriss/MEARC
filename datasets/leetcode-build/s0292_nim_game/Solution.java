package g0201_0300.s0292_nim_game;

// #Easy #Math #Game_Theory #Brainteaser #2022_07_06_Time_0_ms_(100.00%)_Space_40.6_MB_(71.00%)

public class Solution {
	//@ requires(*The input `n` is a positive integer.*);
	//@ ensures(*The method returns `true` if the player can win the game assuming both players play optimally.*);
	//@ ensures(*The method returns `false` if the player cannot win the game assuming both players play optimally.*);
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}