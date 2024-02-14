package g2301_2400.s2367_number_of_arithmetic_triplets;

// #Easy #Array #Hash_Table #Two_Pointers #Enumeration
// #2022_08_14_Time_3_ms_(66.67%)_Space_42.1_MB_(25.00%)

import java.util.HashSet;
import java.util.Set;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The input array `nums` is strictly increasing.*);
	//@ requires(*The input array `nums` has a length greater than or equal to 3.*);
	//@ requires(*The input integer `diff` is a positive integer.*);
	//@ requires(*The input integer `diff` is less than or equal to 50.*);
	//@ ensures(*The method returns an integer representing the number of unique arithmetic triplets.*);
	//@ ensures(*The method does not modify the input array `nums`.*);
	//@ ensures(*The method does not modify the input integer `diff`.*);
	//@ ensures(*The method does not have any side effects.*);
    public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }
        int ans = 0;
        for (int x : nums) {
            if (set.contains(x - diff) && set.contains(x + diff)) {
                ans++;
            }
        }
        return ans;
    }
}