package g0001_0100.s0009_palindrome_number;

// #Easy #Math #Udemy_Integers #2024_01_04_Time_5_ms_(77.91%)_Space_44.1_MB_(13.06%)

public class Solution {
//@ requires(*The integer parameter `x` is greater than or equal to -2^31 and is less than or equal to 2^31 - 1.*);
//@ ensures(*If the integer parameter `x` is a palindrome integer, the boolean result is equal to the true literal.*);
//@ ensures(*If the integer parameter `x` is not a palindrome integer, the boolean result is equal to the false literal.*);
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int rev = 0;
        int localX = x;
        //@ ghost int k = rev;
        //@ maintaining localX >= 0;
        while (localX > 0) {
            //@ set k = rev;
            // assume 0 <= k <= Integer.MAX_VALUE / 10;
            rev *= 10;
            //@ set k = rev;
            // assume 0 <= k + localX % 10 <= Integer.MAX_VALUE;
            rev += localX % 10;
            localX /= 10;
        }
        return rev == x;
    }
}