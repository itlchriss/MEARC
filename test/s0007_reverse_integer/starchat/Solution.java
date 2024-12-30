package g0001_0100.s0007_reverse_integer;

// #Medium #Top_Interview_Questions #Math #Udemy_Integers
// #2024_01_04_Time_1_ms_(96.61%)_Space_40.9_MB_(11.62%)

public class Solution {
//@ requires(*The integer parameter `x` is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ requires(**);
//@ requires(*Example #19*);
//@ requires(*Software specification: 2415\. Maximum Number of Non-Overlapping Substrings with the Same Letter*);
//@ requires(*Medium*);
//@ requires(*Given a string `s` composed of lowercase English letters, return the maximum number of non-overlapping substrings that have the same letter.*);
//@ requires(**);
//@ requires(*A substring is a contiguous sequence of characters within a string.*);
//@ requires(**);
//@ requires(***Example 1:***);
//@ requires(**);
//@ requires(***Input:** s =  "aababcaab "*);
//@ requires(***Output:** 2*);
//@ requires(***Explanation:** Substrings with the same letters are  "aa " and  "aaba ".*);
//@ requires(**);
//@ requires(*The substrings with different letters are  "ababca " and  "abcaab ".*);
//@ requires(**);
//@ requires(*Note that some substrings may overlap, but you should count the maximum number of non-overlapping substrings with the same letter.*);
//@ requires(**);
//@ requires(***Example 2:***);
//@ requires(**);
//@ requires(***Input:** s =  "aaaaa "*);
//@ requires(***Output:** 5*);
//@ requires(***Explanation:** Substrings with the same letters are  "a ",  "aa ",  "aaa ",  "aaaa ",  "aaaaa ".*);
//@ requires(**);
//@ requires(***Constraints:***);
//@ requires(**);
//@ requires(**   `1 <= s.length <= 105`*);
//@ requires(**   `s` composed of lowercase English letters.*);
//@ ensures(*The integer result is less than or equal to 2147483647 and is greater than or equal to -2147483648.*);
//@ ensures(*If the integer parameter `x` is equal to 123, the integer result is equal to 321.*);
//@ ensures(*If the integer parameter `x` is equal to -123, the integer result is equal to -321.*);
//@ ensures(*If the integer parameter `x` is equal to 120, the integer result is equal to 21.*);
//@ ensures(*If the integer parameter `x` is equal to 0, the integer result is equal to 0.*);
//@ ensures(*If the integer parameter `x` is equal to 2147483647, the integer result is equal to 0.*);
//@ ensures(*If the integer parameter `x` is equal to -2147483648, the integer result is equal to 0.*);
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