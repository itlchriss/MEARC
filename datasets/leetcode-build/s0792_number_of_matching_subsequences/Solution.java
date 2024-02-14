package g0701_0800.s0792_number_of_matching_subsequences;

// #Medium #String #Hash_Table #Sorting #Trie #2022_03_26_Time_92_ms_(84.26%)_Space_118_MB_(25.03%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The input array `words` is not null.*);
	//@ requires(*The length of `s` is between 1 and 5 * 10^4.*);
	//@ requires(*The length of `words` is between 1 and 5000.*);
	//@ requires(*Each string in `words` has a length between 1 and 50.*);
	//@ requires(*`s` and each string in `words` consist of only lowercase English letters.*);
	//@ ensures(*The method returns an integer representing the number of strings in `words` that are subsequences of `s`.*);
    public int numMatchingSubseq(String s, String[] words) {
        List<Node>[] buckets = new ArrayList[26];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (String word : words) {
            char start = word.charAt(0);
            buckets[start - 'a'].add(new Node(word, 0));
        }
        int result = 0;
        for (char c : s.toCharArray()) {
            List<Node> currBucket = buckets[c - 'a'];
            buckets[c - 'a'] = new ArrayList<>();
            for (Node node : currBucket) {
                node.index++;
                if (node.index == node.word.length()) {
                    result++;
                } else {
                    char start = node.word.charAt(node.index);
                    buckets[start - 'a'].add(node);
                }
            }
        }
        return result;
    }

    private static class Node {
        String word;
        int index;

        public Node(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
}