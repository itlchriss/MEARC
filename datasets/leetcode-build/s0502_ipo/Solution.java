package g0501_0600.s0502_ipo;

// #Hard #Array #Sorting #Greedy #Heap_Priority_Queue
// #2022_07_24_Time_51_ms_(89.62%)_Space_101.7_MB_(47.03%)

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
	//@ requires(*1. The value of `k` must be greater than or equal to 1.*);
	//@ requires(*2. The value of `w` must be greater than or equal to 0.*);
	//@ requires(*3. The length of `profits` and `capital` arrays must be the same.*);
	//@ requires(*4. The length of `profits` and `capital` arrays must be greater than or equal to 1.*);
	//@ requires(*5. The values in the `profits` array must be non-negative.*);
	//@ requires(*6. The values in the `capital` array must be non-negative.*);
	//@ ensures(*1. The returned value must be the final maximized capital.*);
	//@ ensures(*2. The returned value must be a 32-bit signed integer.*);
	//@ ensures(*3. The final maximized capital must be the sum of the initial capital (`w`) and the profits obtained from finishing the selected projects.*);
	//@ ensures(*4. The number of distinct projects finished must be at most `k`.*);
	//@ ensures(*5. The selected projects must have a minimum capital requirement that is less than or equal to the available capital (`w`).*);
	//@ ensures(*6. The selected projects must have the maximum profit among all projects that satisfy the capital requirement.*);
	//@ ensures(*7. If there are multiple projects with the same maximum profit, the project with the lowest index should be selected.*);
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<int[]> minCapital =
                new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[1]));
        PriorityQueue<int[]> maxProfit = new PriorityQueue<>((int[] a, int[] b) -> b[0] - a[0]);
        for (int i = 0; i < profits.length; i++) {
            if (w >= capital[i]) {
                maxProfit.offer(new int[] {profits[i], capital[i]});
            } else {
                minCapital.offer(new int[] {profits[i], capital[i]});
            }
        }
        int count = 0;
        while (count < k && !maxProfit.isEmpty()) {
            int[] temp = maxProfit.poll();
            w += temp[0];
            count += 1;
            while (!minCapital.isEmpty() && minCapital.peek()[1] <= w) {
                maxProfit.offer(minCapital.poll());
            }
        }
        return w;
    }
}