package g2101_2200.s2150_find_all_lonely_numbers_in_the_array;

// #Medium #Array #Hash_Table #Counting #2022_05_31_Time_93_ms_(70.66%)_Space_63.5_MB_(80.52%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are non-negative integers.*);
	//@ ensures(*The returned list contains only lonely numbers from the input array `nums`.*);
	//@ ensures(*The returned list may be in any order.*);
	//@ ensures(*The returned list may contain duplicate lonely numbers.*);
	//@ ensures(*The returned list may contain lonely numbers in any order.*);
	//@ ensures(*The returned list may contain lonely numbers that are adjacent to each other in the input array `nums`.*);
	//@ ensures(*The returned list may contain lonely numbers that are not adjacent to each other in the input array `nums`.*);
    public List<Integer> findLonely(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int i : nums) {
            m.put(i, m.getOrDefault(i, 0) + 1);
        }
        for (int i : nums) {
            if (m.get(i) == 1 && !m.containsKey(i - 1) && !m.containsKey(i + 1)) {
                ans.add(i);
            }
        }
        return ans;
    }
}