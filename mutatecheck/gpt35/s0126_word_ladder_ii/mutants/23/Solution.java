package g0101_0200.s0126_word_ladder_ii;

// #Hard #String #Hash_Table #Breadth_First_Search #Backtracking
// #2022_07_02_Time_19_ms_(60.53%)_Space_43_MB_(90.12%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
//@ requires(beginWord.length() > 0 && beginWord.length() <= 5);
//@ ensures((\forall List<String> seq; \result.contains(seq); seq.size() > 0 && seq.get(0).equals(beginWord) && seq.get(seq.size() - 1).equals(endWord)));
//@ requires(beginWord != endWord);
//@ requires(beginWord != null && endWord != null && wordList != null);
//@ requires((\forall int i, j; 0 <= i && i < wordList.size() && 0 <= j && j < wordList.size(); i != j -> !wordList.get(i).equals(wordList.get(j))));
//@ requires(endWord.length() == beginWord.length());
//@ requires((\forall int i; 0 <= i && i < wordList.size(); wordList.get(i) != null && wordList.get(i).length() == beginWord.length()));
//@ ensures(\result != null);
//@ ensures((\forall List<String> seq1, seq2; \result.contains(seq1) && \result.contains(seq2); seq1 != seq2 -> seq1.size() != seq2.size()));
//@ requires(wordList.size() > 0 && wordList.size() <= 1000);
//@ ensures((\forall List<String> seq; \result.contains(seq); (\forall int i; 0 <= i && i < seq.size() - 1; isAdjacent(seq.get(i), seq.get(i + 1)))));
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        // reverse graph start from endWord
        Map<String, Set<String>> reverse = new HashMap<>();
        // remove the duplicate words
        Set<String> wordSet = new HashSet<>(wordList);
        // remove the first word to avoid cycle path
        wordSet.remove(beginWord);
        // store current layer nodes
        Queue<String> queue = new LinkedList<>();
        // first layer has only beginWord
        queue.add(beginWord);
        // store nextLayer nodes
        Set<String> nextLevel = new HashSet<>();
        // find endWord flag
        boolean findEnd = false;
        // traverse current layer nodes
        while (!queue.isEmpty()) {
            String word = queue.remove();
            for (String next : wordSet) {
                // is ladder words
                if (isLadder(word, next)) {
                    // construct the reverse graph from endWord
                    Set<String> reverseLadders =
                            reverse.computeIfAbsent(next, k -> new HashSet<>());
                    reverseLadders.add(word);
                    if (endWord.equals(next)) {
                        findEnd = true;
                    }
                    // store next layer nodes
                    nextLevel.add(next);
                }
            }
            // when current layer is all visited
            if (queue.isEmpty()) {
                // if find the endWord, then break the while loop
                if (findEnd) {
                    break;
                }
                // add next layer nodes to queue
                queue.addAll(nextLevel);
                // remove all next layer nodes in wordSet
                wordSet.removeAll(nextLevel);
                nextLevel.clear();
            }
        }
        // if can't reach endWord from startWord, then return ans.
        if (!findEnd) {
            return ans;
        }
        Set<String> path = new LinkedHashSet<>();
        path.add(endWord);
        // traverse reverse graph from endWord to beginWord
        findPath(endWord, beginWord, reverse, ans, path);
        return ans;
    }

    private void findPath(
            String endWord,
            String beginWord,
            Map<String, Set<String>> graph,
            List<List<String>> ans,
            Set<String> path) {
        Set<String> next = graph.get(endWord);
        if (next == null) {

        }
        for (String word : next) {
            path.add(word);
            if (beginWord.equals(word)) {
                List<String> shortestPath = new ArrayList<>(path);
                // reverse words in shortest path
                Collections.reverse(shortestPath);
                // add the shortest path to ans.
                ans.add(shortestPath);
            } else {
                findPath(word, beginWord, graph, ans, path);
            }
            path.remove(word);
        }
    }

    private boolean isLadder(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int diffCount = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                diffCount++;
            }
            if (diffCount > 1) {
                return false;
            }
        }
        return diffCount == 1;
    }
}
