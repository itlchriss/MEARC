package g2201_2300.s2211_count_collisions_on_a_road;

// #Medium #String #Stack #2022_06_12_Time_113_ms_(45.96%)_Space_54.3_MB_(57.28%)

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `directions` is not null.*);
//@ ensures(*The length of the input string `directions` is greater than or equal to 1.*);
//@ ensures(*Each character in the input string `directions` is either 'L', 'R', or 'S'.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the total number of collisions that will happen on the road.*);
//@ ensures(**);
//@ ensures(*Additional behavioural requirements:*);
//@ ensures(*The method should handle the case where there are no collisions and return 0.*);
//@ ensures(*The method should correctly calculate the number of collisions when two cars moving in opposite directions collide with each other.*);
//@ ensures(*The method should correctly calculate the number of collisions when a moving car collides with a stationary car.*);
//@ ensures(*After a collision, the cars involved should stay at the point where they collided and cannot change their state or direction of motion.*);
    public int countCollisions(String directions) {
        if (directions == null || directions.length() == 1) {
            return 0;
        }
        Deque<Character> stack = new ArrayDeque<>();
        char[] direction = directions.toCharArray();
        char prevc = '0';
        int collision = 0;
        for (int i = 0; i < direction.length; i++) {
            if (direction[i] == 'R') {
                stack.push(direction[i]);
            } else {
                if ((direction[i] == 'S' && prevc == 'R')) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                    collision += 1;
                    direction[i] = 'S';
                    while (!stack.isEmpty()) {
                        collision++;
                        stack.pop();
                    }
                }
                if ((direction[i] == 'L' && prevc == 'R')) {
                    stack.pop();
                    collision += 2;
                    direction[i] = 'S';
                    while (!stack.isEmpty()) {
                        collision++;
                        stack.pop();
                    }
                }
                if (direction[i] == 'L' && prevc == 'S') {
                    collision++;
                    direction[i] = 'S';
                }
            }
            prevc = direction[i];
        }
        return collision;
    }
}