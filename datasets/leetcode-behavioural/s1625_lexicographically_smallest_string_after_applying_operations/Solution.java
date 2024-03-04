package g1601_1700.s1625_lexicographically_smallest_string_after_applying_operations;

// #Medium #String #Breadth_First_Search #2022_04_18_Time_91_ms_(91.80%)_Space_51.6_MB_(72.13%)

import java.util.HashSet;

public class Solution {
    private String ans = "z";

    private void dfs(String s, int a, int b, HashSet<String> set) {
        if (set.contains(s)) {
            return;
        }
        set.add(s);
        String one = add(s, a);
        String two = rotate(s, b);

        dfs(one, a, b, set);
        dfs(two, a, b, set);
    }

    private String add(String s, int a) {
        char[] temp = s.toCharArray();
        for (int i = 1; i < temp.length; i = i + 2) {
            int val = temp[i] - '0';
            val = (val + a) % 10;
            temp[i] = (char) (val + '0');
        }
        s = new String(temp);
        if (ans.compareTo(s) > 0) {
            ans = s;
        }
        return s;
    }

    private String rotate(String s, int b) {
        if (b < 0) {
            b = b + s.length();
        }
        b = b % s.length();
        b = s.length() - b;
        s = s.substring(b) + s.substring(0, b);
        if (ans.compareTo(s) > 0) {
            ans = s;
        }
        return s;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The length of the string `s` is even.*);
//@ ensures(*The string `s` consists of digits from 0 to 9 only.*);
//@ ensures(*The value of `a` is between 1 and 9 (inclusive).*);
//@ ensures(*The value of `b` is between 1 and `s.length - 1` (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string is lexicographically smallest among all possible strings that can be obtained by applying the operations on `s`.*);

    public String findLexSmallestString(String s, int a, int b) {
        HashSet<String> set = new HashSet<>();
        dfs(s, a, b, set);
        return ans;
    }
}