package g0701_0800.s0795_number_of_subarrays_with_bounded_maximum;

// #Medium #Array #Two_Pointers #2022_03_26_Time_2_ms_(100.00%)_Space_49.8_MB_(90.64%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The values in the input array `nums` are non-negative integers.*);
//@ ensures(*The values in the input array `nums` are within the range of a 32-bit integer.*);
//@ ensures(*The value of `left` is non-negative.*);
//@ ensures(*The value of `right` is non-negative.*);
//@ ensures(*The value of `left` is less than or equal to the value of `right`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value.*);
//@ ensures(*The returned integer value represents the number of contiguous non-empty subarrays that meet the requirements.*);
//@ ensures(*The returned integer value is within the range of a 32-bit integer.*);
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int i = 0;
        int j = 0;
        int count = 0;
        int tempSum = 0;
        while (j < nums.length) {
            if (nums[j] > right) {
                tempSum = 0;
                i = ++j;
            } else if (nums[j] < left) {
                count += tempSum;
                j++;
            } else {
                tempSum = j - i + 1;
                count += tempSum;
                j++;
            }
        }
        return count;
    }
}