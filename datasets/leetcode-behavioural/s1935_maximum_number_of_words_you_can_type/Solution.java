package g1901_2000.s1935_maximum_number_of_words_you_can_type;

// #Easy #String #Hash_Table #2022_05_15_Time_2_ms_(95.06%)_Space_42.6_MB_(61.20%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The `text` parameter is a non-empty string.*);
//@ ensures(*The `brokenLetters` parameter is a string containing distinct lowercase English letters.*);
//@ ensures(*The `text` parameter consists of words separated by a single space without any leading or trailing spaces.*);
//@ ensures(*Each word in the `text` parameter only consists of lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of words in the `text` parameter that can be fully typed using the keyboard.*);
//@ ensures(*The returned integer is greater than or equal to 0.*);
    public int canBeTypedWords(String text, String brokenLetters) {
        String[] words = text.split(" ");
        int count = 0;
        for (String word : words) {
            boolean broken = false;
            for (char c : word.toCharArray()) {
                if (brokenLetters.indexOf(c) != -1) {
                    broken = true;
                    break;
                }
            }
            if (!broken) {
                count++;
            }
        }
        return count;
    }
}