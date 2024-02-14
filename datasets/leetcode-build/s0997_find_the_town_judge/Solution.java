package g0901_1000.s0997_find_the_town_judge;

// #Easy #Array #Hash_Table #Graph #Data_Structure_II_Day_19_Graph
// #Graph_Theory_I_Day_13_Graph_Theory #2022_03_31_Time_3_ms_(80.64%)_Space_49.9_MB_(88.09%)

public class Solution {
	//@ requires(*The input `n` is a positive integer representing the number of people in the town.*);
	//@ requires(*The input `trust` is a 2D array where each element `trust[i]` is an array of length 2 representing a trust relationship between two people.*);
	//@ requires(*The elements in `trust` are unique pairs.*);
	//@ requires(*The elements in `trust` are integers between 1 and `n`, inclusive.*);
	//@ requires(*The length of `trust` is between 0 and 10^4, inclusive.*);
	//@ ensures(*If there is a town judge, the method returns the label of the town judge as an integer.*);
	//@ ensures(*If there is no town judge, the method returns -1 as an integer.*);
    public int findJudge(int n, int[][] trust) {
        if (trust.length < n - 1) {
            return -1;
        }
        int[] trustScores = new int[n];
        for (int[] t : trust) {
            trustScores[t[1] - 1]++;
            trustScores[t[0] - 1]--;
        }
        for (int i = 0; i < n; i++) {
            if (trustScores[i] == n - 1) {
                return i + 1;
            }
        }
        return -1;
    }
}