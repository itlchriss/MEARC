package g2301_2400.s2369_check_if_there_is_a_valid_partition_for_the_array;

// #Medium #Array #Dynamic_Programming #2022_08_16_Time_7_ms_(81.82%)_Space_93.5_MB_(9.09%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*Return `true` _if the array has **at least** one valid partition_. Otherwise, return `false`. **Explanation:** The array can be partitioned into the subarrays [4,4] and [4,5,6]. This partition is valid, so we return true.
Return `true` _if the array has **at least** one valid partition_. Otherwise, return `false`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public boolean validPartition(int[] nums) {
        boolean[] canPartition = new boolean[nums.length + 1];
        canPartition[0] = true;
        int diff = nums[1] - nums[0];
        boolean equal = diff == 0;
        boolean incOne = diff == 1;
        canPartition[2] = equal;
        for (int i = 3; i < canPartition.length; i++) {
            diff = nums[i - 1] - nums[i - 2];
            if (diff == 0) {
                canPartition[i] = canPartition[i - 2] || (equal && canPartition[i - 3]);
                equal = true;
                incOne = false;
            } else if (diff == 1) {
                canPartition[i] = incOne && canPartition[i - 3];
                equal = false;
                incOne = true;
            }
        }
        return canPartition[nums.length];
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
