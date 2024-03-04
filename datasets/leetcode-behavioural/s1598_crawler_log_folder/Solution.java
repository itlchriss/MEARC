package g1501_1600.s1598_crawler_log_folder;

// #Easy #Array #String #Stack #2022_04_08_Time_0_ms_(100.00%)_Space_42_MB_(81.44%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `logs` is not null.*);
//@ ensures(*The length of `logs` is greater than or equal to 1.*);
//@ ensures(*Each element in `logs` is a valid operation described in the requirements.*);
//@ ensures(*The folder names in `logs` consist of lowercase English letters and digits.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer representing the minimum number of operations needed to go back to the main folder after the change folder operations.*);
    public int minOperations(String[] logs) {
        int steps = 0;
        for (String log : logs) {
            if (log.equals("../")) {
                if (steps > 0) {
                    steps--;
                }
            } else if (!log.equals("./")) {
                steps++;
            }
        }
        return steps;
    }
}