package g1201_1300.s1268_search_suggestions_system;

// #Medium #Array #String #2022_03_14_Time_28_ms_(78.06%)_Space_73.1_MB_(38.32%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The `products` array is not null.*);
//@ ensures(*The `searchWord` string is not null.*);
//@ ensures(*The `products` array has at least one element.*);
//@ ensures(*Each element in the `products` array is a non-empty string consisting of lowercase English letters.*);
//@ ensures(*The length of each element in the `products` array is between 1 and 3000.*);
//@ ensures(*The sum of the lengths of all elements in the `products` array is between 1 and 20000.*);
//@ ensures(*The `searchWord` string consists of lowercase English letters.*);
//@ ensures(*The length of the `searchWord` string is between 1 and 1000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a list of lists of suggested products.*);
//@ ensures(*The outer list has the same length as the `searchWord` string.*);
//@ ensures(*Each inner list contains at most three product names.*);
//@ ensures(*The product names in each inner list have a common prefix with the corresponding prefix of the `searchWord` string.*);
//@ ensures(*If there are more than three products with a common prefix, the three lexicographically minimum products are returned.*);
//@ ensures(*The order of the suggested products is the same as the order of the characters in the `searchWord` string.*);
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Trie trie = new Trie();
        Arrays.sort(products);
        for (String p : products) {
            trie.insert(p);
        }

        return trie.getResult(searchWord);
    }

    static class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        void insert(String word) {
            Node curr = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!curr.containsKey(c)) {
                    curr.put(c, new Node());
                }
                curr = curr.get(c);
                curr.addToList(word);
            }
        }

        List<List<String>> getResult(String searchWord) {
            Node curr = root;
            List<List<String>> res = new ArrayList<>();
            for (int i = 0; i < searchWord.length(); i++) {
                char c = searchWord.charAt(i);
                List<String> temp = new ArrayList<>();
                if (curr != null) {
                    curr = curr.get(c);
                }

                for (int j = 0; j < 3 && curr != null && j < curr.getList().size(); j++) {
                    temp.add(curr.getList().get(j));
                }
                res.add(new ArrayList<>(temp));
            }
            return res;
        }
    }

    static class Node {
        Node[] links;
        List<String> list;

        public Node() {
            links = new Node[26];
            list = new ArrayList<>();
        }

        boolean containsKey(char c) {
            return links[c - 'a'] != null;
        }

        Node get(char c) {
            return links[c - 'a'];
        }

        void put(char c, Node node) {
            links[c - 'a'] = node;
        }

        void addToList(String s) {
            list.add(s);
        }

        List<String> getList() {
            return list;
        }
    }
}