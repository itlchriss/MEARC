package g1801_1900.s1893_check_if_all_the_integers_in_a_range_are_covered;

// #Easy #Array #Hash_Table #Prefix_Sum #2022_05_09_Time_1_ms_(72.81%)_Space_42.4_MB_(39.06%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input ranges array is not null.*);
//@ ensures(*The input ranges array has at least one element.*);
//@ ensures(*The input ranges array has at most 50 elements.*);
//@ ensures(*Each element in the ranges array is an array of size - The start and end values in each element of the ranges array are integers.*);
//@ ensures(*The start value is less than or equal to the end value in each element of the ranges array.*);
//@ ensures(*The left and right values are integers.*);
//@ ensures(*The left value is less than or equal to the right value.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether each integer in the inclusive range [left, right] is covered by at least one interval in ranges.*);
//@ ensures(*If each integer in the range [left, right] is covered, the method returns true.*);
//@ ensures(*If any integer in the range [left, right] is not covered, the method returns false.*);
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] temp = new int[52];
        for (int[] range : ranges) {
            int start = range[0];
            int end = range[ranges[0].length - 1];
            temp[start] += 1;
            temp[end + 1] += -1;
        }
        for (int i = 1; i < temp.length; i++) {
            temp[i] += temp[i - 1];
        }
        for (int i = left; i <= right; i++) {
            if (temp[i] == 0) {
                return false;
            }
        }
        return true;
    }
}