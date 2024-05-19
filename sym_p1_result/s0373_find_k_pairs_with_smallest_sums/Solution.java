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
//@ requires(*The length of the integer array parameter `nums1` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*The length of the integer array parameter `nums2` is less than or equal to 100000 and is greater than or equal to 1.*);
//@ requires(*All the values in the integer array parameter `nums1` are less than or equal to 1000000000 and are greater than or equal to -1000000000.*);
//@ requires(*All the values in the integer array parameter `nums2` are less than or equal to 1000000000 and are greater than or equal to -1000000000.*);
//@ requires(*The integer parameter `k` is less than or equal to 1000 and is greater than or equal to 1.*);
//@ ensures(*The length of the list result is less than or equal to the integer parameter `k`.*);
//@ ensures(*Each element in the list result is a list of two integers representing a pair `(u, v)`.*);
//@ ensures(*The pairs in the list result are the `k` pairs with the smallest sums.*);
//@ ensures(*The pairs in the list result are formed by taking one element from the integer array parameter `nums1` and one element from the integer array parameter `nums2`.*);
//@ ensures(*The pairs in the list result are sorted in ascending order based on their sums.*);

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