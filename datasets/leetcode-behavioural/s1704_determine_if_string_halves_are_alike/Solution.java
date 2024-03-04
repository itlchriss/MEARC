package g1701_1800.s1704_determine_if_string_halves_are_alike;

// #Easy #String #Counting #2022_04_24_Time_1_ms_(100.00%)_Space_41.9_MB_(69.58%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is even.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns `true` if the first half of the string `s` and the second half of the string `s` have the same number of vowels.*);
//@ ensures(*The method returns `false` if the first half of the string `s` and the second half of the string `s` do not have the same number of vowels.*);
    public boolean halvesAreAlike(String s) {
        if (s.length() < 1) {
            return false;
        }
        return countVowel(0, s.length() / 2, s) == countVowel(s.length() / 2, s.length(), s);
    }

    private int countVowel(int start, int end, String s) {
        int c = 0;
        for (int i = start; i < end; i++) {
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A'
                    || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                c++;
            }
        }
        return c;
    }
}