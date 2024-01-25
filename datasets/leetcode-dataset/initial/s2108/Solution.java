package g2101_2200.s2108_find_first_palindromic_string_in_the_array;

// #Easy #Array #String #Two_Pointers #2022_05_31_Time_3_ms_(84.92%)_Space_50.1_MB_(69.85%)

public class Solution {
//@ ensures(*Given an array of strings `words`, return _the first **palindromic** string in the array_. If there is no such string, return _an **empty string**_ `""`. **Explanation:** There are no palindromic strings, so the empty string is returned.*);

    public static boolean isPalindrome(String s) {
        int len = s.length();
        for (int i = 0, j = len - 1; i <= len / 2 && j >= len / 2; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word)) {
                return word;
            }
        }
        return "";
    }
}