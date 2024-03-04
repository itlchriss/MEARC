package g1601_1700.s1624_largest_substring_between_two_equal_characters;

// #Easy #String #Hash_Table #2022_04_18_Time_1_ms_(85.13%)_Space_40.5_MB_(82.98%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is between 1 and 300.*);
//@ ensures(*The input string `s` contains only lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the length of the longest substring between two equal characters, excluding the two characters.*);
//@ ensures(*If there is no such substring, the method returns -1.*);
    public int maxLengthBetweenEqualCharacters(String s) {
        int maxLen = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int lastIndex = s.lastIndexOf(c);
            if (lastIndex != i) {
                maxLen = Math.max(maxLen, Math.abs(lastIndex - i - 1));
            }
        }
        return maxLen;
    }
}