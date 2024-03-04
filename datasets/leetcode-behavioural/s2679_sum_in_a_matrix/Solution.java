package g2601_2700.s2679_sum_in_a_matrix;

// #Medium #Array #Sorting #Matrix #Heap_Priority_Queue #Simulation
// #2023_09_11_Time_13_ms_(99.66%)_Space_56.9_MB_(31.71%)

import java.util.Arrays;

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input `nums` is a 2D integer array.*);
//@ ensures(*The length of `nums` is greater than or equal to 1 and less than or equal to - Each row of `nums` is a non-empty array with a length greater than or equal to 1 and less than or equal to - Each element in `nums` is an integer between 0 and 1000 (inclusive).*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the final score.*);
//@ ensures(*The matrix `nums` becomes empty after performing the operations.*);
//@ ensures(**);
//@ ensures(*Other possible behavioral requirements:*);
//@ ensures(*The method should handle the case when `nums` is empty by returning - The method should handle the case when `nums` contains only one row by returning the maximum element in that row.*);
//@ ensures(*The method should handle the case when `nums` contains only one column by returning the sum of all elements in that column.*);
//@ ensures(*The method should handle the case when `nums` contains only one element by returning that element.*);
//@ ensures(*The method should handle the case when all elements in `nums` are the same by returning the sum of all elements.*);
//@ ensures(*The method should handle the case when there are ties for the largest number in a row by selecting any of the tied numbers.*);
//@ ensures(*The method should handle the case when there are ties for the highest number among all those removed in step 1 by selecting any of the tied numbers.*);
    public int matrixSum(int[][] nums) {
        int result = 0;

        for (int[] row : nums) {
            Arrays.sort(row);
            reverseArray(row);
        }

        for (int i = 0; i < nums[0].length; i++) {
            int max = 0;
            for (int[] num : nums) {
                max = Math.max(max, num[i]);
            }
            result += max;
        }

        return result;
    }

    private void reverseArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}