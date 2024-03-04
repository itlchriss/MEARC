package g2701_2800.s2781_length_of_the_longest_valid_substring;

// #Hard #Array #String #Hash_Table #Sliding_Window
// #2023_09_21_Time_137_ms_(75.23%)_Space_60.4_MB_(65.51%)

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `word` is not null.*);
//@ ensures(*The input list `forbidden` is not null.*);
//@ ensures(*The input list `forbidden` does not contain null elements.*);
//@ ensures(*The input list `forbidden` does not contain empty strings.*);
//@ ensures(*The input list `forbidden` does not contain strings longer than 10 characters.*);
//@ ensures(*The input list `forbidden` contains only lowercase English letters.*);
//@ ensures(*The input string `word` consists only of lowercase English letters.*);
//@ ensures(*The length of the input string `word` is between 1 and 10^5.*);
//@ ensures(*The length of the input list `forbidden` is between 1 and 10^5.*);
//@ ensures(*Each string in the input list `forbidden` has a length between 1 and 10.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the length of the longest valid substring of the input string `word`.*);
//@ ensures(*The output is greater than or equal to 0.*);
//@ ensures(*The output is less than or equal to the length of the input string `word`.*);
//@ ensures(*The output is the maximum length such that none of its substrings are present in the input list `forbidden`.*);
    public int longestValidSubstring(String word, List<String> forbidden) {
        Set<String> set = new HashSet<>();
        for (String s : forbidden) {
            set.add(s);
        }
        int n = word.length();
        int ans = 0;
        int i = 0;
        int j = 0;
        while (j < n) {
            int k = j;
            while (k > j - 10 && k >= i) {
                if (set.contains(word.substring(k, j + 1))) {
                    i = k + 1;
                    break;
                }
                k--;
            }
            ans = Math.max(j - i + 1, ans);
            j++;
        }
        return ans;
    }
}