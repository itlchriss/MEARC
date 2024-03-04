package g1301_1400.s1353_maximum_number_of_events_that_can_be_attended;

// #Medium #Array #Greedy #Heap_Priority_Queue
// #2022_08_19_Time_53_ms_(99.53%)_Space_99.5_MB_(52.91%)

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `events` is not null.*);
//@ ensures(*The length of the input array `events` is greater than or equal to 1.*);
//@ ensures(*Each event in the input array `events` is represented by an array of length 2.*);
//@ ensures(*The start day of each event is less than or equal to the end day of that event.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer representing the maximum number of events that can be attended.*);
//@ ensures(*The return value is greater than or equal to 0.*);
//@ ensures(*The return value is less than or equal to the length of the input array `events`.*);
//@ ensures(*The return value is the maximum number of events that can be attended such that no two events overlap in terms of their start and end days.*);
    public int maxEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(e -> e[1]));
        int[] root = new int[events[events.length - 1][1] + 2];
        for (int i = 1; i < root.length; i++) {
            root[i] = i;
        }
        int res = 0;
        for (int[] e : events) {
            int nxtAvailable = find(root, e[0]);
            if (nxtAvailable <= e[1]) {
                res++;
                root[nxtAvailable] = find(root, nxtAvailable + 1);
            }
        }
        return res;
    }

    private int find(int[] root, int i) {
        if (root[i] != i) {
            root[i] = find(root, root[i]);
            return root[i];
        }
        return i;
    }
}