package g1201_1300.s1296_divide_array_in_sets_of_k_consecutive_numbers;

// #Medium #Array #Hash_Table #Sorting #Greedy
// #2022_03_12_Time_39_ms_(97.80%)_Space_55.3_MB_(85.50%)

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `nums` contains only integers.*);
//@ ensures(*The input integer `k` is positive.*);
//@ ensures(*The input integer `k` is less than or equal to the length of the array `nums`.*);
//@ ensures(*The input array `nums` is not empty.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a boolean value indicating whether it is possible to divide the array into sets of `k` consecutive numbers.*);
//@ ensures(*If it is possible to divide the array into sets of `k` consecutive numbers, the method returns `true`.*);
//@ ensures(*If it is not possible to divide the array into sets of `k` consecutive numbers, the method returns `false`.*);
    public boolean isPossibleDivide(int[] nums, int k) {
        Arrays.sort(nums);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (map.get(num) == 0) {
                continue;
            }
            for (int v = num; v < num + k; v++) {
                if (!map.containsKey(v) || map.get(v) == 0) {
                    return false;
                } else {
                    map.put(v, map.get(v) - 1);
                }
            }
        }
        return true;
    }
}