package g0901_1000.s0905_sort_array_by_parity;

// #Easy #Array #Sorting #Two_Pointers #2022_03_28_Time_1_ms_(95.51%)_Space_48.4_MB_(46.34%)

public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `nums` is not null.*);
//@ ensures(*The length of the input array `nums` is greater than or equal to 1.*);
//@ ensures(*The elements in the input array `nums` are integers.*);
//@ ensures(*The elements in the input array `nums` are within the range of 0 to 5000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The output array is not null.*);
//@ ensures(*The length of the output array is the same as the input array.*);
//@ ensures(*The elements in the output array are integers.*);
//@ ensures(*The elements in the output array are sorted such that all the even integers are at the beginning followed by all the odd integers.*);
//@ ensures(*The output array satisfies the condition of having all the even integers at the beginning followed by all the odd integers.*);
    public int[] sortArrayByParity(int[] nums) {
        int temp;
        int i = 0;
        for (int k = 0; k < nums.length; k++) {
            if (nums[k] % 2 == 0) {
                temp = nums[k];
                nums[k] = nums[i];
                nums[i] = temp;
                i++;
            }
        }
        return nums;
    }
}