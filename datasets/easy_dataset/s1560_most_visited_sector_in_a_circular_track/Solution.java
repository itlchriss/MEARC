package g1501_1600.s1560_most_visited_sector_in_a_circular_track;

// #Easy #Array #Simulation #2022_04_11_Time_1_ms_(88.89%)_Space_43.6_MB_(62.96%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `n` must be an integer greater than or equal to - The input `rounds` must be an integer array with a length of `m + 1`, where `m` is an integer greater than or equal to - Each element in the `rounds` array must be an integer between 1 and `n`, inclusive.*);
//@ ensures(*The elements in the `rounds` array must be in ascending order.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method should return a List of integers representing the most visited sectors.*);
//@ ensures(*The List should be sorted in ascending order.*);
    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> res = new ArrayList<>();
        int start = rounds[0];
        int end = rounds[rounds.length - 1];
        int[] ans = new int[n + 1];
        while (start != end) {
            ans[start]++;
            start++;
            if (start > n) {
                start = 1;
            }
        }
        ans[end]++;
        for (int i = 1; i <= n; i++) {
            if (ans[i] != 0) {
                res.add(i);
            }
        }
        return res;
    }
}