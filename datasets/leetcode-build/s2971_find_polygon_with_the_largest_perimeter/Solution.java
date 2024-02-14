package g2901_3000.s2971_find_polygon_with_the_largest_perimeter;

// #Medium #Array #Sorting #Greedy #Prefix_Sum
// #2024_01_16_Time_21_ms_(98.77%)_Space_60.9_MB_(34.24%)

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
	//@ requires(*The input array `nums` must not be null.*);
	//@ requires(*The length of the input array `nums` must be at least 3.*);
	//@ requires(*All elements in the input array `nums` must be positive integers.*);
	//@ ensures(*The method returns a long value representing the largest possible perimeter of a polygon that can be formed from the input array `nums`.*);
	//@ ensures(*If it is not possible to create a polygon from the input array `nums`, the method returns -1.*);
    public long largestPerimeter(int[] nums) {
        long sum = 0L;
        PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i : nums) {
            pq.add((long) i);
            sum = (sum + i);
        }
        while (pq.size() >= 3) {
            long curr = pq.poll();
            if (sum - curr > curr) {
                return sum;
            } else {
                sum = sum - curr;
            }
        }
        return -1;
    }
}