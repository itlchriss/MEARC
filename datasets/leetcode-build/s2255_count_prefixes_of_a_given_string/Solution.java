package g2201_2300.s2255_count_prefixes_of_a_given_string;

// #Easy #Array #String #2022_06_12_Time_1_ms_(94.82%)_Space_44.1_MB_(51.71%)

public class Solution {
	//@ requires(*The input array `words` is not null.*);
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of `words` is greater than or equal to - The length of each string in `words` is greater than or equal to - The length of `s` is greater than or equal to - Each string in `words` and `s` consists only of lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the number of strings in `words` that are a prefix of `s`.*);
    public int countPrefixes(String[] words, String s) {
        int count = 0;
        for (String str : words) {
            if (s.indexOf(str) == 0) {
                ++count;
            }
        }
        return count;
    }
}