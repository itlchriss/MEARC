package g2401_2500.s2454_next_greater_element_iv;

// #Hard #Array #Sorting #Binary_Search #Stack #Heap_Priority_Queue #Monotonic_Stack
// #2022_12_15_Time_29_ms_(95.84%)_Space_57.4_MB_(93.32%)

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array `answer` is not null.*);
//@ ensures(*The length of the output array `answer` is equal to the length of the input array `nums`.*);
//@ ensures(*The elements in the output array `answer` are integers.*);
//@ ensures(*For each index `i` in the input array `nums`, if there exists a second greater integer, the corresponding element in the output array `answer` at index `i` is the second greater integer. Otherwise, the corresponding element in the output array `answer` at index `i` is -1.*);
    public int[] secondGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        Deque<Integer> tmp = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack2.isEmpty() && nums[i] > nums[stack2.peek()]) {
                res[stack2.pop()] = nums[i];
            }
            while (!stack1.isEmpty() && nums[i] > nums[stack1.peek()]) {
                tmp.push(stack1.pop());
            }
            while (!tmp.isEmpty()) {
                stack2.push(tmp.pop());
            }
            stack1.push(i);
        }
        return res;
    }
}