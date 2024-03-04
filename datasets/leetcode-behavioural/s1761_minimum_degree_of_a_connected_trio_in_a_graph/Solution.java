package g1701_1800.s1761_minimum_degree_of_a_connected_trio_in_a_graph;

// #Hard #Graph #2022_04_30_Time_33_ms_(89.17%)_Space_73_MB_(55.41%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` is an integer representing the number of nodes in the graph.*);
//@ ensures(*The input `edges` is a 2D array where each element `edges[i]` is an array of size 2 representing an undirected edge between two nodes.*);
//@ ensures(*The elements in `edges` are valid node indices, i.e., `1 <= edges[i][0], edges[i][1] <= n`.*);
//@ ensures(*The graph represented by `n` and `edges` is undirected and connected.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the minimum degree of a connected trio in the graph.*);
//@ ensures(*If the graph has no connected trios, the method returns -1.*);
    public int minTrioDegree(int n, int[][] edges) {
        int[] degrees = new int[n + 1];
        int[][] adjMatrix = new int[n + 1][n + 1];
        for (int[] edge : edges) {
            adjMatrix[edge[0]][edge[1]] = 1;
            adjMatrix[edge[1]][edge[0]] = 1;
            degrees[edge[0]]++;
            degrees[edge[1]]++;
        }
        int minTrios = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (adjMatrix[i][j] == 0) {
                    continue;
                }
                for (int k = j + 1; k <= n; k++) {
                    if (adjMatrix[j][k] == 0 || adjMatrix[i][k] == 0) {
                        continue;
                    }
                    int trioDegree = degrees[i] + degrees[j] + degrees[k] - 6;
                    minTrios = Math.min(minTrios, trioDegree);
                }
            }
        }
        return minTrios == Integer.MAX_VALUE ? -1 : minTrios;
    }
}