package g0801_0900.s0859_buddy_strings;

// #Easy #String #Hash_Table #2022_03_27_Time_2_ms_(85.33%)_Space_43.4_MB_(16.33%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The lengths of `s` and `goal` are both greater than or equal to - The lengths of `s` and `goal` are both less than or equal to 2 * 10^- `s` and `goal` consist of lowercase letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*If it is possible to swap two letters in `s` such that the result is equal to `goal`, return `true`.*);
//@ ensures(*If it is not possible to swap two letters in `s` such that the result is equal to `goal`, return `false`.*);
    public boolean buddyStrings(String s, String goal) {
        int first = -1;
        int second;
        int[] sCounts = new int[26];
        if (s.equals(goal)) {
            for (int i = 0; i < s.length(); i++) {
                sCounts[s.charAt(i) - 'a']++;
                if (sCounts[s.charAt(i) - 'a'] > 1) {
                    return true;
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            sCounts[curr - 'a']++;
            if (curr != goal.charAt(i)) {
                if (first == -1) {
                    first = i;
                } else {
                    second = i;
                    char[] ss = s.toCharArray();
                    char temp = ss[first];
                    ss[first] = ss[second];
                    ss[second] = temp;
                    return String.valueOf(ss).equals(goal);
                }
            }
        }
        return false;
    }
}