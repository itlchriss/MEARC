package g2201_2300.s2256_minimum_average_difference;

// #Medium #Array #Prefix_Sum #2022_06_12_Time_15_ms_(97.85%)_Space_71.2_MB_(84.53%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are non-negative integers.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned index is a valid index in the input array `nums`.*);
//@ ensures(*The returned index is the index with the minimum average difference.*);
//@ ensures(*If there are multiple indices with the minimum average difference, the returned index is the smallest one.*);
    public int minimumAverageDifference(int[] nums) {
        long numsSum = 0;
        for (int num : nums) {
            numsSum += num;
        }
        long minAverageDiff = Long.MAX_VALUE;
        long sumFromFront = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            sumFromFront += nums[i];
            int numbersRight = i == nums.length - 1 ? 1 : nums.length - i - 1;
            long averageDiff =
                    Math.abs(sumFromFront / (i + 1) - (numsSum - sumFromFront) / numbersRight);
            if (minAverageDiff > averageDiff) {
                minAverageDiff = averageDiff;
                index = i;
            }
            if (averageDiff == 0) {
                break;
            }
        }
        return index;
    }
}