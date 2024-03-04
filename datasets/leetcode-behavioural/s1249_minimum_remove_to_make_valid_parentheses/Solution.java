package g1201_1300.s1249_minimum_remove_to_make_valid_parentheses;

// #Medium #String #Stack #Data_Structure_II_Day_14_Stack_Queue
// #2022_03_12_Time_13_ms_(94.62%)_Space_51.3_MB_(53.95%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned string is a valid parentheses string.*);
//@ ensures(*The returned string is obtained by removing the minimum number of parentheses from the input string `s`.*);
//@ ensures(*The returned string can be written as `AB` (`A` concatenated with `B`), where `A` and `B` are valid strings.*);
//@ ensures(*The returned string can be written as `(A)`, where `A` is a valid string.*);
    public String minRemoveToMakeValid(String s) {
        int closingParantheis = 0;
        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                closingParantheis++;
            }
        }
        StringBuilder result = new StringBuilder();
        int openingParanthesis = 0;
        for (char ch : s.toCharArray()) {
            if (ch == ')' && openingParanthesis == 0) {
                closingParantheis--;
            } else {
                if (ch == ')') {
                    openingParanthesis--;
                }
                if (ch == '(' && closingParantheis == 0) {
                    continue;
                }
                if (ch == '(') {
                    openingParanthesis++;
                    closingParantheis--;
                }
                result.append(ch);
            }
        }
        return result.toString();
    }
}