package g0801_0900.s0859_buddy_strings;

// #Easy #String #Hash_Table #2022_03_27_Time_2_ms_(85.33%)_Space_43.4_MB_(16.33%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Given two strings `s` and `goal`, return `true` _if you can swap two letters in_ `s` _so the result is equal to_ `goal`_, otherwise, return_ `false`_._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
