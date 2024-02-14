package g2701_2800.s2729_check_if_the_number_is_fascinating;

// #Easy #Hash_Table #Math #2023_09_19_Time_1_ms_(96.07%)_Space_40.3_MB_(46.53%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
	//@ requires(*The input `n` is an integer.*);
	//@ requires(*`n` consists of exactly 3 digits.*);
	//@ requires(*`100 <= n <= 999`.*);
	//@ ensures(*The method returns a boolean value (`true` or `false`).*);
	//@ ensures(*If `n` is fascinating, the method returns `true`.*);
	//@ ensures(*If `n` is not fascinating, the method returns `false`.*);
	//@ ensures(*The resulting number after concatenation contains all the digits from 1 to 9 exactly once.*);
	//@ ensures(*The resulting number after concatenation does not contain any 0's.*);
    public boolean isFascinating(int n) {
        Set<Integer> set = new HashSet<>();
        return add(set, n) && add(set, 2 * n) && add(set, 3 * n);
    }

    private boolean add(Set<Integer> set, int cur) {
        while (cur > 0) {
            int digit = cur % 10;
            if (digit == 0 || set.contains(digit)) {
                return false;
            }
            set.add(digit);
            cur /= 10;
        }
        return true;
    }
}