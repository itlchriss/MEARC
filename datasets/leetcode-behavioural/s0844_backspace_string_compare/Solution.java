package g0801_0900.s0844_backspace_string_compare;

// #Easy #String #Two_Pointers #Stack #Simulation #Algorithm_II_Day_4_Two_Pointers
// #Level_1_Day_14_Stack #2022_03_24_Time_0_ms_(100.00%)_Space_40.1_MB_(87.37%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input strings `s` and `t` are not null.*);
//@ ensures(*The lengths of `s` and `t` are between 1 and 200 (inclusive).*);
//@ ensures(*The characters in `s` and `t` are either lowercase letters or `'#'`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether `s` and `t` are equal when both are typed into empty text editors.*);
//@ ensures(*If the method returns `true`, it means that `s` and `t` become equal after applying backspaces.*);
//@ ensures(*If the method returns `false`, it means that `s` and `t` do not become equal after applying backspaces.*);
    public boolean backspaceCompare(String s, String t) {
        return cmprStr(s).equals(cmprStr(t));
    }

    private String cmprStr(String str) {
        StringBuilder res = new StringBuilder();
        int count = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            char currChar = str.charAt(i);
            if (currChar == '#') {
                count++;
            } else {
                if (count > 0) {
                    count--;
                } else {
                    res.append(currChar);
                }
            }
        }
        return res.toString();
    }
}