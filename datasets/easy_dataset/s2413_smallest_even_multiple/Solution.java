package g2401_2500.s2413_smallest_even_multiple;

// #Easy #Math #Number_Theory #2022_11_15_Time_0_ms_(100.00%)_Space_40.9_MB_(48.99%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer.*);
//@ ensures(*The input `n` is between 1 and 150 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a positive integer.*);
//@ ensures(*The output is a multiple of both 2 and `n`.*);
//@ ensures(*The output is the smallest positive integer that satisfies the above conditions.*);
    public int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : n * 2;
    }
}