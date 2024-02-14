package g2801_2900.s2813_maximum_elegance_of_a_k_length_subsequence;

// #Hard #Array #Hash_Table #Sorting #Greedy #Heap_Priority_Queue
// #2023_11_20_Time_35_ms_(100.00%)_Space_81.2_MB_(96.77%)

import java.util.ArrayDeque;
import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `items` is not null.*);
	//@ requires(*The length of the input array `items` is greater than or equal to - The length of each subarray in `items` is equal to - The profit values in `items` are positive integers.*);
	//@ requires(*The category values in `items` are positive integers.*);
	//@ requires(*The value of `k` is greater than or equal to - The value of `k` is less than or equal to the length of `items`.*);
	//@ ensures(*The method returns an integer value representing the maximum elegance of a subsequence of `items` with size exactly `k`.*);
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (a, b) -> b[0] - a[0]);
        int n = items.length;
        boolean[] vis = new boolean[n];
        ArrayDeque<Long> arr = new ArrayDeque<>();
        long distinct = 0;
        long sum = 0;
        for (int i = 0; i < k; i++) {
            sum += items[i][0];
            if (vis[items[i][1] - 1]) {
                arr.push((long) items[i][0]);
            } else {
                ++distinct;
                vis[items[i][1] - 1] = true;
            }
        }
        long ans = sum + distinct * distinct;
        for (int i = k; i < n && distinct < k; i++) {
            if (!vis[items[i][1] - 1]) {
                sum -= arr.pop();
                sum += items[i][0];
                ++distinct;
                vis[items[i][1] - 1] = true;
                ans = Math.max(ans, sum + distinct * distinct);
            }
        }
        return ans;
    }
}