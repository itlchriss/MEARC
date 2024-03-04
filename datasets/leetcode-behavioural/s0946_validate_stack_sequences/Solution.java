package g0901_1000.s0946_validate_stack_sequences;

// #Medium #Array #Stack #Simulation #2022_12_26_Time_1_ms_(98.95%)_Space_42_MB_(87.24%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The `pushed` array and `popped` array must have the same length.*);
//@ ensures(*All elements in the `pushed` array must be unique.*);
//@ ensures(*The `pushed` array and `popped` array must have distinct values.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*If the `pushed` and `popped` arrays represent a valid sequence of push and pop operations on an initially empty stack, the method should return `true`.*);
//@ ensures(*If the `pushed` and `popped` arrays do not represent a valid sequence of push and pop operations on an initially empty stack, the method should return `false`.*);
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        int j = 0;
        int len = pushed.length;
        while (i < len) {
            if (pushed[i] == popped[j]) {
                i++;
                j++;
            } else if (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            } else {
                stack.push(pushed[i++]);
            }
        }
        while (j < len) {
            if (!stack.isEmpty() && stack.peek() != popped[j++]) {
                return false;
            } else {
                stack.pop();
            }
        }
        return true;
    }
}