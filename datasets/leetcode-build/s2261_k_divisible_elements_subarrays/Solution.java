package g2201_2300.s2261_k_divisible_elements_subarrays;

// #Medium #Array #Hash_Table #Trie #Enumeration #Hash_Function #Rolling_Hash
// #2022_06_18_Time_73_ms_(92.32%)_Space_67.2_MB_(78.03%)

import java.util.HashSet;

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is greater than or equal to 1 and less than or equal to 200.*);
	//@ requires(*Each element in `nums` is greater than or equal to 1 and less than or equal to 200.*);
	//@ requires(*The value of `k` is greater than or equal to 1 and less than or equal to the length of `nums`.*);
	//@ requires(*The value of `p` is greater than or equal to 1 and less than or equal to 200.*);
	//@ ensures(*The method returns an integer representing the number of distinct subarrays that have at most `k` elements divisible by `p`.*);
    public int countDistinct(int[] nums, int k, int p) {
        HashSet<Long> numSubarray = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int countDiv = 0;
            long hashCode = 1;
            for (int j = i; j < nums.length; j++) {
                hashCode = 199L * hashCode + nums[j];
                if (nums[j] % p == 0) {
                    countDiv++;
                }
                if (countDiv <= k) {
                    numSubarray.add(hashCode);
                } else {
                    break;
                }
            }
        }
        return numSubarray.size();
    }
}