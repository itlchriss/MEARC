package g2001_2100.s2047_number_of_valid_words_in_a_sentence;

// #Easy #String #2022_05_26_Time_19_ms_(42.57%)_Space_44.9_MB_(37.62%)

@SuppressWarnings("java:S135")
public class Solution {
	//@ requires(*The input sentence is a non-empty string.*);
	//@ requires(*The sentence only contains lowercase letters, digits, hyphens, punctuation marks, and spaces.*);
	//@ requires(*There is at least one token in the sentence.*);
	//@ ensures(*The method returns an integer representing the number of valid words in the sentence.*);
	//@ ensures(*A valid word is a token that meets all three conditions:*);
	//@ ensures(*1. It only contains lowercase letters, hyphens, and/or punctuation marks (no digits).*);
	//@ ensures(*2. There is at most one hyphen, and if present, it must be surrounded by lowercase characters.*);
	//@ ensures(*3. There is at most one punctuation mark, and if present, it must be at the end of the token.*);
    public int countValidWords(String sentence) {
        String[] tokens = sentence.split("\\s+");
        int count = 0;
        for (String token : tokens) {
            int hyphenCount = 0;
            int punctuationMarkCount = 0;
            boolean valid = true;
            if (token.isEmpty() || token.equals("")) {
                continue;
            }
            for (int i = 0; i < token.length(); i++) {
                if (token.charAt(i) == '-') {
                    hyphenCount++;
                    if (hyphenCount > 1
                            || i == 0
                            || i == token.length() - 1
                            || !Character.isAlphabetic(token.charAt(i - 1))
                            || !Character.isAlphabetic(token.charAt(i + 1))) {
                        valid = false;
                        break;
                    }
                } else if (token.charAt(i) == '!'
                        || token.charAt(i) == '.'
                        || token.charAt(i) == ',') {
                    punctuationMarkCount++;
                    if (punctuationMarkCount > 1 || i != token.length() - 1) {
                        valid = false;
                        break;
                    }
                } else if (Character.isDigit(token.charAt(i))) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                count++;
            }
        }
        return count;
    }
}