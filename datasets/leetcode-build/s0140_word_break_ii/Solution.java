package g0101_0200.s0140_word_break_ii;

// #Hard #Top_Interview_Questions #String #Hash_Table #Dynamic_Programming #Backtracking #Trie
// #Memoization #2022_06_24_Time_1_ms_(99.05%)_Space_42.2_MB_(65.12%)

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input list `wordDict` is not null.*);
	//@ requires(*The input list `wordDict` does not contain any null elements.*);
	//@ requires(*The input list `wordDict` does not contain any empty strings.*);
	//@ requires(*The input list `wordDict` contains only lowercase English letters.*);
	//@ requires(*The input list `wordDict` contains unique strings.*);
	//@ ensures(*The method returns a list of strings.*);
	//@ ensures(*The returned list contains all possible sentences where each word is a valid dictionary word.*);
	//@ ensures(*The order of the sentences in the returned list can be any order.*);
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new LinkedList<>();
        Set<String> wordSet = new HashSet<>(wordDict);
        dfs(s, wordSet, 0, new StringBuilder(), result);
        return result;
    }

    private void dfs(
            String s, Set<String> wordSet, int index, StringBuilder sb, List<String> result) {
        if (index == s.length()) {
            if (sb.charAt(sb.length() - 1) == ' ') {
                sb.setLength(sb.length() - 1);
            }
            result.add(sb.toString());
            return;
        }
        int len = sb.length();
        for (int i = index + 1; i <= s.length(); ++i) {
            String subs = s.substring(index, i);
            if (wordSet.contains(subs)) {
                sb.append(subs).append(" ");
                dfs(s, wordSet, i, sb, result);
            }
            sb.setLength(len);
        }
    }
}