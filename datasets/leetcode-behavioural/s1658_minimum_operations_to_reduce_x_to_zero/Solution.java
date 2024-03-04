package g1601_1700.s1658_minimum_operations_to_reduce_x_to_zero;

// #Medium #Array #Hash_Table #Binary_Search #Prefix_Sum #Sliding_Window
// #2022_04_23_Time_4_ms_(98.95%)_Space_97.8_MB_(46.33%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are positive integers.*);
//@ ensures(*The input integer `x` is a positive integer.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output is an integer representing the minimum number of operations required to reduce `x` to exactly 0.*);
//@ ensures(*If it is not possible to reduce `x` to exactly 0, the output is -1.*);
    public int minOperations(int[] nums, int x) {
        int totalArraySum = 0;
        for (int each : nums) {
            totalArraySum += each;
        }
        if (totalArraySum == x) {
            return nums.length;
        }
        int target = totalArraySum - x;
        // as we need to find value equal to x so that x-x=0,
        // and we need to search the longest sub array with sum equal t0 total array sum -x;
        int sum = 0;
        int result = -1;
        int start = 0;
        for (int end = 0; end < nums.length; end++) {
            sum += nums[end];
            while (sum > target && start < nums.length) {
                sum -= nums[start];
                start++;
            }
            if (sum == target) {
                result = Math.max(result, end + 1 - start);
            }
        }
        if (result == -1) {
            return result;
        } else {
            return nums.length - result;
        }
    }
}