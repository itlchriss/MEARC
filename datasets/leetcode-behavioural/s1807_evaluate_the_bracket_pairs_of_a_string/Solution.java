package g1801_1900.s1807_evaluate_the_bracket_pairs_of_a_string;

// #Medium #Array #String #Hash_Table #2022_05_03_Time_40_ms_(80.47%)_Space_79.8_MB_(96.48%)

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `s` is not null.*);
//@ ensures(*The input list `knowledge` is not null.*);
//@ ensures(*Each element in `knowledge` is a list of size 2.*);
//@ ensures(*The length of each key and value in `knowledge` is between 1 and 10.*);
//@ ensures(*The input string `s` consists of lowercase English letters and round brackets.*);
//@ ensures(*Every open bracket in `s` has a corresponding close bracket.*);
//@ ensures(*The key in each bracket pair of `s` is non-empty.*);
//@ ensures(*Each key in `knowledge` is unique.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output string is the resulting string after evaluating all of the bracket pairs.*);
//@ ensures(*Each bracket pair in the output string is replaced with the corresponding value from `knowledge` if it exists, or with a question mark if the value is unknown.*);
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> knowledgeMapper = new HashMap<>();
        for (List<String> pair : knowledge) {
            knowledgeMapper.put(pair.get(0), pair.get(1));
        }
        StringBuilder answer = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char letter = s.charAt(i);
            if (letter == '(') {
                StringBuilder key = new StringBuilder();
                letter = s.charAt(++i);
                while (letter != ')') {
                    key.append(letter);
                    letter = s.charAt(++i);
                }
                answer.append(knowledgeMapper.getOrDefault(key.toString(), "?"));
            } else {
                answer.append(letter);
            }
            i++;
        }
        return answer.toString();
    }
}