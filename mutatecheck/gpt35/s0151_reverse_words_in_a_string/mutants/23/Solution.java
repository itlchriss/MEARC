package g0101_0200.s0151_reverse_words_in_a_string;

// #Medium #String #Two_Pointers #Udemy_Strings
// #2022_06_25_Time_2_ms_(99.94%)_Space_42.4_MB_(88.57%)

public class Solution {
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ');
//@ ensures (\forall int i; 0 <= i && i < \result.length(); \result.charAt(i) == s.charAt(\result.length() - 1 - i));
// requires public String reverseWords(String s)
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ';
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ');
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ');
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ');
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ';
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ');
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(i + 1) != ' ');
// ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' '
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ');
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ');
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ');
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ');
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ' || \result.charAt(i + 1) != ' ' || \result.charAt(j - 1) != ' ';
//@ ensures \result != null;
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || \result.charAt(j - 1) != ' ');
//@ ensures (\forall int i, j; 0 <= i && i < j && j < \result.length(); \result.charAt(i) != ' ' || \result.charAt(j) != ' ' || i + 1 == j);
//@ requires s != null && s.length() >= 1;
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
        if (sb.length() >= 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
