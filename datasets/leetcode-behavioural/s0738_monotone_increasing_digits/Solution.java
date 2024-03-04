package g0701_0800.s0738_monotone_increasing_digits;

// #Medium #Math #Greedy #2022_03_25_Time_0_ms_(100.00%)_Space_39.5_MB_(82.97%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input integer `n` is non-negative.*);
//@ ensures(*The input integer `n` is less than or equal to 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output integer is less than or equal to the input integer `n`.*);
//@ ensures(*The output integer has monotone increasing digits, meaning that each pair of adjacent digits satisfy `x <= y`.*);
    public int monotoneIncreasingDigits(int n) {
        for (int i = 10; n / i > 0; i *= 10) {
            int digit = (n / i) % 10;
            int endnum = n % i;
            int firstendnum = endnum * 10 / i;
            if (digit > firstendnum) {
                n -= endnum + 1;
            }
        }
        return n;
    }
}