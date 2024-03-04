package g1901_2000.s1921_eliminate_maximum_number_of_monsters;

// #Medium #Array #Sorting #Greedy #2022_05_14_Time_19_ms_(90.78%)_Space_51.1_MB_(97.09%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input arrays `dist` and `speed` are not null.*);
//@ ensures(*The lengths of the input arrays `dist` and `speed` are equal.*);
//@ ensures(*The length of the input arrays `dist` and `speed` is at least 1.*);
//@ ensures(*The elements in the input array `dist` are positive integers.*);
//@ ensures(*The elements in the input array `speed` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The return value is an integer.*);
//@ ensures(*The return value is greater than or equal to 0.*);
//@ ensures(*The return value is less than or equal to the length of the input arrays `dist` and `speed`.*);
//@ ensures(*If the return value is equal to the length of the input arrays `dist` and `speed`, it means all the monsters can be eliminated before they reach the city.*);
//@ ensures(*If the return value is less than the length of the input arrays `dist` and `speed`, it means some monsters cannot be eliminated before they reach the city.*);
//@ ensures(*The input arrays `dist` and `speed` are not modified by the method.*);
    public int eliminateMaximum(int[] dist, int[] speed) {
        for (int i = 0; i < dist.length; i++) {
            dist[i] = (dist[i] - 1) / speed[i] + 1;
        }
        Arrays.sort(dist);
        int ans = 1;
        int time = 1;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] > time) {
                ans++;
                time++;
            } else {
                return ans;
            }
        }
        return ans;
    }
}