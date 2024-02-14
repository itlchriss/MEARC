package g0501_0600.s0554_brick_wall;

// #Medium #Array #Hash_Table #2022_08_03_Time_12_ms_(87.69%)_Space_55.8_MB_(7.95%)

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*The input `wall` is a non-empty 2D list.*);
	//@ requires(*The number of rows in `wall` is greater than or equal to 1 and less than or equal to 10,- The number of columns in each row of `wall` is greater than or equal to 1 and less than or equal to 10,- The sum of the lengths of all rows in `wall` is greater than or equal to 1 and less than or equal to 20,- The sum of the values in each row of `wall` is the same for all rows.*);
	//@ ensures(*The method returns an integer representing the minimum number of crossed bricks after drawing a vertical line.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> row : wall) {
            int sum = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }
        int result = wall.size();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result = Math.min(result, wall.size() - entry.getValue());
        }
        return result;
    }
}