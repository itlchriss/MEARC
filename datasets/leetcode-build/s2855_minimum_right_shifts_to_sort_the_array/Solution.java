package g2801_2900.s2855_minimum_right_shifts_to_sort_the_array;

// #Easy #Array #2023_12_15_Time_1_ms_(100.00%)_Space_42.6_MB_(5.66%)

import java.util.List;

public class Solution {
	//@ requires(*The input list `nums` is not null.*);
	//@ requires(*The length of the input list `nums` is greater than or equal to 1.*);
	//@ requires(*The input list `nums` contains distinct positive integers.*);
	//@ requires(*The integers in the input list `nums` are between 1 and 100 (inclusive).*);
	//@ ensures(*The method returns an integer value.*);
	//@ ensures(*If it is possible to sort the array using right shifts, the method returns the minimum number of right shifts required.*);
	//@ ensures(*If it is not possible to sort the array using right shifts, the method returns -1.*);
    public int minimumRightShifts(List<Integer> nums) {
        int i;
        for (i = 1; i < nums.size(); i++) {
            if (nums.get(i) < nums.get(i - 1)) {
                break;
            }
        }
        if (nums.size() == i) {
            return 0;
        } else {
            int k;
            for (k = i + 1; k < nums.size(); k++) {
                if (nums.get(k) <= nums.get(k - 1)) {
                    break;
                }
            }
            if (k == nums.size() && nums.get(k - 1) < nums.get(0)) {
                return nums.size() - i;
            }
            return -1;
        }
    }
}