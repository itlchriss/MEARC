package g0401_0500.s0472_concatenated_words;

// #Hard #Array #String #Dynamic_Programming #Depth_First_Search #Trie
// #2022_07_20_Time_84_ms_(74.09%)_Space_82_MB_(34.63%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    private final List<String> ans = new ArrayList<>();
    private Trie root;
	//@ requires(*The input array `words` must not be null.*);
	//@ requires(*The input array `words` must not contain any duplicates.*);
	//@ requires(*The length of each word in the input array `words` must be between 0 and - The sum of the lengths of all words in the input array `words` must be between 0 and*);
	//@ ensures(*The method returns a list of strings.*);
	//@ ensures(*The returned list contains all the concatenated words found in the input array `words`.*);
	//@ ensures(*Each concatenated word in the returned list is comprised entirely of at least two shorter words from the input array `words`.*);

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        root = new Trie();
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        for (String word : words) {
            Trie ptr = root;
            if (search(word, 0, 0)) {
                ans.add(word);
            } else {
                for (int j = 0; j < word.length(); j++) {
                    if (ptr.nxt[word.charAt(j) - 'a'] == null) {
                        ptr.nxt[word.charAt(j) - 'a'] = new Trie();
                    }
                    ptr = ptr.nxt[word.charAt(j) - 'a'];
                }
                ptr.endHere = true;
            }
        }
        return ans;
    }

    private boolean search(String cur, int idx, int wordCnt) {
        if (idx == cur.length()) {
            return wordCnt >= 2;
        }
        Trie ptr = root;
        for (int i = idx; i < cur.length(); i++) {
            if (ptr.nxt[cur.charAt(i) - 'a'] == null) {
                return false;
            }
            if (ptr.nxt[cur.charAt(i) - 'a'].endHere) {
                boolean ret = search(cur, i + 1, wordCnt + 1);
                if (ret) {
                    return true;
                }
            }
            ptr = ptr.nxt[cur.charAt(i) - 'a'];
        }
        return ptr.endHere && wordCnt >= 2;
    }

    private static class Trie {
        Trie[] nxt;
        boolean endHere;

        Trie() {
            nxt = new Trie[26];
            endHere = false;
        }
    }
}