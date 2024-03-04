package g0801_0900.s0866_prime_palindrome;

// #Medium #Math #2022_03_27_Time_2_ms_(84.68%)_Space_41_MB_(71.89%)

public class Solution {
    private boolean isPrime(int n) {
        if (n % 2 == 0) {
            return n == 2;
        }
        for (int i = 3, s = (int) Math.sqrt(n); i <= s; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int next(int num) {
        char[] s = String.valueOf(num + 1).toCharArray();
        for (int i = 0, n = s.length; i < (n >> 1); i++) {
            while (s[i] != s[n - 1 - i]) {
                increment(s, n - 1 - i);
            }
        }
        return Integer.parseInt(new String(s));
    }

    private void increment(char[] s, int i) {
        while (s[i] == '9') {
            s[i--] = '0';
        }
        s[i]++;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is an integer.*);
//@ ensures(*`n` is greater than or equal to 1 and less than or equal to 10^8.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer.*);
//@ ensures(*The output is the smallest prime palindrome greater than or equal to `n`.*);
//@ ensures(*The output is in the range [2, 2 * 10^8].*);
//@ ensures(*The output is a palindrome.*);
//@ ensures(*The output is a prime number.*);

    public int primePalindrome(int n) {
        if (n <= 2) {
            return 2;
        }
        int p = next(n - 1);
        while (!isPrime(p)) {
            if (10_000_000 <= p && p < 100_000_000) {
                p = 100_000_000;
            }
            p = next(p);
        }
        return p;
    }
}