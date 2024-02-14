package g0201_0300.s0287_find_the_duplicate_number;

// #Medium #Top_100_Liked_Questions #Top_Interview_Questions #Array #Binary_Search #Two_Pointers
// #Bit_Manipulation #Binary_Search_II_Day_5 #Big_O_Time_O(n)_Space_O(n)
// #2022_07_06_Time_2_ms_(99.82%)_Space_61.1_MB_(83.92%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 2.*);
	//@ requires(*All the integers in `nums` are in the range `[1, n]` inclusive.*);
	//@ requires(*There is exactly one repeated number in `nums`.*);
	//@ ensures(*The method returns the repeated number in `nums`.*);
    public int findDuplicate(int[] nums) {
        int[] arr = new int[nums.length + 1];
        for (int num : nums) {
            arr[num] += 1;
            if (arr[num] == 2) {
                return num;
            }
        }
        return 0;
    }
}