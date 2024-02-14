package g0501_0600.s0564_find_the_closest_palindrome;

// #Hard #String #Math #2022_08_10_Time_2_ms_(100.00%)_Space_42.7_MB_(26.15%)

@SuppressWarnings("java:S2184")
public class Solution {
	//@ requires(*The input string `n` must be a valid integer representation.*);
	//@ requires(*The length of `n` must be between 1 and 18 (inclusive).*);
	//@ requires(*`n` must consist of only digits.*);
	//@ requires(*`n` must not have leading zeros.*);
	//@ requires(*`n` must be within the range of [1, 10^18 - 1].*);
	//@ ensures(*The returned string must be a valid integer representation.*);
	//@ ensures(*The returned string must be a palindrome.*);
	//@ ensures(*The returned string must be the closest palindrome to the input string `n`.*);
	//@ ensures(*If there is a tie for the closest palindrome, the returned string must be the smaller one.*);
    public String nearestPalindromic(String n) {
        if (n.length() == 1) {
            return String.valueOf(Integer.parseInt(n) - 1);
        }
        long num = Long.parseLong(n);
        int offset = (int) Math.pow(10, n.length() / 2);
        long first =
                isPalindrome(n)
                        ? palindromeGenerator(num + offset, n.length())
                        : palindromeGenerator(num, n.length());
        long second =
                first < num
                        ? palindromeGenerator(num + offset, n.length())
                        : palindromeGenerator(num - offset, n.length());

        if (first + second == 2 * num) {
            return first < second ? String.valueOf(first) : String.valueOf(second);
        }
        return Math.abs(num - first) > Math.abs(num - second)
                ? String.valueOf(second)
                : String.valueOf(first);
    }

    private long palindromeGenerator(long num, int length) {
        if (num < 10) {
            return 9;
        }
        int numOfDigits = String.valueOf(num).length();
        if (numOfDigits > length) {
            return ((long) Math.pow(10, numOfDigits - 1) + 1);
        } else if (numOfDigits < length) {
            return ((long) Math.pow(10, numOfDigits) - 1);
        }
        num = num - num % (long) Math.pow(10, numOfDigits / 2);
        long temp = num;
        for (int j = 0; j < numOfDigits / 2; j++) {
            long digit = (long) Math.pow(10, numOfDigits - j - 1);
            num += (int) ((temp / digit) * Math.pow(10, j));
            temp = temp % digit;
        }
        return num;
    }

    private boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}