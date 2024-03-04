package g2001_2100.s2039_the_time_when_the_network_becomes_idle;

// #Medium #Array #Breadth_First_Search #Graph
// #2022_05_29_Time_134_ms_(72.29%)_Space_145.3_MB_(56.63%)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
//@ ensures(***Preconditions:***);
//@ ensures(**);
//@ ensures(*1. The input `edges` is a 2D integer array representing the message channels between servers.*);
//@ ensures(*2. The input `patience` is a 0-indexed integer array of length `n`, where `n` is the number of servers.*);
//@ ensures(*3. All servers are connected, meaning a message can be passed from one server to any other server(s) directly or indirectly through the message channels.*);
//@ ensures(*4. The server labeled `0` is the master server and the rest are data servers.*);
//@ ensures(*5. Each data server needs to send its message to the master server for processing and wait for a reply.*);
//@ ensures(*6. At the beginning of second `0`, each data server sends its message to be processed.*);
//@ ensures(**);
//@ ensures(***Postconditions:***);
//@ ensures(**);
//@ ensures(*1. The method returns the earliest second starting from which the network becomes idle.*);
//@ ensures(*2. The network becomes idle when there are no messages passing between servers or arriving at servers.*);
//@ ensures(**);
//@ ensures(***Additional Preconditions:***);
//@ ensures(**);
//@ ensures(*1. The input `n` is a positive integer.*);
//@ ensures(*2. The length of `patience` is equal to `n`.*);
//@ ensures(*3. The value of `patience[0]` is `0`.*);
//@ ensures(*4. The values in `patience` are positive integers.*);
//@ ensures(*5. The length of `edges` is greater than or equal to 1 and less than or equal to the minimum of `10^5` and `n * (n - 1) / 2`.*);
//@ ensures(*6. Each element in `edges` is a 2-element array.*);
//@ ensures(*7. The values in `edges` are valid server labels (between `0` and `n-1`).*);
//@ ensures(*8. There are no duplicate edges in `edges`.*);
//@ ensures(*9. Each server can directly or indirectly reach another server.*);
    public int networkBecomesIdle(int[][] edges, int[] pat) {
        int n = pat.length;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] arr : edges) {
            adj.get(arr[0]).add(arr[1]);
            adj.get(arr[1]).add(arr[0]);
        }
        int[] distance = new int[n];
        Arrays.fill(distance, 99999);
        distance[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> Integer.compare(a1[1], a2[1]));
        pq.add(new int[] {0, 0});
        while (!pq.isEmpty()) {
            int[] a = pq.poll();
            int node = a[0];
            for (Integer nn : adj.get(node)) {
                if (distance[node] + 1 < distance[nn]) {
                    distance[nn] = 1 + distance[node];
                    pq.add(new int[] {nn, distance[nn]});
                }
            }
        }
        int max = 0;
        for (int i = 1; i < n; i++) {
            int num1 = 2 * distance[i];
            int num2 = num1 / pat[i];
            if (num1 % pat[i] != 0) {
                num2++;
            }
            num2--;
            num2 *= pat[i];
            max = Math.max(max, num2 + num1);
        }
        return max + 1;
    }
}