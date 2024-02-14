package g0201_0300.s0239_sliding_window_maximum;

// #Hard #Top_100_Liked_Questions #Top_Interview_Questions #Array #Heap_Priority_Queue
// #Sliding_Window #Queue #Monotonic_Queue #Udemy_Arrays #Big_O_Time_O(n*k)_Space_O(n+k)
// #2022_07_04_Time_58_ms_(52.28%)_Space_145_MB_(50.60%)

import java.util.LinkedList;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` has at least `k` elements.*);
	//@ requires(*The value of `k` is greater than or equal to 1.*);
	//@ ensures(*The output array `result` is not null.*);
	//@ ensures(*The length of the output array `result` is equal to `nums.length - k + 1`.*);
	//@ ensures(*The elements in the output array `result` are the maximum values in each sliding window of size `k`.*);
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int x = 0;
        LinkedList<Integer> dq = new LinkedList<>();
        int i = 0;
        int j = 0;
        while (j < nums.length) {
            while (!dq.isEmpty() && dq.peekLast() < nums[j]) {
                dq.pollLast();
            }
            dq.addLast(nums[j]);
            if (j - i + 1 == k) {
                res[x] = dq.peekFirst();
                ++x;
                if (dq.peekFirst() == nums[i]) {
                    dq.pollFirst();
                }
                ++i;
            }
            ++j;
        }
        return res;
    }
}