package g1701_1800.s1785_minimum_elements_to_add_to_form_a_given_sum;

// #Medium #Array #Greedy #2022_04_30_Time_2_ms_(70.75%)_Space_82.5_MB_(57.55%)

public class Solution {
	//@ requires(*The input array `nums` is not null.*);
	//@ requires(*The length of the input array `nums` is greater than or equal to 1.*);
	//@ requires(*The limit `limit` is a positive integer.*);
	//@ requires(*The goal `goal` is an integer within the range of -10^9 to 10^9.*);
	//@ ensures(*The output is an integer representing the minimum number of elements needed to add to the array `nums` to make the sum equal to `goal`.*);
	//@ ensures(*The sum of the array `nums` plus the added elements is equal to `goal`.*);
	//@ ensures(*The array `nums` maintains its property that `abs(nums[i]) <= limit`.*);
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long diff = Math.abs(goal - sum);
        return diff % limit == 0 ? (int) (diff / limit) : (int) ((diff / limit) + 1);
    }
}