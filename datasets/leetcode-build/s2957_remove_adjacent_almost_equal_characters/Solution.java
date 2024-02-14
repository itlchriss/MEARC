package g2901_3000.s2957_remove_adjacent_almost_equal_characters;

// #Medium #String #Dynamic_Programming #Greedy
// #2024_01_15_Time_1_ms_(100.00%)_Space_42.1_MB_(15.57%)

public class Solution {
	//@ requires(*The input string `word` is not null.*);
	//@ requires(*The length of `word` is greater than or equal to 1 and less than or equal to 100.*);
	//@ requires(*`word` consists only of lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the minimum number of operations needed to remove all adjacent almost-equal characters from `word`.*);
    public int removeAlmostEqualCharacters(String word) {
        int count = 0;
        char[] wordArray = word.toCharArray();
        for (int i = 1; i < wordArray.length; i++) {
            if (Math.abs(wordArray[i] - wordArray[i - 1]) <= 1) {
                count++;
                wordArray[i] =
                        (i + 1 < wordArray.length
                                        && (wordArray[i + 1] != 'a' && wordArray[i + 1] != 'b'))
                                ? 'a'
                                : 'z';
            }
        }
        return count;
    }
}