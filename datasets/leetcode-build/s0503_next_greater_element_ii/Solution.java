package g0501_0600.s0503_next_greater_element_ii;

// #Medium #Array #Stack #Monotonic_Stack #Programming_Skills_II_Day_10
// #2022_07_24_Time_7_ms_(97.03%)_Space_44.3_MB_(85.99%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	//@ requires(*1. The input array `nums` is not null.*);
	//@ requires(*2. The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*3. The elements in the input array `nums` are integers.*);
	//@ ensures(*1. The output array `result` is not null.*);
	//@ ensures(*2. The length of the output array `result` is the same as the length of the input array `nums`.*);
	//@ ensures(*3. The elements in the output array `result` are integers.*);
	//@ ensures(*4. For each element `nums[i]` in the input array, if there exists a next greater number, `result[i]` is the next greater number. Otherwise, `result[i]` is -1.*);
    public int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length * 2 - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            result[i % nums.length] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return result;
    }
}