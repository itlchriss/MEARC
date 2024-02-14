package g1601_1700.s1616_split_two_strings_to_make_palindrome;

// #Medium #String #Greedy #Two_Pointers #2022_04_13_Time_4_ms_(89.77%)_Space_43.3_MB_(85.58%)

@SuppressWarnings("java:S2234")
public class Solution {
	//@ requires(*The input strings `a` and `b` are not null.*);
	//@ requires(*The lengths of `a` and `b` are equal.*);
	//@ requires(*The lengths of `a` and `b` are between 1 and 10^5 (inclusive).*);
	//@ requires(*`a` and `b` consist of lowercase English letters.*);
	//@ ensures(*The method returns a boolean value indicating whether it is possible to form a palindrome string.*);
	//@ ensures(*If it is possible to form a palindrome string, the method returns true.*);
	//@ ensures(*If it is not possible to form a palindrome string, the method returns false.*);
    public boolean checkPalindromeFormation(String a, String b) {
        return check(a, b) || check(b, a);
    }

    private boolean check(String a, String b) {
        int i = 0;
        int j = b.length() - 1;
        while (j > i && a.charAt(i) == b.charAt(j)) {
            ++i;
            --j;
        }
        return isPalindrome(a, i, j) || isPalindrome(b, i, j);
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (j > i && s.charAt(i) == s.charAt(j)) {
            ++i;
            --j;
        }
        return i >= j;
    }
}