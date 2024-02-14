package g2201_2300.s2233_maximum_product_after_k_increments;

// #Medium #Array #Greedy #Heap_Priority_Queue
// #2022_06_14_Time_364_ms_(75.06%)_Space_52.3_MB_(95.74%)

import java.util.PriorityQueue;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to - The input integer `k` is greater than or equal to - The elements in the input array `nums` are non-negative integers.*);
	//@ requires(*The elements in the input array `nums` are less than or equal to 10^*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*The returned integer value is the maximum product of the elements in the input array `nums` after performing at most `k` operations.*);
	//@ ensures(*The returned integer value is modulo 10^9 + 7.*);
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.add(num);
        }
        while (k-- > 0) {
            pq.add(pq.poll() + 1);
        }
        long ans = 1;
        int mod = 1000000007;
        while (!pq.isEmpty()) {
            ans = (ans * pq.poll()) % mod;
        }
        return (int) ans;
    }
}