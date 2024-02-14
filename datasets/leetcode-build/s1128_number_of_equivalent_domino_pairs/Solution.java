package g1101_1200.s1128_number_of_equivalent_domino_pairs;

// #Easy #Array #Hash_Table #Counting #2023_06_01_Time_15_ms_(51.49%)_Space_55_MB_(20.00%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input `dominoes` is a non-empty list of arrays, where each array has a length of 2.*);
	//@ requires(*The elements in each array of `dominoes` are integers between 1 and 9.*);
	//@ ensures(*The method returns an integer representing the number of pairs `(i, j)` where `0 <= i < j < dominoes.length` and `dominoes[i]` is equivalent to `dominoes[j]`.*);
	//@ ensures(*The method correctly determines the equivalence of two dominoes based on the given conditions: either (`a == c` and `b == d`), or (`a == d` and `b == c`).*);
	//@ ensures(*The method handles the case where there are no equivalent domino pairs and returns 0.*);
	//@ ensures(*The method handles the case where there is only one equivalent domino pair and returns 1.*);
	//@ ensures(*The method handles the case where there are multiple equivalent domino pairs and returns the correct count.*);
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int[] dominoe : dominoes) {
            int smaller = Math.min(dominoe[0], dominoe[1]);
            int bigger = Math.max(dominoe[0], dominoe[1]);
            int key = smaller * 10 + bigger;
            count += map.getOrDefault(key, 0);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return count;
    }
}