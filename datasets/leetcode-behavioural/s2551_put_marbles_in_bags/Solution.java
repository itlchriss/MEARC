package g2501_2600.s2551_put_marbles_in_bags;

// #Hard #Array #Sorting #Greedy #Heap_Priority_Queue
// #2023_08_15_Time_33_ms_(99.82%)_Space_55.1_MB_(97.58%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `weights` is not null.*);
//@ ensures(*The length of the input array `weights` is greater than or equal to `k`.*);
//@ ensures(*The value of `k` is greater than or equal to - The values in the input array `weights` are positive integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the difference between the maximum and minimum scores among marble distributions.*);
//@ ensures(*The maximum score is calculated by distributing the marbles in a way that maximizes the sum of the costs of all the `k` bags.*);
//@ ensures(*The minimum score is calculated by distributing the marbles in a way that minimizes the sum of the costs of all the `k` bags.*);
//@ ensures(*The marbles are divided into `k` bags according to the given rules:*);
//@ ensures(*  - No bag is empty.*);
//@ ensures(*  - If the `i`th marble and `j`th marble are in a bag, then all marbles with an index between the `i`th and `j`th indices should also be in that same bag.*);
//@ ensures(*  - If a bag consists of all the marbles with an index from `i` to `j` inclusively, then the cost of the bag is `weights[i] + weights[j]`.*);
    public long putMarbles(int[] weights, int k) {
        long minAns = weights[0] + (long) weights[weights.length - 1];
        long maxAns = weights[0] + (long) weights[weights.length - 1];
        long[] arr = new long[weights.length - 1];
        for (int i = 1; i < weights.length; i++) {
            arr[i - 1] = weights[i] + (long) weights[i - 1];
        }
        Arrays.sort(arr);
        for (int i = 0; i < k - 1; i++) {
            minAns = minAns + arr[i];
        }
        for (int i = arr.length - 1; i > arr.length - k; i--) {
            maxAns = maxAns + arr[i];
        }
        return maxAns - minAns;
    }
}