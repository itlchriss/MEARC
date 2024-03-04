package g1001_1100.s1047_remove_all_adjacent_duplicates_in_string;

// #Easy #String #Stack #2022_02_28_Time_3_ms_(99.99%)_Space_42.7_MB_(84.52%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The input string `s` consists of lowercase English letters.*);
//@ ensures(*The length of the input string `s` is between 1 and 10^5 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string is not null.*);
//@ ensures(*The output string consists of lowercase English letters.*);
//@ ensures(*The output string is the final string after all duplicate removals have been made.*);
//@ ensures(*The output string is unique, meaning there is only one possible final string after all duplicate removals have been made.*);
    public String removeDuplicates(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] array = s.toCharArray();
        int length = array.length;
        int fast = 0;
        int slow = 0;
        while (fast < length) {
            if (slow == 0 || array[fast] != array[slow - 1]) {
                array[slow++] = array[fast++];
            } else {
                if (array[fast] == array[slow - 1]) {
                    fast++;
                }
                slow--;
            }
        }
        return new String(array, 0, slow);
    }
}