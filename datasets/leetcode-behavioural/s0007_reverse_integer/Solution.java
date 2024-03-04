package g0001_0100.s0007_reverse_integer;

// #Medium #Top_Interview_Questions #Math #Udemy_Integers
// #2024_01_04_Time_1_ms_(96.61%)_Space_40.9_MB_(11.62%)

public class Solution {
//@ ensures(*If the integer parameter `x` is positive, the integer result is `x` with its digits reversed.*);
//@ ensures(*If the integer parameter `x` is negative, the integer result is `-x` with its digits reversed.*);
//@ ensures(*If the reversed integer result is outside the range of [-2^31, 2^31 - 1], the integer result is 0.*);
    public int reverse(int x) {
        long rev = 0;
        while (x != 0) {
            rev = (rev * 10) + (x % 10);
            x /= 10;
        }
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) rev;
    }
}