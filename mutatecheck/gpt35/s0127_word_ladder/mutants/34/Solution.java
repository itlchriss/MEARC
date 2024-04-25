import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
//@ requires(beginWord != endWord);
//@ requires((\forall int i; 0 <= i && i < wordDict.size(); wordDict.get(i).matches("[a-z]+")));
//@ ensures (\result >= 0);
//@ requires(beginWord.length() > 0 && beginWord.length() <= 10);
//@ requires(beginWord != null && endWord != null && wordDict != null);
//@ requires((\forall int i, j; 0 <= i && i < wordDict.size() && 0 <= j && j < wordDict.size(); i != j -> !wordDict.get(i).equals(wordDict.get(j))));
//@ requires((\forall int i; 0 <= i && i < wordDict.size(); wordDict.get(i) != null && wordDict.get(i).length() == beginWord.length()));
//@ requires(endWord.length() == beginWord.length());
//@ ensures((\forall int i; 0 <= i && i < wordDict.size(); wordDict.contains(\result) || !wordDict.contains(\result) && \result == 0));
//@ requires(wordDict.size() > 0 && wordDict.size() <= 5000);
    public int ladderLength(String beginWord, String endWord, List<String> wordDict) {
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> wordSet = new HashSet<>(wordDict);
        Set<String> visited = new HashSet<>();
        if (!wordDict.contains(endWord)) {
            return 0;
        }
        int len = 1;
        int strLen = beginWord.length();
        beginSet.add(beginWord);
        endSet.add(endWord);
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }
            Set<String> tempSet = new HashSet<>();
            for (String s : beginSet) {
                char[] chars = s.toCharArray();
                for (int i = 0; i < strLen; i++) {
                    char old = chars[i];
                    for (char j = 'a'; j <= 'z'; j++) {
                        chars[i] = j;
                        String temp = new String(chars);
                        if (endSet.contains(temp)) {
                            return len + 1;
                        }
                        if (!visited.contains(temp) && wordSet.contains(temp)) {
                            tempSet.add(temp);
                            visited.add(temp);
                        }
                    }

                }
            }
            beginSet = tempSet;
            len++;
        }
        return 0;
    }
}
