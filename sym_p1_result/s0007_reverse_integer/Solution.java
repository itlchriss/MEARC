package g0001_0100.s0007_reverse_integer;

// #Medium #Top_Interview_Questions #Math #Udemy_Integers
// #2024_01_04_Time_1_ms_(96.61%)_Space_40.9_MB_(11.62%)

public class Solution {
//@ requires(*Assume the environment does not allow you to store 64-bit integers (signed or unsigned).*);
//@ requires(*Example 1:*);
//@ requires(*Input: x = 123*);
//@ requires(*Output: 321*);
//@ requires(*Example 2:*);
//@ requires(*Input: x = -123*);
//@ requires(*Output: -321*);
//@ requires(*Example 3:*);
//@ requires(*Input: x = 120*);
//@ requires(*Output: 21*);
//@ requires(*Example 4:*);
//@ requires(*Input: x = 0*);
//@ requires(*Output: 0*);
//@ requires(*Constraints:*);
//@ requires(*<code>-2<sup>31</sup> <= x <= 2<sup>31</sup> - 1</code>*);
//@ ensures(*Given a signed 32-bit integer param_x, the result is param_x with its digits reversed.*);
//@ ensures(*If reversing param_x causes the value to go outside the signed 32-bit integer range <code>[-2<sup>31</sup>, 2<sup>31</sup> - 1]</code>, then the result is `0`.*);
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