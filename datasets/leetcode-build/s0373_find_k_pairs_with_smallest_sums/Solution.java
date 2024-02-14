package g0301_0400.s0373_find_k_pairs_with_smallest_sums;

// #Medium #Array #Heap_Priority_Queue #2022_07_12_Time_59_ms_(46.79%)_Space_120.7_MB_(83.25%)

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    private static class Node {
        long sum;
        List<Integer> al;
        int index;

        Node(int index, int num1, int num2) {
            this.sum = (long) num1 + (long) num2;
            this.al = new ArrayList<>();
            this.al.add(num1);
            this.al.add(num2);
            this.index = index;
        }
    }
	//@ requires(*The input arrays `nums1` and `nums2` are not null.*);
	//@ requires(*The input arrays `nums1` and `nums2` are sorted in ascending order.*);
	//@ requires(*The input integer `k` is greater than 0.*);
	//@ ensures(*The returned list `kSmallestPairs` is not null.*);
	//@ ensures(*The size of the returned list `kSmallestPairs` is equal to `k`.*);
	//@ ensures(*Each element in the returned list `kSmallestPairs` is a list of two integers.*);
	//@ ensures(*The pairs in the returned list `kSmallestPairs` have the smallest sums among all possible pairs.*);
	//@ ensures(*The pairs in the returned list `kSmallestPairs` are sorted in ascending order based on their sums.*);

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b) -> a.sum < b.sum ? -1 : 1);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums1.length && i < k; i++) {
            queue.add(new Node(0, nums1[i], nums2[0]));
        }
        for (int i = 1; i <= k && !queue.isEmpty(); i++) {
            Node cur = queue.poll();
            res.add(cur.al);
            int next = cur.index;
            int lastNum1 = cur.al.get(0);
            if (next + 1 < nums2.length) {
                queue.add(new Node(next + 1, lastNum1, nums2[next + 1]));
            }
        }
        return res;
    }
}