package g2601_2700.s2662_minimum_cost_of_a_path_with_special_roads;

// #Medium #Array #Heap_Priority_Queue #Graph #Shortest_Path
// #2023_09_09_Time_3_ms_(100.00%)_Space_43.2_MB_(92.86%)

public class Solution {
    private static int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
	//@ requires(*The length of the `start` array must be - The length of the `target` array must be - The length of the `specialRoads` array must be between 1 and - The length of each subarray in the `specialRoads` array must be - The values of `startX` and `targetX` must be between 1 and 10^- The values of `startY` and `targetY` must be between 1 and 10^- The values of `x1i`, `x2i`, `y1i`, and `y2i` in each subarray of `specialRoads` must be between `startX` and `targetX`, and between `startY` and `targetY`.*);
	//@ requires(*The value of `costi` in each subarray of `specialRoads` must be between 1 and 10^*);
	//@ ensures(*The method returns an integer representing the minimum cost required to go from `(startX, startY)` to `(targetX, targetY)`.*);

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        int n = specialRoads.length;
        var dist = new int[n];
        int minDistIdx = 0;
        for (int i = 0; i < n; i++) {
            dist[i] =
                    dist(
                                    start[0], start[1],
                                    specialRoads[i][0], specialRoads[i][1])
                            + specialRoads[i][4];
            if (dist[minDistIdx] > dist[i]) {
                minDistIdx = i;
            }
        }
        int cost = dist(start[0], start[1], target[0], target[1]);
        while (minDistIdx != -1) {
            var cur = minDistIdx;
            minDistIdx = -1;
            cost =
                    Math.min(
                            cost,
                            dist[cur]
                                    + dist(
                                            target[0], target[1],
                                            specialRoads[cur][2], specialRoads[cur][3]));
            for (int i = 0; i < n; i++) {
                if (i == cur || dist[i] == -1) {
                    continue;
                }
                dist[i] =
                        Math.min(
                                dist[i],
                                dist[cur]
                                        + dist(
                                                specialRoads[cur][2], specialRoads[cur][3],
                                                specialRoads[i][0], specialRoads[i][1])
                                        + specialRoads[i][4]);
                if (minDistIdx == -1 || dist[minDistIdx] > dist[i]) {
                    minDistIdx = i;
                }
            }
            dist[cur] = -1;
        }
        return cost;
    }
}