package g1801_1900.s1880_check_if_word_equals_summation_of_two_words;

// #Easy #String #2022_05_11_Time_2_ms_(31.97%)_Space_42.7_MB_(13.61%)

public class Solution {
	//@ requires(*The input strings `firstWord`, `secondWord`, and `targetWord` must consist of lowercase English letters from `'a'` to `'j'` inclusive.*);
	//@ requires(*The lengths of `firstWord`, `secondWord`, and `targetWord` must be between 1 and 8 (inclusive).*);
	//@ ensures(*The method should return `true` if the summation of the numerical values of `firstWord` and `secondWord` equals the numerical value of `targetWord`.*);
	//@ ensures(*The method should return `false` otherwise.*);
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        StringBuilder sb = new StringBuilder();
        int a = getSum(firstWord, sb);
        sb.setLength(0);
        int b = getSum(secondWord, sb);
        sb.setLength(0);
        int c = getSum(targetWord, sb);
        return a + b == c;
    }

    private int getSum(String firstWord, StringBuilder sb) {
        for (char c : firstWord.toCharArray()) {
            sb.append(c - 'a');
        }
        return Integer.parseInt(sb.toString());
    }
}