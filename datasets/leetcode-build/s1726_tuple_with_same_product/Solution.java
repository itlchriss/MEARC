package g1701_1800.s1726_tuple_with_same_product;

// #Medium #Array #Hash_Table #2022_04_28_Time_235_ms_(90.73%)_Space_63.4_MB_(87.42%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to - All elements in the input array `nums` are distinct positive integers.*);
	//@ requires(*The maximum value of the elements in the input array `nums` is less than or equal to 10^*);
	//@ ensures(*The method returns an integer representing the number of tuples (a, b, c, d) such that a * b = c * d, where a, b, c, and d are elements of the input array `nums` and a != b != c != d.*);
    public int tupleSameProduct(int[] nums) {
        HashMap<Integer, Integer> ab = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                ab.put(nums[i] * nums[j], ab.getOrDefault(nums[i] * nums[j], 0) + 1);
            }
        }
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : ab.entrySet()) {
            int val = entry.getValue();
            count = count + (val * (val - 1)) / 2;
        }
        return count * 8;
    }
}