package g1601_1700.s1665_minimum_initial_energy_to_finish_tasks;

// #Hard #Array #Sorting #Greedy #2022_04_23_Time_30_ms_(81.16%)_Space_99.8_MB_(21.74%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `tasks` must not be null.*);
//@ ensures(*The length of the input array `tasks` must be greater than or equal to 1.*);
//@ ensures(*The length of each task array within `tasks` must be 2.*);
//@ ensures(*The value of `actual_i` for each task must be greater than or equal to 1.*);
//@ ensures(*The value of `minimum_i` for each task must be greater than or equal to `actual_i`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the minimum initial amount of energy needed to finish all the tasks.*);
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[1] - a[0] - b[1] + b[0]);
        int prev = 0;
        for (int[] item : tasks) {
            prev = Math.max(prev + item[0], item[1]);
        }
        return prev;
    }
}