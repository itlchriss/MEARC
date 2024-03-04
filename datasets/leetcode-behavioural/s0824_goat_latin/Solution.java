package g0801_0900.s0824_goat_latin;

// #Easy #String #2022_03_23_Time_2_ms_(94.82%)_Space_42.6_MB_(46.17%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input sentence is a non-empty string.*);
//@ ensures(*The input sentence consists of words separated by spaces.*);
//@ ensures(*Each word in the sentence consists of lowercase and uppercase letters only.*);
//@ ensures(*The sentence has no leading or trailing spaces.*);
//@ ensures(*All the words in the sentence are separated by a single space.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a string representing the conversion from the input sentence to Goat Latin.*);
//@ ensures(*The output sentence consists of words separated by spaces.*);
//@ ensures(*Each word in the output sentence follows the rules of Goat Latin:*);
//@ ensures(*  - If a word begins with a vowel, "ma" is appended to the end of the word.*);
//@ ensures(*  - If a word begins with a consonant, the first letter is removed and appended to the end of the word, followed by "ma".*);
//@ ensures(*  - Each word has one additional letter 'a' added to the end per its word index in the sentence, starting with 1.*);
    public String toGoatLatin(String sentence) {
        String[] splits = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        StringBuilder a = new StringBuilder();
        for (String word : splits) {
            if (isVowel(word.charAt(0))) {
                sb.append(word).append("ma");
            } else {
                char firstChar = word.charAt(0);
                sb.append(word.substring(1)).append(firstChar).append("ma");
            }
            a.append("a");
            sb.append(a);
            sb.append(" ");
        }
        return sb.toString().trim();
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E'
                || c == 'I' || c == 'O' || c == 'U';
    }
}