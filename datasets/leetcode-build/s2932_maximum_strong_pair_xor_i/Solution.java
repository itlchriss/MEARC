package g2901_3000.s2932_maximum_strong_pair_xor_i;

// #Easy #Array #Hash_Table #Bit_Manipulation #Sliding_Window #Trie
// #2024_01_02_Time_2_ms_(98.64%)_Space_43.1_MB_(59.18%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is at least 1.*);
	//@ requires(*Each element in the input array `nums` is a positive integer.*);
	//@ requires(*Each element in the input array `nums` is at most 100.*);
	//@ ensures(*The return value is an integer.*);
	//@ ensures(*The return value is the maximum XOR value out of all possible strong pairs in the input array `nums`.*);
    public int maximumStrongPairXor(int[] nums) {
        int max = 0;
        int pair = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (Math.abs(nums[i] - nums[j]) <= Math.min(nums[i], nums[j])) {
                    pair = nums[i] ^ nums[j];
                    max = Math.max(max, pair);
                }
            }
        }
        return max;
    }
}