package g2201_2300.s2293_min_max_game;

// #Easy #Array #Simulation #2022_06_14_Time_1_ms_(90.39%)_Space_44_MB_(50.37%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of `nums` is a power of 2.*);
	//@ requires(*The elements of `nums` are integers.*);
	//@ requires(*The elements of `nums` are non-negative.*);
	//@ requires(*The elements of `nums` are within the range of 1 to 10^9.*);
	//@ ensures(*The last remaining number in `nums` is returned as an integer.*);
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] newNums = new int[n / 2];
        for (int i = 0; i < n / 2; i++) {
            if (i % 2 == 0) {
                newNums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
            } else {
                newNums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
            }
        }
        return minMaxGame(newNums);
    }
}