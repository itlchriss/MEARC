package g0601_0700.s0645_set_mismatch;

// #Easy #Array #Hash_Table #Sorting #Bit_Manipulation
// #2022_03_21_Time_2_ms_(97.45%)_Space_43.6_MB_(89.93%)

public class Solution {
//@ ensures(*The integer array parameter `nums` must not be null.*);
//@ ensures(*The integer array parameter `nums` must have a length greater than or equal to 2.*);
//@ ensures(*The integer array result contains two elements.*);
//@ ensures(*The first element of the integer array result is the number that occurs twice in the integer array parameter `nums`.*);
//@ ensures(*The second element of the integer array result is the number that is missing in the integer array parameter `nums`.*);
    public int[] findErrorNums(int[] nums) {
        int[] ans = new int[2];
        int i = 0;
        while (i < nums.length) {
            int correct = nums[i] - 1;
            if (nums[i] != nums[correct]) {
                int temp = nums[i];
                nums[i] = nums[correct];
                nums[correct] = temp;
            } else {
                i++;
            }
        }
        for (int a = 0; a < nums.length; a++) {
            if (nums[a] != a + 1) {
                ans[0] = nums[a];
                ans[1] = a + 1;
                break;
            }
        }
        return ans;
    }
}