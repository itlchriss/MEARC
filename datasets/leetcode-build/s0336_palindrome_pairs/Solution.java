package g0301_0400.s0336_palindrome_pairs;

// #Hard #Array #String #Hash_Table #Trie #2022_07_10_Time_1165_ms_(57.51%)_Space_270.4_MB_(47.12%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private static class TrieNode {
        TrieNode[] next;
        int index;
        List<Integer> list;

        TrieNode() {
            next = new TrieNode[26];
            index = -1;
            list = new ArrayList<>();
        }
    }
	//@ requires(*The input list `words` is not null.*);
	//@ requires(*The input list `words` contains unique words.*);
	//@ requires(*Each word in the input list `words` consists of lowercase English letters.*);
	//@ requires(*The length of each word in the input list `words` is between 0 and 300.*);
	//@ ensures(*The output is a list of pairs of distinct indices `(i, j)` from the input list `words`.*);
	//@ ensures(*The concatenation of `words[i]` and `words[j]` is a palindrome for each pair `(i, j)` in the output list.*);
	//@ ensures(*The output list contains all possible pairs of distinct indices `(i, j)` such that the concatenation of `words[i]` and `words[j]` is a palindrome.*);

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            addWord(root, words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            search(words, i, root, res);
        }
        return res;
    }

    private void addWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i--) {
            int j = word.charAt(i) - 'a';
            if (root.next[j] == null) {
                root.next[j] = new TrieNode();
            }
            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }
            root = root.next[j];
        }
        root.list.add(index);
        root.index = index;
    }

    private void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
        for (int j = 0; j < words[i].length(); j++) {
            if (root.index >= 0
                    && root.index != i
                    && isPalindrome(words[i], j, words[i].length() - 1)) {
                res.add(Arrays.asList(i, root.index));
            }
            root = root.next[words[i].charAt(j) - 'a'];
            if (root == null) {
                return;
            }
        }
        for (int j : root.list) {
            if (i == j) {
                continue;
            }
            res.add(Arrays.asList(i, j));
        }
    }

    private boolean isPalindrome(String word, int i, int j) {
        while (i < j) {
            if (word.charAt(i++) != word.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}