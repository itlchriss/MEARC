package g2701_2800.s2798_number_of_employees_who_met_the_target;

// #Easy #Array #Enumeration #2023_09_14_Time_0_ms_(100.00%)_Space_40.6_MB_(98.10%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `hours` must not be null.*);
//@ ensures(*The length of the input array `hours` must be greater than 0.*);
//@ ensures(*The length of the input array `hours` must be less than or equal to 50.*);
//@ ensures(*The target value must be a non-negative integer.*);
//@ ensures(*Each element in the input array `hours` must be a non-negative integer.*);
//@ ensures(*Each element in the input array `hours` must be less than or equal to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return an integer value representing the number of employees who worked at least `target` hours.*);
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int count = 0;
        for (int i : hours) {
            if (i >= target) {
                count++;
            }
        }
        return count;
    }
}