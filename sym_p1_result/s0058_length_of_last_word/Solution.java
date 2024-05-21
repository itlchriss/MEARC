package g0001_0100.s0058_length_of_last_word;

// #Easy #String #Programming_Skills_II_Day_6 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.3_MB_(97.60%)

public class Solution {
//@ requires(*A word is a maximal substring consisting of non-space characters only.*);
//@ requires(*Example 1:*);
//@ requires(*Input: s = "Hello World"*);
//@ requires(*Output: 5*);
//@ requires(*Explanation: The last word is "World" with length 5.*);
//@ requires(*Example 2:*);
//@ requires(*Input: s = " fly me to the moon "*);
//@ requires(*Output: 4*);
//@ requires(*Explanation: The last word is "moon" with length 4.*);
//@ requires(*Example 3:*);
//@ requires(*Input: s = "luffy is still joyboy"*);
//@ requires(*Output: 6*);
//@ requires(*Explanation: The last word is "joyboy" with length 6.*);
//@ requires(*Constraints:*);
//@ requires(*<code>1 <= s.length <= 10<sup>4</sup></code>*);
//@ requires(*param_s consists of only English letters and spaces `' '`.*);
//@ requires(*There will be at least one word in param_s.*);
//@ ensures(*Given a string param_s consisting of some words separated by some number of spaces, the result is the length of the last word in the string.*);
    public int lengthOfLastWord(String s) {
        int len = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
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