package g0601_0700.s0648_replace_words;

// #Medium #Array #String #Hash_Table #Trie #2022_03_21_Time_14_ms_(87.12%)_Space_58.8_MB_(59.91%)

import java.util.List;

public class Solution {
	//@ requires(*The `dictionary` list is not null.*);
	//@ requires(*The `sentence` string is not null.*);
	//@ requires(*The `dictionary` list contains only lowercase letters.*);
	//@ requires(*The length of each word in the `dictionary` list is between 1 and 100.*);
	//@ requires(*The `sentence` string contains only lowercase letters and spaces.*);
	//@ requires(*The length of each word in the `sentence` string is between 1 and 100.*);
	//@ requires(*Every two consecutive words in the `sentence` string are separated by exactly one space.*);
	//@ requires(*The `sentence` string does not have leading or trailing spaces.*);
	//@ ensures(*The returned `sentence` string is the same length as the input `sentence` string.*);
	//@ ensures(*The returned `sentence` string contains only lowercase letters and spaces.*);
	//@ ensures(*The returned `sentence` string has the same number of words as the input `sentence` string.*);
	//@ ensures(*The returned `sentence` string has the same order of words as the input `sentence` string.*);
	//@ ensures(*The returned `sentence` string has replaced all the successors in the input `sentence` string with the root forming it.*);
	//@ ensures(*If a successor can be replaced by more than one root, the returned `sentence` string replaces it with the root that has the shortest length.*);
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        dictionary.forEach(trie::insert);
        String[] allWords = sentence.split(" ");
        for (int i = 0; i < allWords.length; i++) {
            allWords[i] = trie.getRootForWord(allWords[i]);
        }
        return String.join(" ", allWords);
    }

    static class Node {
        Node[] links = new Node[26];
        boolean wordCompleted;

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public void put(char ch, Node node) {
            links[ch - 'a'] = node;
        }

        public Node get(char ch) {
            return links[ch - 'a'];
        }

        public boolean isWordCompleted() {
            return wordCompleted;
        }

        public void setWordCompleted(boolean flag) {
            wordCompleted = flag;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node node = root;
            for (int i = 0; i < word.length(); i++) {
                if (!node.containsKey(word.charAt(i))) {
                    node.put(word.charAt(i), new Node());
                }
                node = node.get(word.charAt(i));
            }
            node.setWordCompleted(true);
        }

        public String getRootForWord(String word) {
            Node node = root;
            StringBuilder rootWord = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                if (node.containsKey(word.charAt(i))) {
                    rootWord.append(word.charAt(i));
                    node = node.get(word.charAt(i));
                    if (node.isWordCompleted()) {
                        return rootWord.toString();
                    }

                } else {
                    return word;
                }
            }
            return word;
        }
    }
}