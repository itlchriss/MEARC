package g0701_0800.s0735_asteroid_collision;

// #Medium #Array #Stack #Level_2_Day_18_Stack #2022_03_25_Time_2_ms_(99.59%)_Space_43.1_MB_(91.77%)

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `asteroids` is not null.*);
//@ ensures(*The length of the input array `asteroids` is at least 2.*);
//@ ensures(*Each element in the input array `asteroids` is an integer.*);
//@ ensures(*The absolute value of each element in the input array `asteroids` represents its size.*);
//@ ensures(*The sign of each element in the input array `asteroids` represents its direction (positive meaning right, negative meaning left).*);
//@ ensures(*Each asteroid moves at the same speed.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an array of integers representing the state of the asteroids after all collisions.*);
//@ ensures(*If two asteroids meet, the smaller one will explode.*);
//@ ensures(*If both asteroids are the same size, both will explode.*);
//@ ensures(*Two asteroids moving in the same direction will never meet.*);
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new LinkedList<>();
        for (int a : asteroids) {
            if (a > 0) {
                stack.addLast(a);
            } else {
                if (!stack.isEmpty() && stack.peekLast() > 0) {
                    if (stack.peekLast() == Math.abs(a)) {
                        stack.pollLast();
                    } else {
                        while (!stack.isEmpty()
                                && stack.peekLast() > 0
                                && stack.peekLast() < Math.abs(a)) {
                            stack.pollLast();
                        }
                        if (!stack.isEmpty()
                                && stack.peekLast() > 0
                                && stack.peekLast() == Math.abs(a)) {
                            stack.pollLast();
                        } else if (stack.isEmpty() || stack.peekLast() < 0) {
                            stack.addLast(a);
                        }
                    }
                } else {
                    stack.addLast(a);
                }
            }
        }
        int[] ans = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            ans[i] = stack.pollLast();
        }
        return ans;
    }
}