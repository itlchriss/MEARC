package g0801_0900.s0839_similar_string_groups;

// #Hard #Array #String #Depth_First_Search #Breadth_First_Search #Union_Find
// #2022_03_24_Time_15_ms_(80.39%)_Space_43.8_MB_(42.95%)

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `strs` is not null.*);
//@ ensures(*The length of `strs` is greater than or equal to 1.*);
//@ ensures(*The length of each string in `strs` is greater than or equal to 1.*);
//@ ensures(*Each string in `strs` consists of lowercase letters only.*);
//@ ensures(*All words in `strs` have the same length and are anagrams of each other.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of groups.*);
//@ ensures(*The number of groups is greater than or equal to 0.*);
    public int numSimilarGroups(String[] strs) {
        boolean[] visited = new boolean[strs.length];
        int res = 0;
        for (int i = 0; i < strs.length; i++) {
            if (!visited[i]) {
                res++;
                bfs(i, visited, strs);
            }
        }
        return res;
    }

    private void bfs(int i, boolean[] visited, String[] strs) {
        Queue<String> qu = new LinkedList<>();
        qu.add(strs[i]);
        visited[i] = true;
        while (!qu.isEmpty()) {
            String s = qu.poll();
            for (int j = 0; j < strs.length; j++) {
                if (visited[j]) {
                    continue;
                }
                if (isSimilar(s, strs[j])) {
                    visited[j] = true;
                    qu.add(strs[j]);
                }
            }
        }
    }

    private boolean isSimilar(String s1, String s2) {
        Character c1 = null;
        Character c2 = null;
        int mismatchCount = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            mismatchCount++;
            if (c1 == null) {
                c1 = s1.charAt(i);
                c2 = s2.charAt(i);
            } else if (s2.charAt(i) != c1 || s1.charAt(i) != c2) {
                return false;
            } else if (mismatchCount > 2) {
                return false;
            }
        }
        return true;
    }
}