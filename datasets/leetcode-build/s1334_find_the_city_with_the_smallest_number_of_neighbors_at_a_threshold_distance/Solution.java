package g1301_1400.s1334_find_the_city_with_the_smallest_number_of_neighbors_at_a_threshold_distance;

// #Medium #Dynamic_Programming #Graph #Shortest_Path
// #2022_03_19_Time_21_ms_(49.75%)_Space_47_MB_(36.59%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
	//@ requires(*The input integer `n` represents the number of cities and must be greater than or equal to 2.*);
	//@ requires(*The input array `edges` represents the bidirectional and weighted edges between cities and must have a length greater than or equal to 1.*);
	//@ requires(*Each element `edges[i]` in the `edges` array is an array of length 3, where `edges[i][0]` represents the starting city, `edges[i][1]` represents the destination city, and `edges[i][2]` represents the weight of the edge. The starting city and destination city must be integers between 0 and `n-1`.*);
	//@ requires(*The input integer `maxDist` represents the maximum distance threshold and must be greater than or equal to 1.*);
	//@ ensures(*The method returns an integer representing the city with the smallest number of reachable cities within the distance threshold. If there are multiple cities with the same smallest number, the method returns the city with the greatest number.*);
	//@ ensures(*The method calculates the distance of a path connecting cities `i` and `j` by summing the weights of the edges along that path.*);
	//@ ensures(*The method determines the neighboring cities at a distance threshold for each city and returns the city with the smallest number of neighboring cities within the distance threshold.*);
    public int findTheCity(int n, int[][] edges, int maxDist) {
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }
        return fllowdWarshall(graph, n, maxDist);
    }

    private int fllowdWarshall(int[][] graph, int n, int maxDist) {
        int inf = 10001;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && graph[i][j] == 0) {
                    dist[i][j] = inf;
                } else {
                    dist[i][j] = graph[i][j];
                }
            }
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return getList(dist, n, maxDist);
    }

    private int getList(int[][] dist, int n, int maxDist) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!map.containsKey(i)) {
                    map.put(i, new ArrayList<>());
                    if (dist[i][j] <= maxDist && i != j) {
                        map.get(i).add(j);
                    }
                } else if (map.containsKey(i) && dist[i][j] <= maxDist && i != j) {
                    map.get(i).add(j);
                }
            }
        }
        int numOfEle = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (numOfEle >= map.get(i).size()) {
                numOfEle = Math.min(numOfEle, map.get(i).size());
                ans = i;
            }
        }
        return ans;
    }
}