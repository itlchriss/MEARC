package g1901_2000.s1974_minimum_time_to_type_word_using_special_typewriter;

// #Easy #String #Greedy #2022_05_21_Time_1_ms_(74.90%)_Space_42.5_MB_(14.56%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input word is a non-empty string.*);
//@ ensures(*The input word consists of lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of seconds to type out the characters in the word.*);
    public int minTimeToType(String word) {
        int min = 0;
        char curr = 'a';
        for (int i = 0; i < word.length(); i++) {
            int diff = curr - word.charAt(i);
            curr = word.charAt(i);
            min += Math.min(diff + 26, Math.min(Math.abs(diff), 26 - diff));
            min++;
        }
        return min;
    }
}