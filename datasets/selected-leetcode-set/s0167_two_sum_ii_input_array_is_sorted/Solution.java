package g0101_0200.s0167_two_sum_ii_input_array_is_sorted;

// #Medium #Array #Binary_Search #Two_Pointers #Algorithm_I_Day_3_Two_Pointers
// #Binary_Search_I_Day_7 #2022_06_25_Time_1_ms_(99.21%)_Space_50.3_MB_(31.33%)

public class Solution {
//@ requires(*The integer array parameter `numbers` is sorted in non-decreasing order.*);
//@ requires(*The integer array parameter `numbers` contains at least 2 elements.*);
//@ requires(*The integer parameter `target` is greater than or equal to -1000 and is less than or equal to 1000.*);
//@ ensures(*The integer array result contains the indices of two numbers in the integer array parameter `numbers` that add up to the integer parameter `target`.*);
//@ ensures(*The indices in the integer array result are 1-indexed.*);
//@ ensures(*The indices in the integer array result are added by one.*);
//@ ensures(*The length of the integer array result is equal to 2.*);
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int i = 0;
        //@ assume numbers.length > 1;
        int j = numbers.length - 1;
        //@ maintaining 0 <= i <= numbers.length;
        //@ maintaining 0 <= j < numbers.length;
        //@ maintaining i < j || i == j;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                res[0] = i + 1;
                res[1] = j + 1;
                return res;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}