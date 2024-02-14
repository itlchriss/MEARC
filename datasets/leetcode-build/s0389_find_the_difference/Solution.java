package g0301_0400.s0389_find_the_difference;

// #Easy #String #Hash_Table #Sorting #Bit_Manipulation #Programming_Skills_I_Day_8_String
// #Udemy_Bit_Manipulation #2022_07_13_Time_1_ms_(100.00%)_Space_42.3_MB_(48.30%)

public class Solution {
	//@ requires(*The input strings `s` and `t` must not be null.*);
	//@ requires(*The length of string `t` must be one more than the length of string `s`.*);
	//@ requires(*The strings `s` and `t` must consist of lowercase English letters.*);
	//@ ensures(*The output must be a single character.*);
	//@ ensures(*The output character must be the letter that was added to string `t` after shuffling string `s`.*);
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