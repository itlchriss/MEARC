package g2301_2400.s2399_check_distances_between_same_letters;

// #Easy #Array #String #Hash_Table #2022_09_19_Time_1_ms_(99.88%)_Space_43.8_MB_(22.33%)

public class Solution {
	//@ requires(*The input string `s` must have a length greater than or equal to 2 and less than or equal to 52.*);
	//@ requires(*The input string `s` must consist only of lowercase English letters.*);
	//@ requires(*Each letter in the input string `s` must appear exactly twice.*);
	//@ requires(*The length of the input array `distance` must be 26.*);
	//@ requires(*Each element in the input array `distance` must be between 0 and 50 (inclusive).*);
	//@ ensures(*The method should return a boolean value indicating whether the input string `s` is a well-spaced string.*);
	//@ ensures(*If the input string `s` is a well-spaced string, the method should return `true`.*);
	//@ ensures(*If the input string `s` is not a well-spaced string, the method should return `false`.*);
    public boolean checkDistances(String s, int[] distance) {
        boolean[] seenFirstIndexYet = new boolean[26];
        for (int idxIntoS = 0; idxIntoS < s.length(); ++idxIntoS) {
            char c = s.charAt(idxIntoS);
            if (!seenFirstIndexYet[c - 'a']) {
                seenFirstIndexYet[c - 'a'] = true;
                distance[c - 'a'] += idxIntoS;
            } else {
                // seenFirstIndexYet[c - 'a']
                distance[c - 'a'] -= idxIntoS;
                if (distance[c - 'a'] != -1) {
                    // early return
                    return false;
                }
            }
        }
        return true;
    }
}