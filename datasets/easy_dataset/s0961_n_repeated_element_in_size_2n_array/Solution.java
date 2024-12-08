package g0901_1000.s0961_n_repeated_element_in_size_2n_array;

// #Easy #Array #Hash_Table #2022_03_31_Time_1_ms_(87.33%)_Space_54.1_MB_(66.98%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is even.*);
//@ ensures(*The length of the input array `nums` is equal to twice the value of `n`.*);
//@ ensures(*The input array `nums` contains `n + 1` unique elements.*);
//@ ensures(*Exactly one element in the input array `nums` is repeated `n` times.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned integer value is the element that is repeated `n` times in the input array `nums`.*);
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