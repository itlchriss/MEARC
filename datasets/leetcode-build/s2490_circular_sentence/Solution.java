package g2401_2500.s2490_circular_sentence;

// #Easy #String #2023_01_27_Time_1_ms_(99.85%)_Space_42.6_MB_(55.63%)

public class Solution {
	//@ requires(*The input sentence is a string.*);
	//@ requires(*The sentence consists of only uppercase and lowercase English letters and spaces.*);
	//@ requires(*The words in the sentence are separated by a single space.*);
	//@ requires(*There are no leading or trailing spaces.*);
	//@ ensures(*The method returns a boolean value indicating whether the sentence is circular or not.*);
    public boolean isCircularSentence(String sentence) {
        char[] letters = sentence.toCharArray();
        int len = letters.length;
        for (int i = 0; i < len - 1; ++i) {
            if (letters[i] == ' ' && letters[i - 1] != letters[i + 1]) {
                return false;
            }
        }
        return letters[0] == letters[len - 1];
    }
}