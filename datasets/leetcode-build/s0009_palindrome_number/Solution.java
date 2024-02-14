package g0001_0100.s0009_palindrome_number;

// #Easy #Math #Udemy_Integers #2024_01_04_Time_5_ms_(77.91%)_Space_44.1_MB_(13.06%)

public class Solution {
	//@ requires(*1. The input integer `x` should be within the range of -2^31 to 2^31 - 1.*);
	//@ ensures(*1. The method should return `true` if the input integer `x` is a palindrome.*);
	//@ ensures(*2. The method should return `false` if the input integer `x` is not a palindrome.*);
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
        return rev == x;
    }
}