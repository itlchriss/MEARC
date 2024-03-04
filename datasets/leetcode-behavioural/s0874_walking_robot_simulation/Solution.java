package g0801_0900.s0874_walking_robot_simulation;

// #Medium #Array #Simulation #2022_03_28_Time_16_ms_(93.75%)_Space_50.9_MB_(75.89%)

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The `commands` array is not null.*);
//@ ensures(*The `obstacles` array is not null.*);
//@ ensures(*The length of the `commands` array is between 1 and 10,000.*);
//@ ensures(*Each element in the `commands` array is either -2, -1, or an integer between 1 and 9.*);
//@ ensures(*The length of the `obstacles` array is between 0 and 10,000.*);
//@ ensures(*Each element in the `obstacles` array is a pair of integers between -30,000 and 30,000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the maximum Euclidean distance that the robot ever gets from the origin squared.*);
//@ ensures(*The robot's final position is within the bounds of the XY-plane.*);
//@ ensures(*If the robot encounters an obstacle, it stays in its current location and moves on to the next command.*);
    public int robotSim(int[] commands, int[][] obstacles) {
        // 0=right, 1=up, 2=left, 3=down
        int direction = 1;
        int x = 0;
        int y = 0;
        int maxDis = 0;
        Map<Integer, TreeSet<Integer>> xMap = new HashMap<>(obstacles.length);
        Map<Integer, TreeSet<Integer>> yMap = new HashMap<>(obstacles.length);
        for (int[] xy : obstacles) {
            xMap.computeIfAbsent(xy[0], k -> new TreeSet<>()).add(xy[1]);
            yMap.computeIfAbsent(xy[1], k -> new TreeSet<>()).add(xy[0]);
        }
        for (int cmd : commands) {
            if (cmd == -1) {
                direction += 3;
                direction %= 4;
            } else if (cmd == -2) {
                direction += 1;
                direction %= 4;
            } else {
                Integer next = null;
                switch (direction) {
                    case 0:
                        x = getXPlusOne(x, y, yMap, cmd, next);
                        break;
                    case 1:
                        y = getXPlusOne(y, x, xMap, cmd, next);
                        break;
                    case 2:
                        x = getXMinusOne(x, y, yMap, cmd, next);
                        break;
                    case 3:
                        y = getXMinusOne(y, x, xMap, cmd, next);
                        break;
                    default:
                        // error
                        break;
                }
                maxDis = Math.max(maxDis, x * x + y * y);
            }
        }
        return maxDis;
    }

    private int getXMinusOne(
            int x, int y, Map<Integer, TreeSet<Integer>> yMap, int cmd, Integer next) {
        if (yMap.containsKey(y)) {
            next = yMap.get(y).floor(x - 1);
        }
        if (next != null) {
            x = Math.max(x - cmd, next + 1);
        } else {
            x -= cmd;
        }
        return x;
    }

    private int getXPlusOne(
            int x, int y, Map<Integer, TreeSet<Integer>> yMap, int cmd, Integer next) {
        if (yMap.containsKey(y)) {
            next = yMap.get(y).ceiling(x + 1);
        }
        if (next != null) {
            x = Math.min(x + cmd, next - 1);
        } else {
            x += cmd;
        }
        return x;
    }
}