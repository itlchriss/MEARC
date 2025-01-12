package g0101_0200.s0151_reverse_words_in_a_string;

// #Medium #String #Two_Pointers #Udemy_Strings
// #2022_06_25_Time_2_ms_(99.94%)_Space_42.4_MB_(88.57%)

public class Solution {
//@ requires(*The length of the string parameter `s` is less than or equal to 10000 and is greater than or equal to 1.*);
//@ requires(*The string parameter `s` contains English letters, digits, and spaces.*);
//@ requires(*The string parameter `s` contains at least one word.*);
//@ ensures(*The string result contains words in reverse order compared to the string parameter `s`.*);
//@ ensures(*The string result contains a single space between words and does not contain leading or trailing spaces.*);
//@ ensures(*If the string parameter `s` is equal to "the sky is blue", the string result is equal to "blue is sky the".*);
//@ ensures(*If the string parameter `s` is equal to " hello world ", the string result is equal to "world hello".*);
//@ ensures(*If the string parameter `s` is equal to "a good example", the string result is equal to "example good a".*);
//@ ensures(*If the string parameter `s` is equal to " Bob Loves Alice ", the string result is equal to "Alice Loves Bob".*);
//@ ensures(*If the string parameter `s` is equal to "Alice does not even like bob", the string result is equal to "bob like even not does Alice".*);
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                i--;
                continue;
            }
            int start = s.lastIndexOf(' ', i);
            sb.append(' ');
            sb.append(s, start + 1, i + 1);
            i = start - 1;
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}