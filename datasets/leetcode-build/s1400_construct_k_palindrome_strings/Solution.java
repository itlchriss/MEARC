package g1301_1400.s1400_construct_k_palindrome_strings;

// #Medium #String #Hash_Table #Greedy #Counting
// #2022_03_12_Time_5_ms_(94.62%)_Space_53.8_MB_(27.48%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input string `s` consists of lowercase English letters.*);
	//@ requires(*The length of the input string `s` is between 1 and 10^5 (inclusive).*);
	//@ requires(*The input integer `k` is between 1 and 10^5 (inclusive).*);
	//@ ensures(*The method returns `true` if it is possible to construct `k` palindrome strings using all the characters in `s`.*);
	//@ ensures(*The method returns `false` if it is not possible to construct `k` palindrome strings using all the characters in `s`.*);
    public boolean canConstruct(String s, int k) {
        if (s.length() == k) {
            // if size is same as k we can separate out all letters
            return true;
        }
        if (s.length() < k) {
            // if size is less than it is not possible
            return false;
        }
        // count occurrence of each letter
        int[] count = new int[26];
        for (char curr : s.toCharArray()) {
            count[curr - 'a']++;
        }
        // reduce k whenever count is odd
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                k--;
            }
        }
        // we can have max k odd characters
        return k >= 0;
    }
}