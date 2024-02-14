package g1601_1700.s1678_goal_parser_interpretation;

// #Easy #String #Programming_Skills_I_Day_8_String
// #2022_04_21_Time_0_ms_(100.00%)_Space_40.4_MB_(87.95%)

public class Solution {
	//@ requires(*The input `command` is a non-null string.*);
	//@ requires(*The length of `command` is between 1 and 100 (inclusive).*);
	//@ requires(*`command` consists only of the characters "G", "()", and/or "(al)".*);
	//@ ensures(*The output is a non-null string.*);
	//@ ensures(*The output is the concatenation of the interpreted strings in the original order.*);
	//@ ensures(*The output is the interpretation of `command` according to the Goal Parser rules:*);
	//@ ensures(*- "G" is interpreted as "G".*);
	//@ ensures(*- "()" is interpreted as "o".*);
	//@ ensures(*- "(al)" is interpreted as "al".*);
    public String interpret(String command) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < command.length()) {
            if (command.charAt(i) == '(' && command.charAt(i + 1) == ')') {
                sb.append("o");
                i++;
            } else if ((command.charAt(i) != '(' || command.charAt(i + 1) == ')')
                    && command.charAt(i) != ')') {
                sb.append(command.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }
}