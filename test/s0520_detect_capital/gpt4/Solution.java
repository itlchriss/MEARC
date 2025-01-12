package g0501_0600.s0520_detect_capital;

// #Easy #String #2022_07_25_Time_2_ms_(65.95%)_Space_42_MB_(74.10%)

public class Solution {
//@ requires(*The length of the string parameter `word` is less than or equal to 100 and is greater than or equal to 1.*);
//@ requires(*The string parameter `word` consists of only lowercase and uppercase English letters.*);
//@ ensures(*If the boolean result is equal to the true literal, all characters in the string parameter `word` are uppercase, or all characters in the string parameter `word` are lowercase, or only the first character in the string parameter `word` is uppercase.*);
//@ ensures(*If the boolean result is equal to the false literal, the string parameter `word` does not meet any of the conditions for correct capital usage.*);
//@ ensures(*If the string parameter `word` is equal to "USA", the boolean result is equal to the true literal.*);
//@ ensures(*If the string parameter `word` is equal to "FlaG", the boolean result is equal to the false literal.*);
    public boolean detectCapitalUse(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        int upper = 0;
        int lower = 0;
        int n = word.length();
        boolean firstUpper = Character.isUpperCase(word.charAt(0));
        for (int i = 0; i < n; i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                upper++;
            } else if (Character.isLowerCase(word.charAt(i))) {
                lower++;
            }
        }
        if (firstUpper && upper > 1) {
            firstUpper = false;
        }
        return upper == n || lower == n || firstUpper;
    }
}