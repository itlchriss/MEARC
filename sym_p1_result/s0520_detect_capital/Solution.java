package g0501_0600.s0520_detect_capital;

// #Easy #String #2022_07_25_Time_2_ms_(65.95%)_Space_42_MB_(74.10%)

public class Solution {
//@ requires(*We define the usage of capitals in a word to be right when one of the following cases holds:*);
//@ requires(*All letters in this word are capitals, like `"USA"`.*);
//@ requires(*All letters in this word are not capitals, like `"leetcode"`.*);
//@ requires(*Only the first letter in this word is capital, like `"Google"`.*);
//@ requires(*Example 1:*);
//@ requires(*Input: word = "USA"*);
//@ requires(*Output: true*);
//@ requires(*Example 2:*);
//@ requires(*Input: word = "FlaG"*);
//@ requires(*Output: false*);
//@ requires(*Constraints:*);
//@ requires(*`1 <= word.length <= 100`*);
//@ requires(*param_word consists of lowercase and uppercase English letters.*);
//@ ensures(*Given a string param_word, the result is `true` if the usage of capitals in it is right.*);
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