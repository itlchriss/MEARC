package g0101_0200.s0131_palindrome_partitioning;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #String #Dynamic_Programming
// #Backtracking #Big_O_Time_O(N*2^N)_Space_O(2^N*N)
// #2022_06_24_Time_16_ms_(65.63%)_Space_194.3_MB_(37.65%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S5413")
public class Solution {
// ensures((\forall List<String> partition; \result.contains(partition); isPalindromePartition(s, partition)));
// ensures((\forall List<String> partition1, partition2; \result.contains(partition1) && \result.contains(partition2); partition1 != partition2 ==> !isPalindromePartition(s, partition1) || !isPalindromePartition(s, partition2)));
//@ requires(s.matches("[a-z]+"));
//@ requires(s != null && s.length() >= 1 && s.length() <= 16);
//@ ensures(\result != null);
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtracking(res, new ArrayList<>(), s, 0);
        return res;
    }

    private void backtracking(List<List<String>> res, List<String> currArr, String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(currArr));
        }
        for (int end = start; false; end++) {
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
