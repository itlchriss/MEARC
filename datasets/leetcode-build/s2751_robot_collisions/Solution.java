package g2701_2800.s2751_robot_collisions;

// #Hard #Array #Sorting #Stack #Simulation #2023_09_24_Time_29_ms_(98.29%)_Space_59.5_MB_(66.29%)

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;

public class Solution {
	//@ requires(*The length of the `positions` array is equal to the length of the `healths` array and the length of the `directions` string.*);
	//@ requires(*The `positions` array contains unique integers.*);
	//@ requires(*The `directions` string contains only 'L' or 'R' characters.*);
	//@ requires(*The values in the `positions` array and the `healths` array are positive integers.*);
	//@ requires(*The values in the `positions` array and the `healths` array are less than or equal to 10^9.*);
	//@ ensures(*The returned list contains the health of the remaining robots in the same order as they were given in the input.*);
	//@ ensures(*If there are no survivors, the returned list is empty.*);
	//@ ensures(*The health of a robot decreases by one if it collides with another robot and the other robot has a higher health.*);
	//@ ensures(*If two robots collide and have the same health, both robots are removed from the line.*);
	//@ ensures(*The surviving robot continues in the same direction it was going.*);
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        List<Integer> rindex = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rindex.add(i);
        }
        rindex.sort(Comparator.comparingInt(a -> positions[a]));
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i : rindex) {
            if (directions.charAt(i) == 'R') {
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty() && healths[i] > 0) {
                if (healths[stack.peek()] < healths[i]) {
                    healths[stack.pop()] = 0;
                    healths[i] -= 1;
                } else if (healths[stack.peek()] > healths[i]) {
                    healths[stack.peek()] -= 1;
                    healths[i] = 0;
                } else {
                    healths[stack.pop()] = 0;
                    healths[i] = 0;
                }
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int h : healths) {
            if (h > 0) {
                ans.add(h);
            }
        }
        return ans;
    }
}