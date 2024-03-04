package g1601_1700.s1639_number_of_ways_to_form_a_target_string_given_a_dictionary;

// #Hard #Array #String #Dynamic_Programming #2022_04_20_Time_50_ms_(95.33%)_Space_75.1_MB_(48.00%)

public class Solution {
    private static final int MOD = (int) 1e9 + 7;
//@ ensures(*Preconditions:*);
//@ ensures(*The input `words` is a non-empty array of strings.*);
//@ ensures(*The input `target` is a non-empty string.*);
//@ ensures(*The length of each string in `words` is the same as the length of `target`.*);
//@ ensures(*All strings in `words` and `target` contain only lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of ways to form `target` from `words`.*);
//@ ensures(*The returned value is modulo 10^9 + 7.*);

    public int numWays(String[] words, String target) {
        int[][] counts = precompute(words);
        Integer[][] memo = new Integer[target.length()][words[0].length()];
        return solve(memo, counts, words, target, 0, 0);
    }

    private int[][] precompute(String[] words) {
        int[][] counts = new int[words[0].length()][26];
        for (String word : words) {
            for (int idx = 0; idx < word.length(); idx++) {
                counts[idx][word.charAt(idx) - 'a']++;
            }
        }
        return counts;
    }

    private int solve(
            Integer[][] memo, int[][] counts, String[] words, String target, int idx, int len) {
        if (idx >= target.length()) {
            return 1;
        }
        if (len >= words[0].length() || words[0].length() - len < target.length() - idx) {
            return 0;
        }
        if (memo[idx][len] != null) {
            return memo[idx][len];
        }
        int answer = 0;
        answer += solve(memo, counts, words, target, idx, len + 1);
        answer %= MOD;
        answer +=
                (int)
                        ((long) solve(memo, counts, words, target, idx + 1, len + 1)
                                * counts[len][target.charAt(idx) - 'a']
                                % MOD);
        answer %= MOD;
        memo[idx][len] = answer;
        return memo[idx][len];
    }
}