package g0501_0600.s0503_next_greater_element_ii;

// #Medium #Array #Stack #Monotonic_Stack #Programming_Skills_II_Day_10
// #2022_07_24_Time_7_ms_(97.03%)_Space_44.3_MB_(85.99%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer array result has the same length as the integer array parameter `nums`.*);
//@ ensures(*For each element in the integer array parameter `nums`, the integer result is the next greater number in the circular array. If there is no greater number, the result is -1.*);
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