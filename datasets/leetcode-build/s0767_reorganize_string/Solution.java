package g0701_0800.s0767_reorganize_string;

// #Medium #String #Hash_Table #Sorting #Greedy #Heap_Priority_Queue #Counting
// #2022_03_26_Time_1_ms_(94.60%)_Space_42.4_MB_(42.02%)

public class Solution {
	//@ requires(*The input string `s` is not null.*);
	//@ requires(*The length of `s` is between 1 and 500.*);
	//@ requires(*`s` consists only of lowercase English letters.*);
	//@ ensures(*The returned string is a rearrangement of `s`.*);
	//@ ensures(*Any two adjacent characters in the returned string are not the same.*);
	//@ ensures(*If it is not possible to rearrange `s` such that no two adjacent characters are the same, the method returns an empty string "".*);
    public String reorganizeString(String s) {
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a']++;
        }
        int max = 0;
        int letter = 0;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > max) {
                max = hash[i];
                letter = i;
            }
        }
        if (max > (s.length() + 1) / 2) {
            return "";
        }
        char[] res = new char[s.length()];
        int idx = 0;
        while (hash[letter] > 0) {
            res[idx] = (char) (letter + 'a');
            idx += 2;
            hash[letter]--;
        }
        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                hash[i]--;
            }
        }
        return String.valueOf(res);
    }
}