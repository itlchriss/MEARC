package g2401_2500.s2462_total_cost_to_hire_k_workers;

// #Medium #Array #Two_Pointers #Heap_Priority_Queue #Simulation
// #2023_01_07_Time_57_ms_(96.24%)_Space_54_MB_(92.26%)

import java.util.PriorityQueue;

public class Solution {
//@ ensures(***Preconditions:***);
//@ ensures(**);
//@ ensures(*1. The input array `costs` is not null.*);
//@ ensures(*2. The length of the input array `costs` is greater than or equal to 1.*);
//@ ensures(*3. The values in the input array `costs` are positive integers.*);
//@ ensures(*4. The input integer `k` is greater than or equal to 1.*);
//@ ensures(*5. The input integer `candidates` is greater than or equal to 1.*);
//@ ensures(*6. The input integer `k` is less than or equal to the length of the input array `costs`.*);
//@ ensures(*7. The input integer `candidates` is less than or equal to the length of the input array `costs`.*);
//@ ensures(**);
//@ ensures(***Postconditions:***);
//@ ensures(**);
//@ ensures(*1. The output is a long integer representing the total cost to hire exactly `k` workers.*);
//@ ensures(*2. The output is greater than or equal to 0.*);
//@ ensures(*3. The output is the sum of the costs of the workers chosen in each hiring session.*);
//@ ensures(*4. Each worker can only be chosen once.*);
//@ ensures(*5. The number of hiring sessions is equal to `k`.*);
//@ ensures(*6. In each hiring session, exactly one worker is hired.*);
//@ ensures(*7. In each hiring session, the worker with the lowest cost is chosen from either the first `candidates` workers or the last `candidates` workers.*);
//@ ensures(*8. If there are fewer than `candidates` workers remaining, the worker with the lowest cost among them is chosen.*);
//@ ensures(*9. If there are multiple workers with the same lowest cost, the worker with the smallest index is chosen.*);
//@ ensures(*10. The indexing of the workers may be changed in the process.*);
    public long totalCost(int[] costs, int k, int candidates) {
        // Hint: Maintain two minheaps, one for the left end and one for the right end
        // This problem is intentionally made complex but actually we don't have to record the
        // indices
        int n = costs.length;
        PriorityQueue<Integer> leftMinHeap = new PriorityQueue<>();
        PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();
        long res = 0;
        if (2 * candidates >= n) {
            for (int i = 0; i <= n / 2; i++) {
                leftMinHeap.add(costs[i]);
            }
            for (int i = n / 2 + 1; i < n; i++) {
                rightMinHeap.add(costs[i]);
            }
            while (!leftMinHeap.isEmpty() && !rightMinHeap.isEmpty() && k > 0) {
                if (leftMinHeap.peek() <= rightMinHeap.peek()) {
                    res += leftMinHeap.poll();
                } else {
                    res += rightMinHeap.poll();
                }
                k -= 1;
            }
        } else {
            int left = candidates;
            int right = n - candidates - 1;
            for (int i = 0; i < candidates; i++) {
                leftMinHeap.add(costs[i]);
            }
            for (int i = n - candidates; i < n; i++) {
                rightMinHeap.add(costs[i]);
            }
            while (!leftMinHeap.isEmpty() && !rightMinHeap.isEmpty() && k > 0) {
                if (leftMinHeap.peek() <= rightMinHeap.peek()) {
                    res += leftMinHeap.poll();
                    if (left <= right) {
                        leftMinHeap.add(costs[left]);
                    }
                    left += 1;
                } else {
                    res += rightMinHeap.poll();
                    if (right >= left) {
                        rightMinHeap.add(costs[right]);
                    }
                    right -= 1;
                }
                k -= 1;
            }
        }
        if (k > 0 && leftMinHeap.isEmpty()) {
            while (k > 0) {
                res += rightMinHeap.poll();
                k -= 1;
            }
        }
        if (k > 0 && rightMinHeap.isEmpty()) {
            while (k > 0) {
                res += leftMinHeap.poll();
                k -= 1;
            }
        }
        return res;
    }
}