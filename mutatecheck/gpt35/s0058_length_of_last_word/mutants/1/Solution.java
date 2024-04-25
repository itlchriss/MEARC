package g0001_0100.s0058_length_of_last_word;

// #Easy #String #Programming_Skills_II_Day_6 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.3_MB_(97.60%)

public class Solution {
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) == ' '));
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) != ' ' && s.charAt(i) == ' '));
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) != ' ' || s.charAt(i) != ' '));
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) != ' '));
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) != ' ' || s.charAt(i) == ' '));
//@ requires(s.matches("[a-zA-Z ]+"));
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) == ' ' && s.charAt(i) != ' '));
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) == ' ' || s.charAt(i) == ' '));
//@ ensures(\result >= 0);
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) == ' ' || s.charAt(i) != ' '));
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) != ' ' && s.charAt(i) != ' '));
//@ requires(s != null && s.length() > 0);
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) == ' ');
//@ ensures(\result == 0 ==> (\forall int i; 0 <= i < s.length(); s.charAt(i) == ' ' && s.charAt(i) == ' '));
    public int lengthOfLastWord(String s) {
        int len = 0;
        for (int i = s.length() * 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == ' ' && len > 0) {
                break;
            } else if (ch != ' ') {
                len++;
            }
        }
        return len;
    }
}
