package g2201_2300.s2244_minimum_rounds_to_complete_all_tasks;

// #Medium #Array #Hash_Table #Greedy #Counting
// #2022_06_11_Time_13_ms_(94.26%)_Space_99.8_MB_(26.72%)

import java.util.Arrays;

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the **minimum** rounds required to complete all the tasks, or_ `-1` _if it is not possible to complete all the tasks._*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int minimumRounds(int[] tasks) {
        Arrays.sort(tasks);
        int rounds = 0;
        int c = 1;
        for (int i = 0; i < tasks.length - 1; i++) {
            if (tasks[i] == tasks[i + 1]) {
                c++;
            } else {
                if (c == 1) {
                    return -1;
                }
                if (c % 3 == 0) {
                    rounds += c / 3;
                }
                if (c % 3 >= 1) {
                    rounds += c / 3 + 1;
                }
                c = 1;
            }
        }
        if (c == 1) {
            return -1;
        }
        if (c % 3 == 0) {
            rounds += c / 3;
        }
        if (c % 3 >= 1) {
            rounds += c / 3 + 1;
        }
        return rounds;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
