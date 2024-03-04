package g1801_1900.s1839_longest_substring_of_all_vowels_in_order;

// #Medium #String #Sliding_Window #2022_05_07_Time_24_ms_(86.13%)_Space_58.7_MB_(49.58%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `word` is not null.*);
//@ ensures(*The length of `word` is greater than or equal to 1.*);
//@ ensures(*The characters in `word` are limited to the English vowels (`'a'`, `'e'`, `'i'`, `'o'`, `'u'`).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the length of the longest beautiful substring in `word`.*);
//@ ensures(*If no beautiful substring exists, the method returns 0.*);
    public int longestBeautifulSubstring(String word) {
        int cnt = 1;
        int len = 1;
        int maxLen = 0;
        for (int i = 1; i != word.length(); ++i) {
            if (word.charAt(i - 1) == word.charAt(i)) {
                ++len;
            } else if (word.charAt(i - 1) < word.charAt(i)) {
                ++len;
                ++cnt;
            } else {
                cnt = 1;
                len = 1;
            }

            if (cnt == 5) {
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }
}