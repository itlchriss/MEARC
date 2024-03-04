package g0701_0800.s0784_letter_case_permutation;

// #Medium #String #Bit_Manipulation #Backtracking #Algorithm_I_Day_11_Recursion_Backtracking
// #2022_03_26_Time_10_ms_(40.38%)_Space_55.5_MB_(5.44%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<String> ans = new ArrayList<>();
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The length of the input string `s` is between 1 and 12 (inclusive).*);
//@ ensures(*The input string `s` consists of lowercase English letters, uppercase English letters, and digits.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a list of strings.*);
//@ ensures(*The list contains all possible strings that can be created by transforming each letter in the input string `s` individually to be lowercase or uppercase.*);
//@ ensures(*The order of the strings in the output list can be in any order.*);

    public List<String> letterCasePermutation(String s) {
        helper(s, 0, "");
        return ans;
    }

    public void helper(String s, int curr, String temp) {
        if (curr == s.length()) {
            ans.add(temp);
            return;
        }
        if (Character.isDigit(s.charAt(curr))) {
            helper(s, curr + 1, temp + s.charAt(curr));
        } else {
            if (Character.isLowerCase(s.charAt(curr))) {
                helper(s, curr + 1, temp + s.charAt(curr));
                helper(s, curr + 1, temp + (s.substring(curr, curr + 1)).toUpperCase());
            } else {
                helper(s, curr + 1, temp + s.charAt(curr));
                helper(s, curr + 1, temp + (s.substring(curr, curr + 1)).toLowerCase());
            }
        }
    }
}