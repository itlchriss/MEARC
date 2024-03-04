package g2201_2300.s2225_find_players_with_zero_or_one_losses;

// #Medium #Array #Hash_Table #Sorting #Counting
// #2022_06_12_Time_96_ms_(87.03%)_Space_105.4_MB_(75.62%)

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `matches` is a non-empty array.*);
//@ ensures(*Each element `matches[i]` is an array of length 2.*);
//@ ensures(*The values in `matches[i]` are integers.*);
//@ ensures(*The values in `matches[i]` are positive and less than or equal to 10^5.*);
//@ ensures(*The values in `matches[i]` are unique.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a list of size 2.*);
//@ ensures(*The first element of the output list (`answer[0]`) is a list of players who have not lost any matches.*);
//@ ensures(*The second element of the output list (`answer[1]`) is a list of players who have lost exactly one match.*);
//@ ensures(*The values in `answer[0]` and `answer[1]` are in increasing order.*);
    public List<List<Integer>> findWinners(int[][] matches) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];
            map.putIfAbsent(winner, 0);
            map.put(loser, map.getOrDefault(loser, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 0) {
                list1.add(entry.getKey());
            }
            if (entry.getValue() == 1) {
                list2.add(entry.getKey());
            }
        }
        Collections.sort(list1);
        Collections.sort(list2);
        res.add(list1);
        res.add(list2);
        return res;
    }
}