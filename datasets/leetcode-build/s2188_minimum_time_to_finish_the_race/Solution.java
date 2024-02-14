package g2101_2200.s2188_minimum_time_to_finish_the_race;

// #Hard #Array #Dynamic_Programming #2022_06_06_Time_15_ms_(93.69%)_Space_151_MB_(19.91%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The input array `tires` is not null.*);
	//@ requires(*The length of `tires` is greater than or equal to 1 and less than or equal to 10^5.*);
	//@ requires(*Each element in `tires` is an array of length 2.*);
	//@ requires(*The first element of each subarray in `tires` is greater than or equal to 1 and less than or equal to 10^5.*);
	//@ requires(*The second element of each subarray in `tires` is greater than or equal to 2 and less than or equal to 10^5.*);
	//@ requires(*The input integer `changeTime` is greater than or equal to 1 and less than or equal to 10^5.*);
	//@ requires(*The input integer `numLaps` is greater than or equal to 1 and less than or equal to 1000.*);
	//@ ensures(*The method returns an integer representing the minimum time to finish the race.*);
	//@ ensures(*The returned time is greater than or equal to 0.*);
    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        int minf = Integer.MAX_VALUE;
        // find the minimum of f, to deal with special case and stronger constraints later.
        for (int[] tire : tires) {
            minf = Math.min(minf, tire[0]);
        }
        // if min-f >= changeTime, we can return early
        if (minf >= changeTime) {
            return minf * numLaps + changeTime * (numLaps - 1);
        }
        // shortest[i] record shortest time that one single tire is worth to go the i-th laps
        // worth to go means the i-th lap time is shorter than changeTime + f
        int[] shortest = new int[numLaps + 1];
        Arrays.fill(shortest, Integer.MAX_VALUE);
        int len = 0;
        // traverse all tires, and update the shortest[i]
        // this shortest time is available from [1, len] in the array
        // len is updated in the traverse
        for (int[] tire : tires) {
            int f = tire[0];
            int r = tire[1];
            // index start from 1 to be consistent with numLaps
            int index = 1;
            int t = f;
            int sum = t;
            // use changeTime + minf here, which is a strong constraints than changeTime + f
            while (t <= changeTime + minf && index <= numLaps) {
                shortest[index] = Math.min(shortest[index], sum);
                t = t * r;
                sum += t;
                index++;
            }
            len = Math.max(len, index - 1);
        }
        for (int i = 2; i <= numLaps; i++) {
            // for j > Math.min(i/2, len), it's simply recombination of the values of shortest
            // [1:len]
            // it's ok to go furthur for the loop, just repeat the Math.min computation
            for (int j = 1; j <= Math.min(i / 2, len); j++) {
                shortest[i] = Math.min(shortest[i], shortest[j] + shortest[i - j] + changeTime);
            }
        }
        return shortest[numLaps];
    }
}