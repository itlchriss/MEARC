package g1601_1700.s1615_maximal_network_rank;

// #Medium #Graph #Graph_Theory_I_Day_14_Graph_Theory
// #2022_04_13_Time_3_ms_(97.34%)_Space_42.8_MB_(92.41%)

public class Solution {
	//@ requires(*The input integer `n` represents the number of cities in the infrastructure.*);
	//@ requires(*The input array `roads` represents the bidirectional roads connecting the cities.*);
	//@ requires(*Each element `roads[i]` is an array of size 2, representing a road between cities `a` and `b`.*);
	//@ requires(*The values of `a` and `b` are integers between 0 and `n-1`.*);
	//@ requires(*Each pair of cities has at most one road connecting them.*);
	//@ ensures(*The method returns an integer representing the maximal network rank of the entire infrastructure.*);
	//@ ensures(*The network rank of two different cities is defined as the total number of directly connected roads to either city.*);
	//@ ensures(*If a road is directly connected to both cities, it is only counted once.*);
	//@ ensures(*The maximal network rank is the maximum network rank of all pairs of different cities.*);
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] degrees = new int[n];
        boolean[] connected = new boolean[40_000];
        for (int[] r : roads) {
            degrees[r[0]]++;
            degrees[r[1]]++;
            connected[(r[0] + 101) * (r[1] + 101) - 1] = true;
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (connected[(i + 101) * (j + 101) - 1]) {
                    max = Math.max(max, degrees[i] + degrees[j] - 1);
                } else {
                    max = Math.max(max, degrees[i] + degrees[j]);
                }
            }
        }
        return max;
    }
}