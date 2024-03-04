package g1001_1100.s1096_brace_expansion_ii;

// #Hard #String #Breadth_First_Search #Stack #Backtracking
// #2022_02_18_Time_23_ms_(60.36%)_Space_50.3_MB_(28.57%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input expression is a valid string representing a set of words based on the given grammar.*);
//@ ensures(*The length of the expression is between 1 and 60 characters.*);
//@ ensures(*The characters in the expression are either '{', '}', ',', or lowercase English letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a sorted list of words that the expression represents.*);
//@ ensures(*Each distinct word is written only once in the final answer.*);
    public List<String> braceExpansionII(String expression) {
        Set<String> res = flatten(expression);
        List<String> sorted = new ArrayList<>(res);
        Collections.sort(sorted);
        return sorted;
    }

    private Set<String> flatten(String expression) {
        Set<String> res = new HashSet<>();
        // A temp set to store cartesian product results.
        Set<String> curSet = new HashSet<>();
        int idx = 0;
        while (idx < expression.length()) {
            if (expression.charAt(idx) == '{') {
                // end will be the index of matching "}"
                int end = findClosingBrace(expression, idx);
                Set<String> set = flatten(expression.substring(idx + 1, end));
                curSet = concatenateSet(curSet, set);
                idx = end + 1;
            } else if (Character.isLowerCase(expression.charAt(idx))) {
                // Create set with single element
                Set<String> set =
                        new HashSet<>(Arrays.asList(Character.toString(expression.charAt(idx))));
                curSet = concatenateSet(curSet, set);
                idx++;
            } else if (expression.charAt(idx) == ',') {
                res.addAll(curSet);
                curSet.clear();
                idx++;
            }
        }
        // Don't forget!
        res.addAll(curSet);
        return res;
    }

    private Set<String> concatenateSet(Set<String> set1, Set<String> set2) {
        if (set1.isEmpty() || set2.isEmpty()) {
            return !set2.isEmpty() ? new HashSet<>(set2) : new HashSet<>(set1);
        }
        Set<String> res = new HashSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                res.add(s1 + s2);
            }
        }
        return res;
    }

    private int findClosingBrace(String expression, int start) {
        int count = 0;
        int idx = start;
        while (idx < expression.length()) {
            if (expression.charAt(idx) == '{') {
                count++;
            } else if (expression.charAt(idx) == '}') {
                count--;
            }
            if (count == 0) {
                break;
            }
            idx++;
        }
        return idx;
    }
}