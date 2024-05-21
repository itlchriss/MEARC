package g0201_0300.s0292_nim_game;

// #Easy #Math #Game_Theory #Brainteaser #2022_07_06_Time_0_ms_(100.00%)_Space_40.6_MB_(71.00%)

public class Solution {
//@ requires(*You are playing the following Nim Game with your friend:*);
//@ requires(*Initially, there is a heap of stones on the table.*);
//@ requires(*You and your friend will alternate taking turns, and you go first.*);
//@ requires(*On each turn, the person whose turn it is will remove 1 to 3 stones from the heap.*);
//@ requires(*The one who removes the last stone is the winner.*);
//@ requires(*Example 1:*);
//@ requires(*Input: n = 4*);
//@ requires(*Output: false*);
//@ requires(*Explanation:*);
//@ requires(*These are the possible outcomes:*);
//@ requires(*1.*);
//@ requires(*You remove 1 stone.*);
//@ requires(*Your friend removes 3 stones, including the last stone.*);
//@ requires(*Your friend wins.*);
//@ requires(*2.*);
//@ requires(*You remove 2 stones.*);
//@ requires(*Your friend removes 2 stones, including the last stone.*);
//@ requires(*Your friend wins.*);
//@ requires(*3.*);
//@ requires(*You remove 3 stones.*);
//@ requires(*Your friend removes the last stone.*);
//@ requires(*Your friend wins.*);
//@ requires(*In all outcomes, your friend wins.*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 1*);
//@ requires(*Output: true*);
//@ requires(*Example 3:*);
//@ requires(*Input: n = 2*);
//@ requires(*Output: true*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= n <= 2<sup>31</sup> - 1</code>*);
//@ ensures(*Given param_n, the number of stones in the heap, the result is `true` if you can win the game assuming both you and your friend play optimally, otherwise the result is `false`.*);
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}