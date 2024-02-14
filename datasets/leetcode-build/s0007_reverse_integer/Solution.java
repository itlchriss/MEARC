package g0001_0100.s0007_reverse_integer;

// #Medium #Top_Interview_Questions #Math #Udemy_Integers
// #2024_01_04_Time_1_ms_(96.61%)_Space_40.9_MB_(11.62%)

public class Solution {
	//@ requires(*1. The input `x` is a signed 32-bit integer.*);
	//@ requires(*2. The environment does not allow storing 64-bit integers.*);
	//@ ensures(*1. The method returns an integer value.*);
	//@ ensures(*2. The returned value is the reverse of the input `x` with its digits reversed.*);
	//@ ensures(*3. If the reversed value of `x` is outside the range of a signed 32-bit integer, the method returns 0.*);
	//@ ensures(*4. If the reversed value of `x` is within the range of a signed 32-bit integer, the method returns the reversed value.*);
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