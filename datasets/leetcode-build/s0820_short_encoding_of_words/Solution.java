package g0801_0900.s0820_short_encoding_of_words;

// #Medium #Array #String #Hash_Table #Trie #2022_03_23_Time_35_ms_(49.44%)_Space_57.5_MB_(50.55%)

import java.util.Arrays;

public class Solution {
    private static class Node {
        Node[] nodes = new Node[26];
    }

    private boolean insert(Node node, String word) {
        Node current = node;
        int n = word.length();
        boolean flag = false;
        for (int i = n - 1; i >= 0; i--) {
            if (current.nodes[word.charAt(i) - 'a'] == null) {
                current.nodes[word.charAt(i) - 'a'] = new Node();
                flag = true;
            }
            current = current.nodes[word.charAt(i) - 'a'];
        }
        return flag;
    }
	//@ requires(*The input array `words` is not null.*);
	//@ requires(*The length of the input array `words` is greater than or equal to 1.*);
	//@ requires(*Each element in the input array `words` is not null.*);
	//@ requires(*Each element in the input array `words` is a string consisting of only lowercase letters.*);
	//@ ensures(*The output is an integer representing the length of the shortest reference string `s`.*);
	//@ ensures(*The reference string `s` ends with the character `'#'`.*);
	//@ ensures(*The length of the reference string `s` is equal to the sum of the lengths of all words in the input array `words`, plus the number of elements in the input array `words`.*);
	//@ ensures(*For each index `indices[i]`, the substring of `s` starting from `indices[i]` and up to (but not including) the next `'#'` character is equal to `words[i]`.*);

    public int minimumLengthEncoding(String[] words) {
        int out = 0;
        Arrays.sort(words, (a, b) -> b.length() - a.length());
        Node node = new Node();
        for (String word : words) {
            if (insert(node, word)) {
                out = out + word.length() + 1;
            }
        }
        return out;
    }
}