package g1501_1600.s1558_minimum_numbers_of_function_calls_to_make_target_array;

// #Medium #Array #Greedy #2022_04_11_Time_42_ms_(69.17%)_Space_55.7_MB_(81.20%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return _the minimum number of function calls to make_ `nums` _from_ `arr`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int minOperations(int[] nums) {
        int ops = 0;
        for (int bit = 0; bit < 32; bit++) {
            boolean nonzero = false;
            for (int i = 0; i < nums.length; i++) {
                ops += nums[i] % 2;
                nums[i] /= 2;
                nonzero |= nums[i] > 0;
            }
            if (nonzero) {
                ops++;
            } else {
                break;
            }
        }
        return ops;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
