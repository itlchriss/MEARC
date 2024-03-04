package g1101_1200.s1138_alphabet_board_path;

// #Medium #String #Hash_Table #2023_06_01_Time_1_ms_(48.92%)_Space_40.5_MB_(86.56%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input target is a non-empty string.*);
//@ ensures(*The target consists only of English lowercase letters.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a string representing a sequence of moves.*);
//@ ensures(*The sequence of moves makes the answer equal to the target in the minimum number of moves.*);
//@ ensures(*The sequence of moves can be any path that achieves the target.*);
//@ ensures(*The output string may contain the characters 'U', 'D', 'L', 'R', and '!'.*);
//@ ensures(*The output string may contain any combination of the above characters.*);
//@ ensures(*The output string may contain multiple occurrences of the same character.*);
//@ ensures(*The output string may have a length greater than or equal to the length of the target string.*);
    public String alphabetBoardPath(String target) {
        if (target.length() == 0) {
            return "";
        }
        int sourceRow = 0;
        int sourceCol = 0;
        StringBuilder path = new StringBuilder();
        for (char c : target.toCharArray()) {
            int position = c - 97;
            int targetRow = position / 5;
            int targetCol = position % 5;
            if (targetCol < sourceCol) {
                path.append(helper("L", sourceCol - targetCol));
            }
            if (targetRow < sourceRow) {
                path.append(helper("U", sourceRow - targetRow));
            }
            if (targetRow > sourceRow) {
                path.append(helper("D", targetRow - sourceRow));
            }
            if (targetCol > sourceCol) {
                path.append(helper("R", targetCol - sourceCol));
            }
            path.append("!");
            sourceRow = targetRow;
            sourceCol = targetCol;
        }
        return path.toString();
    }

    public StringBuilder helper(String dir, int time) {
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < time; i++) {
            path.append(dir);
        }
        return path;
    }
}