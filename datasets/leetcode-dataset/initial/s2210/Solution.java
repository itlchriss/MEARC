package g2201_2300.s2210_count_hills_and_valleys_in_an_array;

// #Easy #Array #2022_06_12_Time_0_ms_(100.00%)_Space_41.8_MB_(60.16%)

public class Solution {
<<<<<<< HEAD
=======
//@ ensures(*At index 5: There is no non-equal neighbor of 5 on the right, so index 5 is neither a hill nor a valley. There are 3 hills and valleys so we return 3. At index 5: There is no non-equal neighbor of 1 on the right, so index 5 is neither a hill nor a valley. There are 0 hills and valleys so we return 0.
Return _the number of hills and valleys in_ `nums`.*);

>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
    public int countHillValley(int[] nums) {
        int left = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if ((left > nums[i] && nums[i + 1] > nums[i])
                    || (left < nums[i] && nums[i + 1] < nums[i])) {
                count++;
                left = nums[i];
            }
        }
        return count;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
