package g0501_0600.s0599_minimum_index_sum_of_two_lists;

// #Easy #Array #String #Hash_Table #2022_03_20_Time_7_ms_(93.97%)_Space_42.8_MB_(89.09%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
	//@ requires(*1. The input arrays `list1` and `list2` are not null.*);
	//@ requires(*2. The lengths of `list1` and `list2` are greater than or equal to 1.*);
	//@ requires(*3. The lengths of all strings in `list1` and `list2` are greater than or equal to 1 and less than or equal to 30.*);
	//@ requires(*4. All strings in `list1` and `list2` consist of spaces and English letters.*);
	//@ requires(*5. All strings in `list1` are unique.*);
	//@ requires(*6. All strings in `list2` are unique.*);
	//@ ensures(*1. The output array `result` is not null.*);
	//@ ensures(*2. The length of `result` is greater than or equal to 1.*);
	//@ ensures(*3. The strings in `result` are the common interest restaurants with the least list index sum.*);
	//@ ensures(*4. If there is a tie between answers, all common interest restaurants with the least list index sum are included in `result`.*);
	//@ ensures(*5. The order of the strings in `result` does not matter.*);
    public String[] findRestaurant(String[] list1, String[] list2) {
        int min = 1000000;
        Map<String, Integer> hm = new HashMap<>();
        List<String> result = new ArrayList<>();
        fillMap(list1, hm);
        // find min value
        for (int i = 0; i < list2.length; i++) {
            if (hm.containsKey(list2[i])) {
                int value = hm.get(list2[i]) + i;
                // a new min value was found
                if (value < min) {
                    min = value;
                    // Clean the arraylist
                    result.clear();
                    // add new min value
                    result.add(list2[i]);
                } else if (value == min) {
                    result.add(list2[i]);
                }
            }
        }
        return result.toArray(new String[result.size()]);
    }

    public void fillMap(String[] a, Map<String, Integer> hm) {
        for (int i = 0; i < a.length; i++) {
            hm.put(a[i], i);
        }
    }
}