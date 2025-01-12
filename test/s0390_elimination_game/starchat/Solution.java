package g0301_0400.s0390_elimination_game;

// #Medium #Math #Recursion #2022_07_13_Time_4_ms_(81.16%)_Space_42_MB_(71.14%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 1000000000 and is greater than or equal to 1.*);
//@ requires(*The algorithm has a time complexity of O(log n), where n is the integer parameter.*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to the integer parameter `n`.*);
//@ ensures(*If the integer parameter `n` is equal to 9, the integer result is equal to 6.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the integer result is equal to 1.*);
//@ ensures(*The integer result is the last number that remains in the list after applying the algorithm on the list of all integers in the range [1, n] sorted in a strictly increasing order.*);
//@ ensures(*The algorithm is deterministic and will always produce the same result for the same input.*);
//@ ensures(*The algorithm has a space complexity of O(1), as it does not require any additional data structures to store intermediate results.*);
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 - lastRemaining(n / 2) + 1);
    }
}