package g0101_0200.s0118_pascals_triangle;

// #Easy #Top_Interview_Questions #Array #Dynamic_Programming #Data_Structure_I_Day_4_Array
// #Dynamic_Programming_I_Day_12 #Udemy_Dynamic_Programming
// #2022_06_23_Time_1_ms_(67.08%)_Space_42.4_MB_(5.58%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S2589")
public class Solution {
	//@ requires(*The input `numRows` must be an integer.*);
	//@ requires(*The input `numRows` must be greater than or equal to 1.*);
	//@ requires(*The input `numRows` must be less than or equal to 30.*);
	//@ ensures(*The output is a list of lists of integers.*);
	//@ ensures(*The length of the output list is equal to `numRows`.*);
	//@ ensures(*Each inner list in the output list represents a row in Pascal's triangle.*);
	//@ ensures(*The first element of each inner list is always 1.*);
	//@ ensures(*The last element of each inner list is always 1.*);
	//@ ensures(*Each element in the inner lists (except the first and last elements) is the sum of the two elements directly above it.*);
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> currRow = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i || i <= 1) {
                    currRow.add(1);
                } else {
                    int currCell = output.get(i - 1).get(j - 1) + output.get(i - 1).get(j);
                    currRow.add(currCell);
                }
            }
            output.add(currRow);
        }
        return output;
    }
}