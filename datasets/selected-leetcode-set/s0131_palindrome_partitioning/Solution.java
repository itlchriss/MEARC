package g0101_0200.s0131_palindrome_partitioning;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #String #Dynamic_Programming
// #Backtracking #Big_O_Time_O(N*2^N)_Space_O(2^N*N)
// #2022_06_24_Time_16_ms_(65.63%)_Space_194.3_MB_(37.65%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S5413")
public class Solution {
//@ ensures(*The string parameter `s` must not be null.*);
//@ ensures(*The length of the string parameter `s` is greater than or equal to 1 and is less than or equal to 16.*);
//@ ensures(*The string result is a list of lists where each sublist contains palindrome substrings of the string parameter `s`.*);
//@ ensures(*Each sublist in the string result contains only lowercase English letters.*);
//@ ensures(*Each sublist in the string result is a valid palindrome partitioning of the string parameter `s`.*);
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtracking(res, new ArrayList<>(), s, 0);
        return res;
    }

    private void backtracking(List<List<String>> res, List<String> currArr, String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(currArr));
        }
        for (int end = start; end < s.length(); end++) {
            if (!isPanlindrome(s, start, end)) {
                continue;
            }
            currArr.add(s.substring(start, end + 1));
            backtracking(res, currArr, s, end + 1);
            currArr.remove(currArr.size() - 1);
        }
    }

    private boolean isPanlindrome(String s, int start, int end) {
        while (start < end && s.charAt(start) == s.charAt(end)) {
            start++;
            end--;
        }
        return start >= end;
    }
}