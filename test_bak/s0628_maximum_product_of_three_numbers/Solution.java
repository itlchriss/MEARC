package g0601_0700.s0628_maximum_product_of_three_numbers;

// #Easy #Array #Math #Sorting #2022_03_21_Time_2_ms_(99.90%)_Space_55.5_MB_(5.19%)

public class Solution {
//@ requires(*The length of the integer array parameter `nums` is less than or equal to 10000 and is greater than or equal to 3.*);
//@ requires(*All the values in the integer array parameter `nums` are less than or equal to 1000 and are greater than or equal to -1000.*);
//@ ensures(*The integer result is less than or equal to the product of the three largest values in the integer array parameter `nums` and is greater than or equal to the product of the two smallest values in the integer array parameter `nums` multiplied by the largest value in the integer array parameter `nums`.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3], the integer result is equal to 6.*);
//@ ensures(*If the integer array parameter `nums` is equal to [1,2,3,4], the integer result is equal to 24.*);
//@ ensures(*If the integer array parameter `nums` is equal to [-1,-2,-3], the integer result is equal to -6.*);
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i > max1) {
                max3 = max2;
                max2 = max1;
                max1 = i;
            } else if (i > max2) {
                max3 = max2;
                max2 = i;
            } else if (i > max3) {
                max3 = i;
            }
            if (i < min1) {
                min2 = min1;
                min1 = i;
            } else if (i < min2) {
                min2 = i;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}