package g0301_0400.s0390_elimination_game;

// #Medium #Math #Recursion #2022_07_13_Time_4_ms_(81.16%)_Space_42_MB_(71.14%)

public class Solution {
//@ requires(*You have a list `arr` of all integers in the range `[1, n]` sorted in a strictly increasing order.*);
//@ requires(*Apply the following algorithm on `arr`:*);
//@ requires(*Starting from left to right, remove the first number and every other number afterward until you reach the end of the list.*);
//@ requires(*Repeat the previous step again, but this time from right to left, remove the rightmost number and every other number from the remaining numbers.*);
//@ requires(*Keep repeating the steps again, alternating left to right and right to left, until a single number remains.*);
//@ requires(*Example 1:*);
//@ requires(*Input: n = 9*);
//@ requires(*Output: 6*);
//@ requires(*Explanation:*);
//@ requires(*arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]*);
//@ requires(*arr = [2, 4, 6, 8]*);
//@ requires(*arr = [2, 6]*);
//@ requires(*arr = [6]*);
//@ requires(*Example 2:*);
//@ requires(*Input: n = 1*);
//@ requires(*Output: 1*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= n <= 10<sup>9</sup></code>*);
//@ ensures(*Given the integer param_n, the result is the last number that remains in `arr`.*);
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 - lastRemaining(n / 2) + 1);
    }
}