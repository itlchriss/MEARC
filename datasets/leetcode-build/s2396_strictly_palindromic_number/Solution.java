package g2301_2400.s2396_strictly_palindromic_number;

// #Medium #Math #Two_Pointers #Brainteaser #2022_09_14_Time_0_ms_(100.00%)_Space_40.6_MB_(75.27%)

public class Solution {
	//@ requires(*The input integer `n` must be greater than or equal to 4.*);
	//@ requires(*The input integer `n` must be less than or equal to 10^5.*);
	//@ ensures(*The method should return `true` if `n` is strictly palindromic.*);
	//@ ensures(*The method should return `false` if `n` is not strictly palindromic.*);
    public boolean isStrictlyPalindromic(int n) {
        for (int i = 2; i <= n - 2; i++) {
            String num = Integer.toString(i);
            String s = baseConversion(num, 10, i);
            if (!checkPalindrome(s)) {
                return false;
            }
        }
        return true;
    }

    private String baseConversion(String number, int sBase, int dBase) {
        // Parse the number with source radix
        // and return in specified radix(base)
        return Integer.toString(Integer.parseInt(number, sBase), dBase);
    }

    private boolean checkPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}