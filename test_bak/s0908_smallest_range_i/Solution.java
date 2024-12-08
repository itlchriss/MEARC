package g0901_1000.s0908_smallest_range_i;

// #Easy #Array #Math #2022_03_28_Time_2_ms_(88.84%)_Space_41.9_MB_(99.76%)

public class Solution {
//@ requires(*The integer array parameter `nums` has a length less than or equal to 10000 and greater than or equal to 1.*);
//@ requires(*All values in the integer array parameter `nums` are less than or equal to 10000 and greater than or equal to 0.*);
//@ requires(*The integer parameter `k` is less than or equal to 10000 and greater than or equal to 0.*);
//@ ensures(*The integer result is less than or equal to the difference between the maximum value in the integer array parameter `nums` and the minimum value in the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1] and the integer parameter `k` is equal to 0, the integer result is equal to 0.*);
//@ ensures(*If the integer array parameter `nums` is equal to [0,10] and the integer parameter `k` is equal to 2, the integer result is equal to 6.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,3,6] and the integer parameter `k` is equal to 3, the integer result is equal to 0.*);
    public int smallestRangeI(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min + k >= max - k) {
            return 0;
        }
        return (max - k) - (min + k);
    }
}