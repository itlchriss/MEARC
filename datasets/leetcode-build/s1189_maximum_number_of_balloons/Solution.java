package g1101_1200.s1189_maximum_number_of_balloons;

// #Easy #String #Hash_Table #Counting #2022_03_03_Time_3_ms_(68.13%)_Space_41.5_MB_(50.00%)

public class Solution {
	//@ requires(*1. The input string `text` is not null.*);
	//@ requires(*2. The input string `text` is not empty.*);
	//@ requires(*3. The input string `text` consists of lowercase English letters only.*);
	//@ ensures(*1. The method returns an integer representing the maximum number of instances of the word "balloon" that can be formed using the characters in the input string `text`.*);
	//@ ensures(*2. Each character in the input string `text` can be used at most once to form instances of the word "balloon".*);
	//@ ensures(*3. If no instances of the word "balloon" can be formed using the characters in the input string `text`, the method returns 0.*);
    public int maxNumberOfBalloons(String text) {
        int[] counts = new int[26];
        for (char c : text.toCharArray()) {
            counts[c - 'a']++;
        }
        return Math.min(
                counts[0],
                Math.min(
                        counts[1], Math.min(counts[11] / 2, Math.min(counts[14] / 2, counts[13]))));
    }
}