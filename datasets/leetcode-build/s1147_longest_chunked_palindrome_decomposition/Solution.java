package g1101_1200.s1147_longest_chunked_palindrome_decomposition;

// #Hard #String #Dynamic_Programming #Greedy #Two_Pointers #Hash_Function #Rolling_Hash
// #2023_06_01_Time_2_ms_(60.00%)_Space_43.4_MB_(20.00%)

public class Solution {
	//@ requires(*The input string `text` is not null.*);
	//@ requires(*The input string `text` is not empty.*);
	//@ requires(*The input string `text` consists only of lowercase English characters.*);
	//@ ensures(*The output is an integer representing the largest possible value of `k`.*);
	//@ ensures(*The output is greater than or equal to 1.*);
	//@ ensures(*The concatenation of all the substrings is equal to the input string `text`.*);
	//@ ensures(*Each substring `subtext_i` is a non-empty string.*);
	//@ ensures(*For each valid value of `i` (1 <= i <= k), `subtext_i` is equal to `subtext_k-i+1`.*);
    public int longestDecomposition(String text) {
        int n = text.length();
        int l = 0;
        int r = n - 1;
        int len = 1;
        int ans = 0;
        String lft;
        String rit;
        boolean perfectSubstring = false;
        while (l + len <= r - len + 1) {
            lft = text.substring(l, l + len);
            rit = text.substring(r - len + 1, r + 1);
            if (lft.equals(rit)) {
                ans += 2;
                if (l + len == r - len + 1) {
                    perfectSubstring = true;
                    break;
                }
                l = l + len;
                r = r - len;
                len = 1;
            } else {
                len++;
            }
        }
        if (!perfectSubstring) {
            ans++;
        }
        return ans;
    }
}