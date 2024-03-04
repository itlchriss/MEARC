package g2301_2400.s2358_maximum_number_of_groups_entering_a_competition;

// #Medium #Array #Math #Greedy #Binary_Search
// #2022_08_14_Time_0_ms_(100.00%)_Space_70.5_MB_(27.33%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `grades` is not null.*);
//@ ensures(*The length of the input array `grades` is greater than or equal to 1.*);
//@ ensures(*Each element in the input array `grades` is a positive integer.*);
//@ ensures(*The input array `grades` is not empty.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the maximum number of groups that can be formed.*);
//@ ensures(*The groups are ordered in a way that the sum of grades of students in the i-th group is less than the sum of grades of students in the (i + 1)-th group, for all groups (except the last).*);
//@ ensures(*The total number of students in the i-th group is less than the total number of students in the (i + 1)-th group, for all groups (except the last).*);
    public int maximumGroups(int[] grades) {
        int len = grades.length;
        return (int) (-1 + Math.sqrt(1D + 8 * len)) / 2;
    }
}