package g0901_1000.s0916_word_subsets;

// #Medium #Array #String #Hash_Table #2022_03_29_Time_30_ms_(57.30%)_Space_88_MB_(54.80%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input arrays `words1` and `words2` are not null.*);
	//@ requires(*The length of `words1` and `words2` is greater than or equal to - The length of each string in `words1` and `words2` is greater than or equal to - Each string in `words1` and `words2` consists only of lowercase English letters.*);
	//@ requires(*All the strings in `words1` are unique.*);
	//@ ensures(*The method returns a List of Strings.*);
	//@ ensures(*The returned List contains all the universal strings from `words1`.*);
	//@ ensures(*The order of the strings in the returned List can be any order.*);
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> l1 = new ArrayList<>();
        int[] target = new int[26];
        for (String s1 : words2) {
            int[] temp = new int[26];
            for (char ch1 : s1.toCharArray()) {
                temp[ch1 - 'a']++;
                target[ch1 - 'a'] = Math.max(target[ch1 - 'a'], temp[ch1 - 'a']);
            }
        }
        for (String s1 : words1) {
            int[] count = new int[26];
            for (char ch1 : s1.toCharArray()) {
                count[ch1 - 'a']++;
            }

            if (checkIt(target, count)) {
                l1.add(s1);
            }
        }
        return l1;
    }

    private boolean checkIt(int[] target, int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] < target[i]) {
                return false;
            }
        }
        return true;
    }
}