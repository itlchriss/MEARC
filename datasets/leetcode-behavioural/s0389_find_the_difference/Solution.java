package g0301_0400.s0389_find_the_difference;

// #Easy #String #Hash_Table #Sorting #Bit_Manipulation #Programming_Skills_I_Day_8_String
// #Udemy_Bit_Manipulation #2022_07_13_Time_1_ms_(100.00%)_Space_42.3_MB_(48.30%)

public class Solution {
//@ ensures(*The string parameter `s` must not be null.*);
//@ ensures(*The string parameter `t` must not be null.*);
//@ ensures(*The length of string parameter `t` must be one more than the length of string parameter `s`.*);
//@ ensures(*The character result is the character that was added to string parameter `t` after shuffling string parameter `s`.*);
    public char findTheDifference(String s, String t) {
        char c = 0;
        for (char cs : s.toCharArray()) {
            c ^= cs;
        }
        for (char ct : t.toCharArray()) {
            c ^= ct;
        }
        return c;
    }
}