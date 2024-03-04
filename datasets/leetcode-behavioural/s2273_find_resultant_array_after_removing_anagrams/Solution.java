package g2201_2300.s2273_find_resultant_array_after_removing_anagrams;

// #Easy #Array #String #Hash_Table #Sorting #2022_06_16_Time_2_ms_(99.10%)_Space_42.3_MB_(98.64%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `words` is not null.*);
//@ ensures(*The length of the input array `words` is greater than 1.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned list `words` contains the same elements as the input array `words`.*);
//@ ensures(*The returned list `words` does not contain any adjacent elements that are anagrams of each other.*);
//@ ensures(*The returned list `words` is obtained by deleting anagrams from the input array `words` in any arbitrary order.*);
    public List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }
        int uniqueWordIdx = 0;
        int currIdx = 1;
        result.add(words[uniqueWordIdx]);
        while (currIdx < words.length) {
            if (!isAnagram(words[currIdx], words[uniqueWordIdx])) {
                uniqueWordIdx = currIdx;
                result.add(words[uniqueWordIdx]);
            }
            currIdx++;
        }
        return result;
    }

    /*
    Utility to check if the 2 words are anagrams or not
    */
    private boolean isAnagram(String word1, String word2) {
        int[] charMap = new int[26];
        char[] word1Arr = word1.toCharArray();
        char[] word2Arr = word2.toCharArray();
        for (char a : word1Arr) {
            charMap[a - 'a']++;
        }
        for (char a : word2Arr) {
            charMap[a - 'a']--;
        }
        for (int a : charMap) {
            if (a != 0) {
                return false;
            }
        }
        return true;
    }
}