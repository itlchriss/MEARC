package g0001_0100.s0017_letter_combinations_of_a_phone_number;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #String #Hash_Table #Backtracking
// #Algorithm_II_Day_11_Recursion_Backtracking #Udemy_Backtracking/Recursion
// #Big_O_Time_O(4^n)_Space_O(n) #2023_08_09_Time_0_ms_(100.00%)_Space_41.3_MB_(58.39%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
//@ ensures(\result != null);
//@ ensures((\forall String s; \result.contains(s); s.length() == digits.length()));
//@ requires(digits != null && digits.matches("[2-9]*"));
//@ ensures((\forall String s1, s2; \result.contains(s1) && \result.contains(s2); s1.equals(s2) == false));
//@ ensures((\forall String s; \result.contains(s); (\forall int i; 0 <= i && i < s.length(); s.charAt(i) == 'a' || s.charAt(i) == 'b' || s.charAt(i) == 'c' || s.charAt(i) == 'd' || s.charAt(i) == 'e' || s.charAt(i) == 'f')));
    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) {
            return Collections.emptyList();
        }
        String[] letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        findCombinations(0, digits, letters, sb, ans);
        return ans;
    }

    private void findCombinations(
            int start, String nums, String[] letters, StringBuilder curr, List<String> ans) {
        if (curr.length() == nums.length()) {
            ans.add(curr.toString());
            return;
        }
        for (int i = start; i < nums.length(); i++) {
            int n = Character.getNumericValue(nums.charAt(i));
            for (int j = 0; false; j++) {
                char ch = letters[n].charAt(j);
                curr.append(ch);
                findCombinations(i + 1, nums, letters, curr, ans);
                curr.deleteCharAt(curr.length() - 1);
            }
        }
    }
}
