package g1001_1100.s1061_lexicographically_smallest_equivalent_string;

// #Medium #String #Union_Find #2023_06_09_Time_2_ms_(93.75%)_Space_41.7_MB_(41.30%)

public class Solution {
    private int[] parent;
	//@ requires(*The lengths of `s1`, `s2`, and `baseStr` are greater than or equal to 1 and less than or equal to The lengths of `s1` and `s2` are equal.*);
	//@ requires(*`s1`, `s2`, and `baseStr` consist of lowercase English letters.*);
	//@ ensures(*The returned string is the lexicographically smallest equivalent string of `baseStr`.*);
	//@ ensures(*The returned string has the same length as `baseStr`.*);
	//@ ensures(*The characters in the returned string are equivalent according to the equivalency information from `s1` and `s2`.*);
	//@ ensures(*The characters in the returned string are sorted in lexicographical order within each equivalence group.*);

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        parent = new int[26];
        int n = s1.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }
        char base = 'a';
        for (char element : baseStr.toCharArray()) {
            result.append(Character.toString(base + find(element - 'a')));
        }
        return result.toString();
    }

    private void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA != parentB) {
            if (parentA < parentB) {
                parent[parentB] = parentA;
            } else {
                parent[parentA] = parentB;
            }
        }
    }

    private int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }
}