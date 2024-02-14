package g0601_0700.s0682_baseball_game;

// #Easy #Array #Stack #Simulation #2022_03_22_Time_4_ms_(57.60%)_Space_42.7_MB_(24.43%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	//@ requires(*The input `ops` is not null.*);
	//@ requires(*The length of `ops` is greater than or equal to 1.*);
	//@ requires(*Each element in `ops` is either an integer, "+", "D", or "C".*);
	//@ requires(*For each "+" operation, there are at least two previous scores on the record.*);
	//@ requires(*For each "D" and "C" operation, there is at least one previous score on the record.*);
	//@ ensures(*The method returns an integer representing the sum of all the scores on the record.*);
    public int calPoints(String[] ops) {
        List<Integer> resultList = new ArrayList<>(ops.length);
        for (String op : ops) {
            int size = resultList.size();
            if ("C".equals(op)) {
                resultList.remove(size - 1);
            } else if ("D".equals(op)) {
                resultList.add(resultList.get(size - 1) * 2);
            } else if ("+".equals(op)) {
                resultList.add(resultList.get(size - 1) + resultList.get(size - 2));
            } else {
                resultList.add(Integer.parseInt(op));
            }
        }
        return resultList.stream().reduce(0, Integer::sum);
    }
}