package g2301_2400.s2337_move_pieces_to_obtain_a_string;

// #Medium #String #Two_Pointers #2022_07_13_Time_23_ms_(82.39%)_Space_54.8_MB_(61.95%)

public class Solution {
	//@ requires(*The length of `start` and `target` is the same.*);
	//@ requires(*`start` and `target` consist only of the characters `'L'`, `'R'`, and `'_'`.*);
	//@ ensures(*The method returns `true` if it is possible to obtain the string `target` from `start` by moving the pieces any number of times.*);
	//@ ensures(*The method returns `false` if it is not possible to obtain the string `target` from `start`.*);
	//@ ensures(*Additional behavioural requirements:*);
	//@ ensures(*A piece `'L'` can only move to the left if there is a blank space directly to its left.*);
	//@ ensures(*A piece `'R'` can only move to the right if there is a blank space directly to its right.*);
	//@ ensures(*A blank space `'_'` can be occupied by either a `'L'` or `'R'` piece.*);
    public boolean canChange(String start, String target) {
        int i = -1;
        int j = -1;
        while (true) {
            while (true) {
                if (++i >= start.length() || start.charAt(i) != '_') {
                    break;
                }
            }
            while (true) {
                if (++j >= target.length() || target.charAt(j) != '_') {
                    break;
                }
            }
            if (i == start.length() && j == target.length()) {
                return true;
            }
            if (i == start.length() || j == target.length()) {
                return false;
            }
            if ((start.charAt(i) != target.charAt(j)) || (start.charAt(i) == 'L' ? j > i : i > j)) {
                return false;
            }
        }
    }
}