package g0901_1000.s0961_n_repeated_element_in_size_2n_array;

// #Easy #Array #Hash_Table #2022_03_31_Time_1_ms_(87.33%)_Space_54.1_MB_(66.98%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000 and is greater than or equal to 2.*);
//@ requires(*The length of the integer array parameter `nums` is an even number.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 10000 and are greater than or equal to 0.*);
//@ requires(*The integer array parameter `nums` contains n + 1 unique elements.*);
//@ requires(*Exactly one element of the integer array parameter `nums` is repeated n times.*);
//@ ensures(*The integer result is greater than or equal to 0 and is less than or equal to 10000.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,3], the integer result is equal to 3.*);
//@ ensures(*If the integer array parameter `nums` is equal to [2,1,2,5,3,2], the integer result is equal to 2.*);
//@ ensures(*If the integer array parameter `nums` is equal to [5,1,5,2,5,3,5,4], the integer result is equal to 5.*);
    public int repeatedNTimes(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }
}