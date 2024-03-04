package g1301_1400.s1330_reverse_subarray_to_maximize_array_value;

// #Hard #Array #Math #Greedy #2022_03_19_Time_9_ms_(88.00%)_Space_63.9_MB_(20.00%)

public class Solution {

    private int getAbsoluteDifference(int a, int b) {
        return Math.abs(a - b);
    }
//@ ensures(*Preconditions:*);
//@ ensures(*1. The input array `nums` must not be null.*);
//@ ensures(*2. The length of the input array `nums` must be greater than or equal to 2.*);
//@ ensures(*3. The elements in the input array `nums` must be integers.*);
//@ ensures(*4. The elements in the input array `nums` must be within the range of -10^5 to 10^5.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*1. The method should return an integer value representing the maximum possible value of the final array.*);
//@ ensures(*2. The input array `nums` should remain unchanged after the method is executed.*);
//@ ensures(*3. The final array should be obtained by reversing a subarray of the input array `nums`.*);
//@ ensures(*4. The value of the final array should be calculated as the sum of the absolute differences between adjacent elements in the array.*);

    public int maxValueAfterReverse(int[] nums) {
        int n = nums.length;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            result += getAbsoluteDifference(nums[i], nums[i + 1]);
        }

        int minLine = Integer.MIN_VALUE;
        int maxLine = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            minLine = Math.max(minLine, Math.min(nums[i], nums[i + 1]));
            maxLine = Math.min(maxLine, Math.max(nums[i], nums[i + 1]));
        }
        int diff = Math.max(0, (minLine - maxLine) * 2);
        for (int i = 1; i < n - 1; i++) {
            diff =
                    Math.max(
                            diff,
                            getAbsoluteDifference(nums[0], nums[i + 1])
                                    - getAbsoluteDifference(nums[i], nums[i + 1]));
        }

        for (int i = 0; i < n - 1; i++) {
            diff =
                    Math.max(
                            diff,
                            getAbsoluteDifference(nums[n - 1], nums[i])
                                    - getAbsoluteDifference(nums[i + 1], nums[i]));
        }
        return result + diff;
    }
}