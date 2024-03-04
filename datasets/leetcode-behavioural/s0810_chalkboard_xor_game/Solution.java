package g0801_0900.s0810_chalkboard_xor_game;

// #Hard #Array #Math #Bit_Manipulation #Game_Theory #Brainteaser
// #2022_03_23_Time_0_ms_(100.00%)_Space_41.6_MB_(95.74%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are non-negative integers.*);
//@ ensures(*The elements in the input array `nums` are less than 2^16.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether Alice wins the game.*);
//@ ensures(*If Alice wins the game, the method returns `true`.*);
//@ ensures(*If Alice loses the game, the method returns `false`.*);
    public boolean xorGame(int[] nums) {
        int xor = 0;
        for (int i : nums) {
            xor = xor ^ i;
        }
        return xor == 0 || (nums.length & 1) == 0;
    }
}