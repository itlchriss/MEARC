package g2501_2600.s2563_count_the_number_of_fair_pairs;

// #Medium #Array #Sorting #Binary_Search #Two_Pointers
// #2023_08_21_Time_23_ms_(100.00%)_Space_55.5_MB_(86.80%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 2.*);
//@ ensures(*The values of `lower` and `upper` are within the range of -10^9 to 10^9.*);
//@ ensures(*The value of `lower` is less than or equal to the value of `upper`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a long value representing the number of fair pairs.*);
//@ ensures(*The returned value is greater than or equal to 0.*);
//@ ensures(*The returned value is less than or equal to the total number of fair pairs possible with the given input.*);
//@ ensures(*The method does not modify the input array `nums`.*);
    public long countFairPairs(int[] nums, int lower, int upper) {
        Arrays.sort(nums);
        long c1 = 0;
        long c2 = 0;
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] <= upper) {
                c1 = c1 + (j - i);
                i++;
            } else {
                j--;
            }
        }
        i = 0;
        j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] < lower) {
                c2 = c2 + (j - i);
                i++;
            } else {
                j--;
            }
        }
        return c1 - c2;
    }
}