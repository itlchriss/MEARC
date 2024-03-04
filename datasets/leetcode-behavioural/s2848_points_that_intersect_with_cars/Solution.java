package g2801_2900.s2848_points_that_intersect_with_cars;

// #Easy #Hash_Table #Math #Prefix_Sum #2023_12_13_Time_1_ms_(100.00%)_Space_43.8_MB_(11.45%)

import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `nums` is a non-empty list of lists.*);
//@ ensures(*Each sublist in `nums` contains exactly two integers.*);
//@ ensures(*The integers in each sublist represent the starting and ending points of a car.*);
//@ ensures(*The starting point is less than or equal to the ending point for each car.*);
//@ ensures(*The starting and ending points of each car are between 1 and 100 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the number of integer points on the line that are covered with any part of a car.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
//@ ensures(*The returned value is less than or equal to 100.*);
//@ ensures(*The returned value is the sum of the lengths of all intervals formed by the starting and ending points of the cars, minus the number of overlapping points.*);
//@ ensures(*The returned value is the sum of the lengths of all intervals formed by the starting and ending points of the cars, minus the number of overlapping points, plus 1.*);
    public int numberOfPoints(List<List<Integer>> nums) {
        int min = 101;
        int max = 0;
        int[] count = new int[102];
        for (List<Integer> list : nums) {
            int num1 = list.get(0);
            int num2 = list.get(1);

            if (num1 < min) {
                min = num1;
            }
            if (num2 > max) {
                max = num2;
            }

            count[num1]--;
            count[num2 + 1]++;
        }

        int result = 0;
        int balance = 0;
        for (; min <= max; min++) {
            balance += count[min];
            if (balance < 0) {
                result++;
            }
        }
        return result;
    }
}