package g0601_0700.s0680_valid_palindrome_ii;

// #Easy #String #Greedy #Two_Pointers #2022_03_22_Time_10_ms_(61.31%)_Space_55.3_MB_(15.70%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of the input string `s` is greater than or equal to 1.*);
	//@ requires(*The input string `s` consists of lowercase English letters.*);
	//@ ensures(*The method returns a boolean value indicating whether the input string `s` can be a palindrome after deleting at most one character.*);
	//@ ensures(*If the input string `s` can be a palindrome after deleting at most one character, the method returns `true`.*);
	//@ ensures(*If the input string `s` cannot be a palindrome after deleting at most one character, the method returns `false`.*);
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindrome(s.substring(l + 1, r + 1)) || isPalindrome(s.substring(l, r));
            }
            l++;
            r--;
        }
        return true;
    }

    private boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}