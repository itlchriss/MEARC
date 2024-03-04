package g2501_2600.s2569_handling_sum_queries_after_update;

// #Hard #Array #Segment_Tree #2023_08_21_Time_27_ms_(94.87%)_Space_84.1_MB_(43.59%)

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The length of `nums1` and `nums2` is greater than or equal to 1.*);
//@ ensures(*The length of `nums1` is equal to the length of `nums2`.*);
//@ ensures(*The length of `queries` is greater than or equal to 1.*);
//@ ensures(*Each query in `queries` has a length of 3.*);
//@ ensures(*The value of `l` in a query of type 1 is between 0 and the length of `nums1` - 1.*);
//@ ensures(*The value of `r` in a query of type 1 is between `l` and the length of `nums1` - 1.*);
//@ ensures(*The value of `p` in a query of type 2 is between 0 and 10^6.*);
//@ ensures(*The values in `nums1` are either 0 or 1.*);
//@ ensures(*The values in `nums2` are between 0 and 10^9.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The length of the returned array is equal to the number of queries of type 3.*);
//@ ensures(*The values in the returned array are the sums of the elements in `nums2` after each query of type 3.*);
    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        Deque<Long> dq = new LinkedList<>();
        long sum = 0;
        for (int i : nums2) {
            sum += i;
        }
        Segment root = build(nums1, 0, nums1.length - 1);
        for (int[] q : queries) {
            if (1 == q[0]) {
                root.flip(q[1], q[2]);
            } else if (2 == q[0]) {
                sum += root.sum * q[1];
            } else {
                dq.add(sum);
            }
        }
        int n = dq.size();
        int i = 0;
        long[] res = new long[n];
        while (!dq.isEmpty()) {
            res[i++] = dq.poll();
        }
        return res;
    }

    private static class Segment {
        long sum;
        int f;
        int lo;
        int hi;
        Segment left;
        Segment right;

        public Segment(int l, int r) {
            lo = l;
            hi = r;
        }

        public void flip(int l, int r) {
            if (hi < l || r < lo) {
                return;
            }
            if (l <= lo && hi <= r) {
                f ^= 1;
                sum = hi - lo + 1 - sum;
                return;
            }
            if (1 == f) {
                left.flip(lo, hi);
                right.flip(lo, hi);
                f ^= 1;
            }
            left.flip(l, r);
            right.flip(l, r);
            sum = left.sum + right.sum;
        }
    }

    private Segment build(int[] nums, int l, int r) {
        if (l == r) {
            Segment node = new Segment(l, r);
            node.sum = nums[l];
            return node;
        }
        int mid = l + ((r - l) >> 1);
        Segment left = build(nums, l, mid);
        Segment right = build(nums, mid + 1, r);
        Segment root = new Segment(l, r);
        root.left = left;
        root.right = right;
        root.sum = left.sum + right.sum;
        return root;
    }
}