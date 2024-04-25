package g0001_0100.s0009_palindrome_number;

// #Easy #Math #Udemy_Integers #2024_01_04_Time_5_ms_(77.91%)_Space_44.1_MB_(13.06%)

public class Solution {
//@ ensures((x >= -2147483648) && (x <= 2147483647));
//@ ensures((!(\forall int i; 0 <= i && i < String.valueOf((x)).length() / 2; String.valueOf((x)).charAt(i) == String.valueOf((x)).charAt(String.valueOf((x)).length() - 1 - i))) ==> (\result == false));
//@ ensures((\forall int i; 0 <= i && i < String.valueOf((x)).length() / 2; String.valueOf((x)).charAt(i) == String.valueOf((x)).charAt(String.valueOf((x)).length() - 1 - i)) ==> (\result == true));
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int rev = 0;
        int localX = x;
        while (localX > 0) {
            rev *= 10;
            rev += localX % 10;
            localX /= 10;
        }
        return rev <= x;
    }
}
