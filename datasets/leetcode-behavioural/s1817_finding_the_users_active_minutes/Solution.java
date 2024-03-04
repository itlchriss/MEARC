package g1801_1900.s1817_finding_the_users_active_minutes;

// #Medium #Array #Hash_Table #2022_05_03_Time_16_ms_(91.64%)_Space_121.1_MB_(48.79%)

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `logs` is a 2D integer array.*);
//@ ensures(*The input `k` is an integer.*);
//@ ensures(*The length of `logs` is greater than or equal to 1 and less than or equal to 10^4.*);
//@ ensures(*The ID of each user in `logs` is an integer between 0 and 10^9.*);
//@ ensures(*The time of each action in `logs` is an integer between 1 and 10^5.*);
//@ ensures(*The value of `k` is in the range [the maximum UAM for a user, 10^5].*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output `answer` is a 1-indexed array of integers.*);
//@ ensures(*The length of `answer` is equal to `k`.*);
//@ ensures(*For each index `j` in `answer` (1 <= j <= k), `answer[j]` is the number of users whose UAM equals `j`.*);
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        if (logs.length == 1) {
            int[] res = new int[k];
            res[0] = 1;
            return res;
        }
        Arrays.sort(logs, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
        int[] result = new int[k];
        int start = 1;
        int prevUser = logs[0][0];
        int prevMin = logs[0][1];
        int count = 1;
        while (true) {
            while (start < logs.length && prevUser == logs[start][0]) {
                if (prevMin != logs[start][1]) {
                    count++;
                }
                prevMin = logs[start][1];
                start++;
            }
            result[count - 1]++;
            if (start >= logs.length) {
                break;
            }
            count = 1;
            prevUser = logs[start][0];
            prevMin = logs[start][1];
        }
        return result;
    }
}