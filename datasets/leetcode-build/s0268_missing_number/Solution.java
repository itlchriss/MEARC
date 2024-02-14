package g0201_0300.s0268_missing_number;

// #Easy #Top_Interview_Questions #Array #Hash_Table #Math #Sorting #Binary_Search #Bit_Manipulation
// #2022_07_05_Time_1_ms_(72.07%)_Space_50.6_MB_(76.88%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is equal to `n`.*);
	//@ requires(*The numbers in the input array `nums` are unique.*);
	//@ requires(*The range of numbers in the input array `nums` is from 0 to `n`.*);
	//@ ensures(*The output is an integer representing the missing number in the range from 0 to `n`.*);
	//@ ensures(*The output is the only number in the range that is missing from the input array `nums`.*);
    public int missingNumber(int[] nums) {
        int a = nums.length;
        for (int i = 0; i < nums.length; i++) {
            a ^= i;
            a ^= nums[i];
        }
        return a;
    }
}