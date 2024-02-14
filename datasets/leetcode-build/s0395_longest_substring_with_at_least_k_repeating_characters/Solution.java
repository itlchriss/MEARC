package g0301_0400.s0395_longest_substring_with_at_least_k_repeating_characters;

// #Medium #Top_Interview_Questions #String #Hash_Table #Sliding_Window #Divide_and_Conquer
// #2022_07_15_Time_0_ms_(100.00%)_Space_42.4_MB_(47.47%)

public class Solution {
	//@ requires(*1. The input string `s` is not null.*);
	//@ requires(*2. The input string `s` is not empty.*);
	//@ requires(*3. The input integer `k` is greater than or equal to 1.*);
	//@ requires(*4. The input integer `k` is less than or equal to the length of the input string `s`.*);
	//@ ensures(*1. The output is an integer representing the length of the longest substring of `s` such that the frequency of each character in this substring is greater than or equal to `k`.*);
	//@ ensures(*2. The output is greater than or equal to 0.*);
	//@ ensures(*3. The output is less than or equal to the length of the input string `s`.*);
    public int longestSubstring(String s, int k) {
        return helper(s, k, 0, s.length());
    }

    private int helper(String s, int k, int start, int end) {
        if (end - start < k) {
            return 0;
        }
        int[] nums = new int[26];
        for (int i = start; i < end; i++) {
            nums[s.charAt(i) - 'a']++;
        }
        for (int i = start; i < end; i++) {
            if (nums[s.charAt(i) - 'a'] < k) {
                int j = i + 1;
                while (j < s.length() && nums[s.charAt(j) - 'a'] < k) {
                    j++;
                }
                return Math.max(helper(s, k, start, i), helper(s, k, j, end));
            }
        }
        return end - start;
    }
}