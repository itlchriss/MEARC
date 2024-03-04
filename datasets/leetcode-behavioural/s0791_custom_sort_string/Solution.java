package g0701_0800.s0791_custom_sort_string;

// #Medium #String #Hash_Table #Sorting #2022_03_26_Time_1_ms_(78.82%)_Space_42.1_MB_(48.16%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input strings `order` and `s` are not null.*);
//@ ensures(*The length of `order` is between 1 and 26 (inclusive).*);
//@ ensures(*The length of `s` is between 1 and 200 (inclusive).*);
//@ ensures(*`order` and `s` consist of lowercase English letters.*);
//@ ensures(*All the characters in `order` are unique.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string is a permutation of `s`.*);
//@ ensures(*The characters in the returned string are in the same order as they appear in `order`.*);
//@ ensures(*If a character `x` occurs before a character `y` in `order`, then `x` occurs before `y` in the returned string.*);
//@ ensures(*The returned string can have any valid permutation of the characters in `s` that satisfies the above conditions.*);
    public String customSortString(String order, String s) {
        int[] ord = new int[26];
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            ord[c - 'a']++;
        }
        for (char c : order.toCharArray()) {
            while (ord[c - 'a']-- > 0) {
                sb.append(c);
            }
        }
        for (char c : s.toCharArray()) {
            while (ord[c - 'a']-- > 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}