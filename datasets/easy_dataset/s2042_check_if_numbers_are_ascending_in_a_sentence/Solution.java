package g2001_2100.s2042_check_if_numbers_are_ascending_in_a_sentence;

// #Easy #String #2022_05_26_Time_2_ms_(75.46%)_Space_42.7_MB_(29.81%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of `s` is between 3 and 200 characters.*);
//@ ensures(*`s` consists of lowercase English letters, spaces, and digits from 0 to The number of tokens in `s` is between 2 and 100, inclusive.*);
//@ ensures(*The tokens in `s` are separated by a single space.*);
//@ ensures(*There are at least two numbers in `s`.*);
//@ ensures(*Each number in `s` is a positive number less than 100, with no leading zeros.*);
//@ ensures(*`s` contains no leading or trailing spaces.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if all the numbers in `s` are strictly increasing from left to right.*);
//@ ensures(*The method returns `false` if any of the numbers in `s` are not strictly increasing from left to right.*);
    public boolean areNumbersAscending(String s) {
        String[] words = s.split("\\ ");
        int prev = 0;
        for (String word : words) {
            if (Character.isDigit(word.charAt(0))) {
                if (Integer.parseInt(word) <= prev) {
                    return false;
                } else {
                    prev = Integer.parseInt(word);
                }
            }
        }
        return true;
    }
}