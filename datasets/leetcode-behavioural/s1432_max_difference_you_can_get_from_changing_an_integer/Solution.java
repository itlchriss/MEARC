package g1401_1500.s1432_max_difference_you_can_get_from_changing_an_integer;

// #Medium #Math #Greedy #2022_03_28_Time_1_ms_(97.22%)_Space_39.4_MB_(80.56%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `num` is a positive integer greater than or equal to 1 and less than or equal to 10^8.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum difference between `a` and `b`.*);
//@ ensures(*The new integer `a` is obtained by replacing all occurrences of digit `x` in the decimal representation of `num` with digit `y` for the first time.*);
//@ ensures(*The new integer `b` is obtained by replacing all occurrences of digit `x` in the decimal representation of `num` with digit `y` for the second time.*);
//@ ensures(*The new integer `a` does not have any leading zeros.*);
//@ ensures(*The new integer `b` does not have any leading zeros.*);
//@ ensures(*The new integer `a` is not equal to 0.*);
//@ ensures(*The new integer `b` is not equal to 0.*);
    public int maxDiff(int num) {
        Deque<Integer> stack = new ArrayDeque<>();
        int xMax = 9;
        int yMax = 9;
        int xMin = 0;
        int yMin = 0;
        int min = 0;
        int max = 0;
        boolean areDigitsUnique = true;
        while (num != 0) {
            if (!stack.isEmpty() && num % 10 != stack.peek()) {
                areDigitsUnique = false;
            }
            stack.push(num % 10);
            num /= 10;
            if (stack.peek() != 9) {
                xMax = stack.peek();
            }
            if (stack.peek() > 1) {
                xMin = stack.peek();
            }
        }
        if (areDigitsUnique || stack.peek() == xMin) {
            // Handles no leading zeros/non zero constraints.
            yMin = 1;
        }
        while (!stack.isEmpty()) {
            min = min * 10 + (stack.peek() == xMin ? yMin : stack.peek());
            max = max * 10 + (stack.peek() == xMax ? yMax : stack.peek());
            stack.pop();
        }
        return max - min;
    }
}