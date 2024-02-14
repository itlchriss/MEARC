package g1301_1400.s1328_break_a_palindrome;

// #Medium #String #Greedy #2022_03_19_Time_0_ms_(100.00%)_Space_42.3_MB_(21.67%)

public class Solution {
	//@ requires(*The input string `palindrome` is not null.*);
	//@ requires(*The input string `palindrome` is a palindrome.*);
	//@ requires(*The length of the input string `palindrome` is between 1 and*);
	//@ ensures(*The output string is either the resulting string after replacing one character or an empty string.*);
	//@ ensures(*The output string is not a palindrome.*);
	//@ ensures(*The output string is the lexicographically smallest possible string after replacing one character.*);
    public String breakPalindrome(String palindrome) {
        if (palindrome.length() <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palindrome.length(); i++) {
            char ch = palindrome.charAt(i);
            if (ch != 'a' && i != palindrome.length() - 1 - i) {
                sb.append('a');
                sb.append(palindrome.substring(i + 1));
                return sb.toString();
            } else {
                sb.append(ch);
            }
        }
        sb.deleteCharAt(palindrome.length() - 1);
        sb.append('b');
        return sb.toString();
    }
}