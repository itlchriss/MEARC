package g2101_2200.s2186_minimum_number_of_steps_to_make_two_strings_anagram_ii;

// #Medium #String #Hash_Table #Counting #2022_06_08_Time_22_ms_(77.11%)_Space_70.1_MB_(39.92%)

public class Solution {
	//@ requires(*The input strings `s` and `t` are not null.*);
	//@ requires(*The lengths of `s` and `t` are both greater than or equal to 1.*);
	//@ requires(*The lengths of `s` and `t` are both less than or equal to 2 * 10^5.*);
	//@ requires(*`s` and `t` consist of lowercase English letters.*);
	//@ ensures(*The return value is an integer representing the minimum number of steps required to make `s` and `t` anagrams of each other.*);
	//@ ensures(*The characters in `s` and `t` are in the same order as the original strings.*);
	//@ ensures(*The characters in `s` and `t` are the same as the original strings, but may be rearranged.*);
	//@ ensures(*The lengths of `s` and `t` remain the same as the original strings.*);
	//@ ensures(*The characters in `s` and `t` are lowercase English letters.*);
    public int minSteps(String s, String t) {
        int[] a = new int[26];
        for (int i = 0; i < s.length(); i++) {
            a[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            a[t.charAt(i) - 'a']--;
        }
        int sum = 0;
        for (int j : a) {
            sum += Math.abs(j);
        }
        return sum;
    }
}