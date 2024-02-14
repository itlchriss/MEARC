package g0401_0500.s0409_longest_palindrome;

// #Easy #String #Hash_Table #Greedy #Data_Structure_II_Day_6_String #Level_1_Day_5_Greedy
// #2022_07_16_Time_2_ms_(92.90%)_Space_40.5_MB_(95.32%)

import java.util.BitSet;

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` is not empty.*);
	//@ requires(*3. The input string `s` consists of lowercase and/or uppercase English letters only.*);
	//@ ensures(*1. The method returns an integer representing the length of the longest palindrome that can be built with the letters in the input string `s`.*);
    public int longestPalindrome(String s) {
        BitSet set = new BitSet(60);
        for (char c : s.toCharArray()) {
            set.flip(c - 'A');
        }
        if (set.isEmpty()) {
            return s.length();
        }
        return s.length() - set.cardinality() + 1;
    }
}