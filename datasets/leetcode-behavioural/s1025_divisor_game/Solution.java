package g1001_1100.s1025_divisor_game;

// #Easy #Dynamic_Programming #Math #Game_Theory #Brainteaser
// #2022_02_26_Time_0_ms_(100.00%)_Space_40.7_MB_(27.10%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer greater than 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if Alice wins the game, and `false` otherwise.*);
//@ ensures(*The method modifies the value of `n` on the chalkboard according to the rules of the game.*);
//@ ensures(*The method assumes that both players play optimally.*);
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}