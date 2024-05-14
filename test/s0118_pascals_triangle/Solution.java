package g0101_0200.s0118_pascals_triangle;

// #Easy #Top_Interview_Questions #Array #Dynamic_Programming #Data_Structure_I_Day_4_Array
// #Dynamic_Programming_I_Day_12 #Udemy_Dynamic_Programming
// #2022_06_23_Time_1_ms_(67.08%)_Space_42.4_MB_(5.58%)

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S2589")
public class Solution {
//@ requires(*The integer parameter `numRows` is greater than or equal to 1 and is less than or equal to 30.*);
//@ requires(*The elements in between the 1s in each sublist are the sum of the two numbers directly above them in the previous row of Pascal's triangle.*);
//@ ensures(*The list result contains `numRows` number of sublists.*);
//@ ensures(*Each sublist in the list result represents a row in Pascal's triangle.*);
//@ ensures(*The first sublist in the list result contains one element which is always 1.*);
//@ ensures(*Each subsequent sublist in the list result starts and ends with 1.*);
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