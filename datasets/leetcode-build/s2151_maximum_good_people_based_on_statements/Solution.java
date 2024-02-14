package g2101_2200.s2151_maximum_good_people_based_on_statements;

// #Hard #Array #Bit_Manipulation #Backtracking #Enumeration
// #2022_03_31_Time_76_ms_(47.57%)_Space_71.5_MB_(26.22%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `statements` is not null.*);
	//@ requires(*The length of `statements` is equal to the length of `statements[i]` for all `i`.*);
	//@ requires(*The length of `statements` is at least 2 and at most 15.*);
	//@ requires(*Each element `statements[i][i]` is equal to 2.*);
	//@ ensures(*The method returns an integer representing the maximum number of people who can be good based on the statements made by the `n` people.*);
	//@ ensures(*The returned value is at least 0 and at most `n`.*);
	//@ ensures(*The method does not modify the input array `statements`.*);
    public int maximumGood(int[][] statements) {
        int[] known = new int[statements.length];
        Arrays.fill(known, 2);
        return max(statements, known, 0);
    }

    private int max(int[][] statements, int[] known, int position) {
        if (position == statements.length) {
            return (int) Arrays.stream(known).filter(a -> a == 1).count();
        }
        switch (known[position]) {
            case 0:
                return assumeBad(statements, known, position);
            case 1:
                return assumeGood(statements, known, position);
            default:
                return Math.max(
                        assumeBad(statements, known, position),
                        assumeGood(statements, known, position));
        }
    }

    private int assumeBad(int[][] statements, int[] known, int position) {
        int[] updatedKnown = known.clone();
        updatedKnown[position] = 0;
        return max(statements, updatedKnown, position + 1);
    }

    private int assumeGood(int[][] statements, int[] known, int position) {
        int[] updatedKnown = known.clone();
        boolean conflictDetected = false;
        updatedKnown[position] = 1;
        for (int i = 0; i < statements[position].length; i++) {
            int answer = statements[position][i];
            if (answer != 2) {
                if (known[i] != 2 && answer != known[i]) {
                    conflictDetected = true;
                    break;
                }
                updatedKnown[i] = answer;
            }
        }
        return conflictDetected ? 0 : max(statements, updatedKnown, position + 1);
    }
}