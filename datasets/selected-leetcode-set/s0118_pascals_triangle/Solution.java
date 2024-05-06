package g0101_0200.s0118_pascals_triangle;

// #Easy #Top_Interview_Questions #Array #Dynamic_Programming #Data_Structure_I_Day_4_Array
// #Dynamic_Programming_I_Day_12 #Udemy_Dynamic_Programming
// #2022_06_23_Time_1_ms_(67.08%)_Space_42.4_MB_(5.58%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S2589")
public class Solution {
//@ ensures(*The integer parameter `numRows` is greater than or equal to 1 and is less than or equal to 30.*);
//@ ensures(*The result is a list of lists representing the first `numRows` rows of Pascal's triangle.*);
//@ ensures(*Each inner list in the result represents a row of Pascal's triangle.*);
//@ ensures(*Each number in the inner lists is the sum of the two numbers directly above it in the row.*);
//@ ensures(*The first element of each row and the last element of each row are always 1.*);
//@ ensures(*The length of each inner list in the result corresponds to the row number starting from 1.*);
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