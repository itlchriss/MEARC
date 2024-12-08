package g2101_2200.s2114_maximum_number_of_words_found_in_sentences;

// #Easy #Array #String #2022_06_01_Time_4_ms_(69.59%)_Space_42.8_MB_(67.52%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `sentences` is not null.*);
//@ ensures(*The length of the input array `sentences` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `sentences` is not null.*);
//@ ensures(*Each element in the input array `sentences` is a valid sentence (a list of words separated by a single space).*);
//@ ensures(*Each element in the input array `sentences` does not have leading or trailing spaces.*);
//@ ensures(*All the words in each element of the input array `sentences` are separated by a single space.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum number of words found in a single sentence.*);
//@ ensures(*The returned integer is greater than or equal to 1.*);
//@ ensures(*The returned integer is less than or equal to the total number of words in the input array `sentences`.*);
    public int mostWordsFound(String[] sentences) {
        int max = 0;
        for (String sentence : sentences) {
            max = Math.max(max, countWords(sentence));
        }
        return max;
    }

    private int countWords(String s) {
        int start = 0;
        int wc = 0;
        while (start < s.length()) {
            int end = start;
            while (end < s.length() && Character.isLetter(s.charAt(end))) {
                end++;
            }
            wc++;
            start = ++end;
        }
        return wc;
    }
}