package g0601_0700.s0648_replace_words;

// #Medium #Array #String #Hash_Table #Trie #2022_03_21_Time_14_ms_(87.12%)_Space_58.8_MB_(59.91%)

import java.util.List;

public class Solution {
//@ ensures(*For each word in the input `sentence`, if the word has a **successor** that can be replaced by more than one **root** from the `dictionary`, the word should be replaced with the **root** that has the shortest length.*);
//@ ensures(*The output `sentence` should have all the **successors** replaced with their corresponding **roots** from the `dictionary`.*);
//@ ensures(*The output `sentence` should maintain the same word order as the input `sentence`.*);
//@ ensures(*The output `sentence` should only contain lower-case letters and spaces.*);
//@ ensures(*The output `sentence` should not have leading or trailing spaces.*);
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