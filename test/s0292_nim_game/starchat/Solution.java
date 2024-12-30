package g0201_0300.s0292_nim_game;

// #Easy #Math #Game_Theory #Brainteaser #2022_07_06_Time_0_ms_(100.00%)_Space_40.6_MB_(71.00%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 231 - 1 and is greater than or equal to 1.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification:2521\. Maximum Score From Removing Substrings*);
//@ requires(*Hard*);
//@ requires(*You are given a string `s` of lowercase English letters and an array of pairs of integers `pairs` where `pairs[i] = [lefti, righti]` indicates the `ith` pair of indices (0-indexed).*);
//@ requires(**);
//@ requires(**);
//@ requires(*Return _the **maximum score** you can obtain after applying the above operation on_ `s` _once_.*);
//@ requires(**);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "abcdebaeeef", pairs = \[\[3,8\],\[1,9\],\[4,10\]\]*);
//@ requires(***Output:** 23*);
//@ requires(***Explanation:***);
//@ requires(*Remove the first pair \[3,8\] and concatenate the remaining strings.*);
//@ requires(*s =  "abcde" +  "baeeef"*);
//@ requires(*    The score is 10 + 6 = 16.*);
//@ requires(**);
//@ requires(*Then, remove the second pair \[1,9\] and concatenate the remaining strings.*);
//@ requires(*s =  "abc" +  "deef"*);
//@ requires(*    The score is 3 + 4 = 7.*);
//@ requires(**);
//@ requires(*Then, remove the third pair \[4,10\] and concatenate the remaining string.*);
//@ requires(*s =  "ab" +  "cdef"*);
//@ requires(*    The score is 2 + 4 = 6.*);
//@ requires(**);
//@ requires(*The maximum score is 23.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "a", pairs = \[\[0,0\]\]*);
//@ requires(***Output:** 0*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 105`*);
//@ requires(**   `s` consists of lowercase English letters.*);
//@ requires(**   `0 <= pairs.length <= 105`*);
//@ requires(**   `0 <= lefti <= righti < s.length`*);
//@ requires(**   At most `105` pairs will be valid.*);
//@ requires(**);
//@ requires(***Follow up:** Can you solve this problem in `O(n)` time complexity?*);
//@ ensures(*The boolean result is `true` if and only if the first player can win the game assuming both players play optimally.*);
//@ ensures(*The boolean result is `false` if and only if the first player cannot win the game assuming both players play optimally.*);
//@ ensures(*If the integer parameter `n` is equal to 4, the boolean result is `false`.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the boolean result is `true`.*);
//@ ensures(*If the integer parameter `n` is equal to 2, the boolean result is `true`.*);
//@ ensures(*You can choose one pair to **remove** from `s`, and the strings between `lefti` and `righti` are **concatenated** to form the new string. If you remove the last pair, the resulting string will be empty (`""`).*);
//@ ensures(*A **score** is the number of letters in the resulting string.*);
//@ ensures(***Explanation:** If you remove the pair, the string will be empty, resulting in a score of 0.*);
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}