package g0801_0900.s0818_race_car;

// #Hard #Dynamic_Programming #2022_03_23_Time_9_ms_(82.90%)_Space_44.2_MB_(70.46%)

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `target` is a positive integer greater than or equal to 1 and less than or equal to 10^4.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the length of the shortest sequence of instructions to reach the target position.*);
//@ ensures(*The position of the car is updated according to the sequence of instructions.*);
//@ ensures(*The speed of the car is updated according to the sequence of instructions.*);
//@ ensures(*The position of the car is non-negative and can be negative.*);
//@ ensures(*The speed of the car is positive or negative.*);
//@ ensures(*The speed of the car is multiplied by 2 when an instruction 'A' is received.*);
//@ ensures(*The speed of the car is set to -1 if the current speed is positive and an instruction 'R' is received.*);
//@ ensures(*The speed of the car is set to 1 if the current speed is negative and an instruction 'R' is received.*);
    public int racecar(int target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 1, 0});
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            if (arr[0] == target) {
                return arr[2];
            }
            queue.add(new int[] {arr[0] + arr[1], 2 * arr[1], 1 + arr[2]});
            if ((arr[0] + arr[1]) > target && arr[1] > 0) {
                queue.add(new int[] {arr[0], -1, 1 + arr[2]});
            }
            if ((arr[0]) + arr[1] < target && (arr[1] < 0)) {
                queue.add(new int[] {arr[0], 1, 1 + arr[2]});
            }
        }
        return -1;
    }
}