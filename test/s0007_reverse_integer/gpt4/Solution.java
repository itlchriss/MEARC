package g0001_0100.s0007_reverse_integer;

// #Medium #Top_Interview_Questions #Math #Udemy_Integers
// #2024_01_04_Time_1_ms_(96.61%)_Space_40.9_MB_(11.62%)

public class Solution {
//@ requires(*The integer parameter `x` is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ ensures(*The integer result is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ ensures(*If the integer result is equal to 0, reversing the digits of the integer parameter `x` causes the value to go outside the signed 32-bit integer range.*);
//@ ensures(*If the integer parameter `x` is equal to 123, the integer result is equal to 321.*);
//@ ensures(*If the integer parameter `x` is equal to -123, the integer result is equal to -321.*);
//@ ensures(*If the integer parameter `x` is equal to 120, the integer result is equal to 21.*);
//@ ensures(*If the integer parameter `x` is equal to 0, the integer result is equal to 0.*);
    public int reverse(int x) {
        long rev = 0;        
        //@ ghost long k;
        //@ ghost int m;
        //@ set k = rev;                                    
        //@ set m = x;
        while (x != 0) {            
            //@ set k = rev;
            //@ set m = x;
            // assume (Long.MIN_VALUE)/10 + m <= k <= (Long.MAX_VALUE)/10 - m;
            // assume ((Long.MIN_VALUE) + ( m % 10 ))/10 <= k <= ((Long.MAX_VALUE) - ( m % 10 )) / 10;
            rev = (rev * 10) + (x % 10);            
            //@ set k = rev;
            //@ set m = m/10;
            x /= 10;
        }
        if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) rev;
    }
}