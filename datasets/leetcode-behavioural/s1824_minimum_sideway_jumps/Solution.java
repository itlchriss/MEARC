package g1801_1900.s1824_minimum_sideway_jumps;

// #Medium #Array #Dynamic_Programming #Greedy
// #2022_05_06_Time_17_ms_(96.34%)_Space_217.7_MB_(56.10%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `obstacles` is not null.*);
//@ ensures(*The length of the input array `obstacles` is equal to `n + 1`.*);
//@ ensures(*The value of `n` is greater than or equal to - The values in the input array `obstacles` range from 0 to - The first and last elements of the input array `obstacles` are both *);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum number of side jumps the frog needs to reach any lane at point `n` starting from lane 2 at point 0.*);
    public int minSideJumps(int[] obstacles) {
        int sideJumps = 0;
        int currLane = 2;
        int i = 0;
        while (i < obstacles.length - 1) {
            if (obstacles[i + 1] == currLane) {
                if (obstacles[i] != 0) {
                    currLane = getNextLane(obstacles[i], obstacles[i + 1]);
                } else {
                    int j = i + 2;
                    while (j < obstacles.length
                            && (obstacles[j] == 0 || obstacles[j] == obstacles[i + 1])) {
                        j++;
                    }
                    if (j < obstacles.length) {
                        currLane = getNextLane(obstacles[i + 1], obstacles[j]);
                    } else {
                        i = obstacles.length - 1;
                    }
                }
                sideJumps++;
            }
            i++;
        }
        return sideJumps;
    }

    private int getNextLane(int nextObstacle, int nextNextObstacle) {
        if ((nextObstacle == 2 && nextNextObstacle == 3)
                || (nextObstacle == 3 && nextNextObstacle == 2)) {
            return 1;
        }
        if ((nextObstacle == 1 && nextNextObstacle == 3)
                || (nextObstacle == 3 && nextNextObstacle == 1)) {
            return 2;
        } else {
            return 3;
        }
    }
}