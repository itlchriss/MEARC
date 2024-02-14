package g1701_1800.s1753_maximum_score_from_removing_stones;

// #Medium #Math #Greedy #Heap_Priority_Queue #2022_04_30_Time_0_ms_(100.00%)_Space_41.5_MB_(70.96%)

import java.util.Arrays;

public class Solution {
	//@ requires(*The method takes three integer parameters `a`, `b`, and `c`.*);
	//@ requires(*The values of `a`, `b`, and `c` are positive integers.*);
	//@ requires(*The values of `a`, `b`, and `c` are less than or equal to 10^5.*);
	//@ ensures(*The method returns an integer value representing the maximum score.*);
	//@ ensures(*The maximum score is calculated based on the given rules of the solitaire game.*);
	//@ ensures(*The game stops when there are fewer than two non-empty piles.*);
	//@ ensures(*The maximum score is the total number of points obtained during the game.*);
    public int maximumScore(int a, int b, int c) {
        int[] nums = new int[] {a, b, c};
        Arrays.sort(nums);
        if (nums[0] + nums[1] < nums[2]) {
            return nums[0] + nums[1];
        } else {
            return (nums[0] + nums[1] + nums[2]) / 2;
        }
    }
}