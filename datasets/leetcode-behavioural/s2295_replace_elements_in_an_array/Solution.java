package g2201_2300.s2295_replace_elements_in_an_array;

// #Medium #Array #Hash_Table #Simulation #2022_06_16_Time_89_ms_(65.26%)_Space_170.7_MB_(17.18%)

import java.util.HashMap;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The input array `operations` is not null.*);
//@ ensures(*The length of `nums` is equal to `n`.*);
//@ ensures(*The length of `operations` is equal to `m`.*);
//@ ensures(*The values in `nums` are distinct.*);
//@ ensures(*Each operation in `operations` is a 2-element array.*);
//@ ensures(*The first element of each operation exists in `nums`.*);
//@ ensures(*The second element of each operation does not exist in `nums`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array is not null.*);
//@ ensures(*The length of the returned array is equal to `n`.*);
//@ ensures(*The values in the returned array are the result of applying all the operations to `nums`.*);
    public int[] arrayChange(int[] nums, int[][] operations) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int[] operation : operations) {
            int index = map.get(operation[0]);
            nums[index] = operation[1];
            map.put(operation[1], index);
        }
        return nums;
    }
}