package g2401_2500.s2481_minimum_cuts_to_divide_a_circle;

// #Easy #Math #Geometry #2023_01_25_Time_0_ms_(100.00%)_Space_39.2_MB_(87.50%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is a positive integer greater than or equal to 1.*);
//@ ensures(*The circle is a valid geometric shape with a center and an edge.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum number of cuts needed to divide the circle into `n` equal slices.*);
//@ ensures(*The cuts are valid and can be represented by straight lines that touch points on the edge of the circle and/or its center.*);
//@ ensures(*The resulting slices are equal in size and shape.*);
//@ ensures(*The cuts divide the circle into distinct parts.*);
    public int numberOfCuts(int n) {
        if (n == 1) {
            return 0;
        }
        return n % 2 > 0 ? n : n / 2;
    }
}