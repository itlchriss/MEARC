package g1801_1900.s1802_maximum_value_at_a_given_index_in_a_bounded_array;

// #Medium #Greedy #Binary_Search #Binary_Search_II_Day_17
// #2022_05_02_Time_2_ms_(58.44%)_Space_40.9_MB_(65.43%)

public class Solution {
    private boolean isPossible(int n, int index, int maxSum, int value) {
        int leftValue = Math.max(value - index, 0);
        int rightValue = Math.max(value - ((n - 1) - index), 0);
        long sumBefore = (long) (value + leftValue) * (value - leftValue + 1) / 2;
        long sumAfter = (long) (value + rightValue) * (value - rightValue + 1) / 2;
        return sumBefore + sumAfter - value <= maxSum;
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input integers `n`, `index`, and `maxSum` are positive.*);
//@ ensures(*The value of `n` is greater than or equal to 1.*);
//@ ensures(*The value of `index` is greater than or equal to 0 and less than `n`.*);
//@ ensures(*The value of `maxSum` is greater than or equal to `n`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned value is an integer.*);
//@ ensures(*The returned value is the value of `nums[index]` in the constructed array.*);
//@ ensures(*The length of the constructed array `nums` is equal to `n`.*);
//@ ensures(*Each element `nums[i]` in the constructed array is a positive integer, where `0 <= i < n`.*);
//@ ensures(*The absolute difference between each adjacent pair of elements in the constructed array is less than or equal to 1.*);
//@ ensures(*The sum of all the elements in the constructed array does not exceed `maxSum`.*);

    public int maxValue(int n, int index, int maxSum) {
        int left = 0;
        int right = maxSum - n;
        while (left < right) {
            int middle = (left + right + 1) / 2;
            if (isPossible(n, index, maxSum - n, middle)) {
                left = middle;
            } else {
                right = middle - 1;
            }
        }
        return left + 1;
    }
}