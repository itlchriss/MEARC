package g0201_0300.s0204_count_primes;

// #Medium #Top_Interview_Questions #Array #Math #Enumeration #Number_Theory
// #2022_06_28_Time_115_ms_(92.82%)_Space_46.8_MB_(85.39%)

public class Solution {
//@ ensures(*The integer parameter `n` must be greater than or equal to 0.*);
//@ ensures(*The integer result is the number of prime numbers that are strictly less than the integer parameter `n`.*);
//@ ensures(*If the integer parameter `n` is 0 or 1, the integer result is 0.*);
//@ ensures(*The integer result is 4 if the integer parameter `n` is 10.*);
    public int countPrimes(int n) {
        boolean[] isprime = new boolean[n];
        int count = 0;
        for (int i = 2; i * i <= n; i++) {
            if (!isprime[i]) {
                for (int j = i * 2; j < n; j += i) {
                    isprime[j] = true;
                }
            }
        }
        for (int i = 2; i < isprime.length; i++) {
            if (!isprime[i]) {
                count++;
            }
        }
        return count;
    }
}