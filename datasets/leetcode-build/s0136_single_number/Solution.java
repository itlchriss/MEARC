package g0101_0200.s0136_single_number;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Bit_Manipulation
// #Data_Structure_II_Day_1_Array #Algorithm_I_Day_14_Bit_Manipulation #Udemy_Integers
// #Big_O_Time_O(N)_Space_O(1) #2022_06_24_Time_1_ms_(99.97%)_Space_50.9_MB_(35.58%)

public class Solution {
	//@ requires(*The input array `nums` is not empty.*);
	//@ requires(*The length of the input array `nums` is at least 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ requires(*The elements in the input array `nums` are within the range of -3 * 10^4 to 3 * 10^4.*);
	//@ ensures(*The method returns an integer.*);
	//@ ensures(*The returned integer is the element in the input array `nums` that appears only once.*);
	//@ ensures(*The method has a linear runtime complexity.*);
	//@ ensures(*The method uses only constant extra space.*);
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}