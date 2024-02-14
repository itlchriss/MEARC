package g0201_0300.s0204_count_primes;

// #Medium #Top_Interview_Questions #Array #Math #Enumeration #Number_Theory
// #2022_06_28_Time_115_ms_(92.82%)_Space_46.8_MB_(85.39%)

public class Solution {
	//@ requires(*The input `n` must be a non-negative integer.*);
	//@ requires(*The input `n` must be less than or equal to 5 * 10^6.*);
	//@ ensures(*The method should return an integer representing the number of prime numbers that are strictly less than `n`.*);
	//@ ensures(*If `n` is less than or equal to 1, the method should return 0.*);
	//@ ensures(*If `n` is greater than 1, the method should return the correct count of prime numbers less than `n`.*);
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