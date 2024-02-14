package g0101_0200.s0167_two_sum_ii_input_array_is_sorted;

// #Medium #Array #Binary_Search #Two_Pointers #Algorithm_I_Day_3_Two_Pointers
// #Binary_Search_I_Day_7 #2022_06_25_Time_1_ms_(99.21%)_Space_50.3_MB_(31.33%)

public class Solution {
	//@ requires(*The input array `numbers` is not null.*);
	//@ requires(*The input array `numbers` is sorted in non-decreasing order.*);
	//@ requires(*The length of the input array `numbers` is at least 2.*);
	//@ requires(*The target number `target` is within the range of -1000 to 1000.*);
	//@ ensures(*The output array `result` is not null.*);
	//@ ensures(*The length of the output array `result` is 2.*);
	//@ ensures(*The elements in the output array `result` are the indices of the two numbers in the input array `numbers` that add up to the target number.*);
	//@ ensures(*The indices in the output array `result` are 1-indexed.*);
	//@ ensures(*The indices in the output array `result` are in ascending order.*);
	//@ ensures(*The sum of the two numbers at the indices in the output array `result` is equal to the target number.*);
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int i = 0;
        int j = numbers.length - 1;
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