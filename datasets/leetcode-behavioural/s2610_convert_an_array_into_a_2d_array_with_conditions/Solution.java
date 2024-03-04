package g2601_2700.s2610_convert_an_array_into_a_2d_array_with_conditions;

// #Medium #Array #Hash_Table #2023_08_30_Time_2_ms_(97.24%)_Space_43.9_MB_(80.58%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to - The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are distinct.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is a 2D array.*);
//@ ensures(*The 2D array contains only the elements of the input array `nums`.*);
//@ ensures(*Each row in the 2D array contains distinct integers.*);
//@ ensures(*The number of rows in the 2D array is minimal.*);
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
            int idx = map.get(x);
            if (res.size() < idx) {
                res.add(new ArrayList<>());
            }
            res.get(idx - 1).add(x);
        }
        return res;
    }
}