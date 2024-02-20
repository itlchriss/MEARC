package g0001_0100.s0017_letter_combinations_of_a_phone_number;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #String #Hash_Table #Backtracking
// #Algorithm_II_Day_11_Recursion_Backtracking #Udemy_Backtracking/Recursion
// #Big_O_Time_O(4^n)_Space_O(n) #2023_08_09_Time_0_ms_(100.00%)_Space_41.3_MB_(58.39%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	//@ requires(*1. The input string `digits` must not be null.*);
	//@ requires(*2. The input string `digits` must only contain digits from 2 to 9 inclusive.*);
	//@ requires(*3. The length of the input string `digits` must be between 0 and 4 inclusive.*);
	//@ ensures(*1. The method should return a list of strings representing all possible letter combinations that the input number could represent.*);
	//@ ensures(*2. The order of the letter combinations in the output list does not matter.*);
	//@ ensures(*3. If the input string `digits` is empty, the method should return an empty list.*);
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
            for (int j = 0; j < letters[n].length(); j++) {
                char ch = letters[n].charAt(j);
                curr.append(ch);
                findCombinations(i + 1, nums, letters, curr, ans);
                curr.deleteCharAt(curr.length() - 1);
            }
        }
    }
}