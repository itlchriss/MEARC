package g0901_1000.s0961_n_repeated_element_in_size_2n_array;

// #Easy #Array #Hash_Table #2022_03_31_Time_1_ms_(87.33%)_Space_54.1_MB_(66.98%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is equal to twice the integer parameter `n`.*);
//@ requires(*All the values in the integer array parameter `nums` are unique, except for one value which is repeated `n` times.*);
//@ ensures(*The integer result is equal to the value that is repeated `n` times in the integer array parameter `nums`.*);
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