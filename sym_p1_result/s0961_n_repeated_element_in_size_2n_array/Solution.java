package g0901_1000.s0961_n_repeated_element_in_size_2n_array;

// #Easy #Array #Hash_Table #2022_03_31_Time_1_ms_(87.33%)_Space_54.1_MB_(66.98%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
//@ requires(*You are given an integer array param_nums with the following properties:*);
//@ requires(*`nums.length == 2  n`.*);
//@ requires(*param_nums contains `n + 1` unique elements.*);
//@ requires(*Exactly one element of param_nums is repeated `n` times.*);
//@ requires(*Return the element that is repeated `n` times.*);
//@ requires(*Example 1:*);
//@ requires(*Input: nums = [1,2,3,3]*);
//@ requires(*Output: 3*);
//@ requires(*Example 2:*);
//@ requires(*Input: nums = [2,1,2,5,3,2]*);
//@ requires(*Output: 2*);
//@ requires(*Example 3:*);
//@ requires(*Input: nums = [5,1,5,2,5,3,5,4]*);
//@ requires(*Output: 5*);
//@ requires(*Constraints:*);
//@ requires(*`2 <= n <= 5000`*);
//@ requires(*`nums.length == 2  n`*);
//@ requires(*<code>0 <= nums[i] <= 10<sup>4</sup></code>*);
//@ requires(*param_nums contains `n + 1` unique elements and one of them is repeated exactly `n` times.*);
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