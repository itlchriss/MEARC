package g2001_2100.s2002_maximum_product_of_the_length_of_two_palindromic_subsequences;

// #Medium #String #Dynamic_Programming #Bit_Manipulation #Backtracking #Bitmask
// #2022_05_22_Time_69_ms_(89.94%)_Space_53.8_MB_(41.42%)

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("java:S135")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is at least 2 and at most 12.*);
//@ ensures(*The input string `s` consists of lowercase English letters only.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum possible product of the lengths of two palindromic subsequences of the input string `s`.*);
//@ ensures(*The two palindromic subsequences are disjoint, meaning they do not both pick a character at the same index.*);
//@ ensures(*The product of the lengths of the two palindromic subsequences is maximized.*);
    public int maxProduct(String s) {
        if (s.length() == 2) {
            return 1;
        }
        List<State> list = new ArrayList<>();
        char[] chars = s.toCharArray();
        Set<State> visited = new HashSet<>();
        for (int i = 0; i < chars.length; ++i) {
            int mask = 1 << i;
            recur(chars, new State(i, i, 0, mask), list, visited);
            recur(chars, new State(i, i + 1, 0, mask), list, visited);
        }
        list.sort((a, b) -> b.cnt - a.cnt);
        int res = 1;
        Set<Integer> explored = new HashSet<>();
        for (int i = 0; i < list.size() - 1; ++i) {
            if (explored.contains(i)) {
                continue;
            }
            State cur = list.get(i);
            if (cur.cnt == 1) {
                break;
            }
            for (int j = i + 1; j < list.size(); ++j) {
                State cand = list.get(j);
                if ((cur.mask & cand.mask) < 1) {
                    if (explored.add(j)) {
                        res = Math.max(res, cur.cnt * cand.cnt);
                    }
                    break;
                }
            }
        }
        return res;
    }

    private void recur(char[] chars, State s, List<State> list, Set<State> visited) {
        if (s.i < 0 || s.j >= chars.length) {
            return;
        }
        if (!visited.add(s)) {
            return;
        }
        if (chars[s.i] == chars[s.j]) {
            int m = s.mask | (1 << s.i) | (1 << s.j);
            int nextCnt = s.cnt + (s.i < s.j ? 2 : 1);
            list.add(new State(s.i, s.j, nextCnt, m));
            recur(chars, new State(s.i - 1, s.j + 1, nextCnt, m), list, visited);
        }
        recur(chars, new State(s.i - 1, s.j, s.cnt, s.mask), list, visited);
        recur(chars, new State(s.i, s.j + 1, s.cnt, s.mask), list, visited);
    }

    private static class State {
        int i;
        int j;
        int cnt;
        int mask;

        public State(int i, int j, int cnt, int mask) {
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.mask = mask;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            }
            State s = (State) o;
            return this.i == s.i && this.j == s.j && this.mask == s.mask;
        }

        @Override
        public int hashCode() {
            return (this.i * 31 + this.j) * 31 + this.mask;
        }
    }
}