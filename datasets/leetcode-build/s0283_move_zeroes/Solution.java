package g0201_0300.s0283_move_zeroes;

// #Easy #Top_100_Liked_Questions #Top_Interview_Questions #Array #Two_Pointers
// #Algorithm_I_Day_3_Two_Pointers #Programming_Skills_I_Day_6_Array #Udemy_Arrays
// #Big_O_Time_O(n)_Space_O(1) #2022_07_06_Time_2_ms_(79.54%)_Space_55.7_MB_(5.98%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The elements in the input array `nums` are integers.*);
	//@ ensures(*All the non-zero elements in the input array `nums` are moved to the beginning of the array while maintaining their relative order.*);
	//@ ensures(*All the zero elements in the input array `nums` are moved to the end of the array.*);
	//@ ensures(*The order of the non-zero elements in the input array `nums` is preserved.*);
	//@ ensures(*The order of the zero elements in the input array `nums` is preserved.*);
	//@ ensures(*The length of the input array `nums` remains the same.*);
	//@ ensures(*The input array `nums` is modified in-place, without making a copy of the array.*);
    public void moveZeroes(int[] nums) {
        int firstZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(firstZero, i, nums);
                firstZero++;
            }
        }
    }

    private void swap(int index1, int index2, int[] numbers) {
        int val2 = numbers[index2];
        numbers[index2] = numbers[index1];
        numbers[index1] = val2;
    }
}