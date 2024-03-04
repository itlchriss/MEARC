package g1901_2000.s1957_delete_characters_to_make_fancy_string;

// #Easy #String #2022_05_18_Time_54_ms_(52.94%)_Space_67.8_MB_(72.35%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is greater than or equal to 1.*);
//@ ensures(*The input string `s` consists only of lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string is not null.*);
//@ ensures(*The output string is a modified version of the input string `s`.*);
//@ ensures(*The output string has the minimum possible number of characters deleted.*);
//@ ensures(*The output string is a fancy string, meaning that no three consecutive characters are equal.*);
//@ ensures(*The output string is unique, meaning that there is only one possible final string after the deletion.*);
    public String makeFancyString(String s) {
        StringBuilder ans = new StringBuilder();
        int c = 1;
        ans.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                c++;
            } else {
                c = 1;
            }
            if (c < 3) {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
}