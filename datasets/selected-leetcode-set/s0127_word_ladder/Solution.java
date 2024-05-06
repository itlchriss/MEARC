package g0101_0200.s0127_word_ladder;

// #Hard #Top_Interview_Questions #String #Hash_Table #Breadth_First_Search
// #Graph_Theory_I_Day_12_Breadth_First_Search
// #2022_06_23_Time_37_ms_(94.58%)_Space_54.1_MB_(66.08%)

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
//@ ensures(*The integer result is the number of words in the shortest transformation sequence from the `beginWord` to the `endWord`, or 0 if no such sequence exists.*);
//@ ensures(*The length of the `beginWord` is greater than or equal to 1 and is less than or equal to 10.*);
//@ ensures(*The length of the `endWord` is equal to the length of the `beginWord`.*);
//@ ensures(*The length of the `wordList` is greater than or equal to 1 and is less than or equal to 5000.*);
//@ ensures(*The length of each word in the `wordList` is equal to the length of the `beginWord`.*);
//@ ensures(*The `beginWord`, `endWord`, and all words in the `wordList` consist of lowercase English letters.*);
//@ ensures(*The `beginWord` is not equal to the `endWord`.*);
//@ ensures(*All words in the `wordList` are unique.*);
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
                    chars[i] = old;
                }
            }
            beginSet = tempSet;
            len++;
        }
        return 0;
    }
}