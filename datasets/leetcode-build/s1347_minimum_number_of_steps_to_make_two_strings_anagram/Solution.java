package g1301_1400.s1347_minimum_number_of_steps_to_make_two_strings_anagram;

// #Medium #String #Hash_Table #Counting #2022_03_21_Time_13_ms_(76.34%)_Space_54.6_MB_(41.61%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input strings `s` and `t` are not null.*);
	//@ requires(*The lengths of `s` and `t` are equal.*);
	//@ requires(*`s` and `t` consist of lowercase English letters only.*);
	//@ ensures(*The method returns an integer representing the minimum number of steps required to make `t` an anagram of `s`.*);
    public int minSteps(String s, String t) {
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            if (counts[c - 'a'] > 0) {
                counts[c - 'a']--;
            }
        }
        return Arrays.stream(counts).sum();
    }
}