package g1001_1100.s1013_partition_array_into_three_parts_with_equal_sum;

// #Easy #Array #Greedy #2022_05_02_Time_1_ms_(100.00%)_Space_60.6_MB_(25.47%)

public class Solution {
//@ requires(*The length of the integer array parameter `arr` is less than or equal to 50000 and is greater than or equal to 3.*);
//@ requires(*All values in the integer array parameter `arr` are less than or equal to 10000 and are greater than or equal to -10000.*);
//@ ensures(*If the boolean result is equal to the true literal, the integer array parameter `arr` can be partitioned into three non-empty parts with equal sums.*);
//@ ensures(*If the boolean result is equal to the false literal, the integer array parameter `arr` cannot be partitioned into three non-empty parts with equal sums.*);
//@ ensures(*If the integer array parameter `arr` is equal to [0,2,1,-6,6,-7,9,1,2,0,1], the boolean result is equal to the true literal.*);
//@ ensures(*If the integer array parameter `arr` is equal to [0,2,1,-6,6,7,9,-1,2,0,1], the boolean result is equal to the false literal.*);
//@ ensures(*If the integer array parameter `arr` is equal to [3,3,6,5,-2,2,5,1,-9,4], the boolean result is equal to the true literal.*);
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum = 0;
        for (int j : arr) {
            sum += j;
        }
        // 1. Base condition , the sum should be equally divided into 3 parts
        if (sum % 3 != 0) {
            return false;
        }
        int eq = sum / 3;
        // to keep track of occurences of sum in the sub array
        int count = 0;
        int temp = 0;
        for (int j : arr) {
            // 2. Base / Break condition for loop , i.e. if the count is 2,
            // i.e. sum has been achieved twice ( and there is more indices
            // to go through since we are in the loop again ) then return true
            if (count == 2) {
                return true;
            }
            // 3. Adding to temp array
            temp += j;
            // 4. If sum is achieved , increase the count
            if (temp == eq) {
                count++;
                // put temp=0 to start summing up from the next indices
                temp = 0;
            }
        }
        // 5. If the above conditoin fails , result is false
        return false;
    }
}