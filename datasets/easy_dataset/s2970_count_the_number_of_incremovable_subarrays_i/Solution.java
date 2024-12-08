package g2901_3000.s2970_count_the_number_of_incremovable_subarrays_i;

// #Easy #Array #Binary_Search #Two_Pointers #Enumeration
// #2024_01_16_Time_0_ms_(100.00%)_Space_42.9_MB_(87.73%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*All elements in the input array `nums` are positive integers.*);
//@ ensures(*The input array `nums` is 0-indexed.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer value representing the total number of incremovable subarrays of `nums`.*);
//@ ensures(*The method does not modify the input array `nums`.*);
//@ ensures(*The method considers an empty array as a strictly increasing subarray.*);
//@ ensures(*The method correctly counts all the incremovable subarrays of `nums`.*);
//@ ensures(*The method handles the case where `nums` contains duplicate elements correctly.*);
    public int incremovableSubarrayCount(int[] nums) {
        int n = nums.length;
        int res = 0;
        int left = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int right = Integer.MAX_VALUE;
            for (int j = n - 1; i <= j; j--) {
                res++;
                if (left >= nums[j] || nums[j] >= right) {
                    break;
                }
                right = nums[j];
            }
            if (left >= nums[i]) {
                break;
            }
            left = nums[i];
        }
        return res;
    }
}