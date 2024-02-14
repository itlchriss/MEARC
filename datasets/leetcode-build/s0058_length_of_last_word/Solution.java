package g0001_0100.s0058_length_of_last_word;

// #Easy #String #Programming_Skills_II_Day_6 #Udemy_Arrays
// #2023_08_11_Time_0_ms_(100.00%)_Space_40.3_MB_(97.60%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` is not empty.*);
	//@ requires(*The input string `s` consists of only English letters and spaces.*);
	//@ ensures(*The method returns an integer representing the length of the last word in the input string `s`.*);
	//@ ensures(*The last word is a maximal substring consisting of non-space characters only.*);
	//@ ensures(*The length of the last word is greater than or equal to The length of the last word is less than or equal to the length of the input string `s`.*);
	//@ ensures(*The method returns the correct length of the last word for different input strings.*);
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